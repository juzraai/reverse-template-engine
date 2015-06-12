package hu.juranyi.zsolt.rte.core.parser;

import hu.juranyi.zsolt.rte.data.parser.Scope;
import hu.juranyi.zsolt.rte.data.parser.State;
import hu.juranyi.zsolt.rte.data.template.Template;
import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;
import hu.juranyi.zsolt.rte.data.template.unit.PatternUnit;
import hu.juranyi.zsolt.rte.data.template.unit.TemplateUnit;

import java.util.List;

public class Parser extends Component {

	private State state;

	private String ftuId() {
		return ftuId(state.getScopes().peek().getFtu());
	}

	private String ftuId(FromTillUnit ftu) {
		return ftu.getId();
	}

	public void parse(String input, Template template)
			throws ParseFailedException {
		validate(input, template);
		state = new State(input);
		Scope scope = new Scope(template.getRoot(), 0, input.length());
		state.getScopes().push(scope);
		while (!state.getScopes().isEmpty()) {
			processCurrentScope();
		}
		// TODO produce output, return output vars from state. type?!
	}

	private void processCurrentScope() {
		Scope scope = state.getScopes().peek();
		List<TemplateUnit> units = scope.getFtu().getUnits();
		int next = scope.getNextUnit();
		if (next >= units.size()) {
			LOG.info("[POP] Processing completed of {}", ftuId());
			state.getScopes().pop();
			if (state.getScopes().isEmpty()) {
				LOG.info("Parsing completed.");
			} else {
				LOG.info("[PEEK] Processing {}", ftuId());
			}
		} else {
			processCurrentUnit(units.get(next));
			scope.setNextUnit(++next);
		}
	}

	private void processCurrentUnit(TemplateUnit templateUnit) {
		if (templateUnit instanceof FromTillUnit) {
			pushState((FromTillUnit) templateUnit);
		} else {
			LOG.info("Processing {} ({})", templateUnit.getId(), ftuId());
			if (templateUnit instanceof PatternUnit) {
				PatternUnit pu = (PatternUnit) templateUnit;
				Components.getComponent(PatternProcessor.class).processPattern(
						pu.getPatternLine().getPattern(), state);
			}
			// TODO else CommandUnit
		}
	}

	private void pushState(FromTillUnit ftu) {
		Scope scope = Components.getComponent(PatternProcessor.class)
				.findScope(ftu, state);
		if (null == scope) {
			LOG.info("[SKIP] Skipping {}, scope not found.", ftuId(ftu));
		} else {
			state.getScopes().push(scope);
			LOG.info("[PUSH] Processing {}", ftuId());
		}
	}

	private void validate(String input, Template template)
			throws ParseFailedException {
		if (null == input || input.isEmpty()) {
			produceException("Input is null or empty.");
		}
		if (null == template || null == template.getRoot()
				|| template.getRoot().getUnits().isEmpty()) {
			produceException("Template is null or empty: %s",
					String.valueOf(template));
		}
	}
}
