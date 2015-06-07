package hu.juranyi.zsolt.rte.core.parser;

import hu.juranyi.zsolt.rte.data.parser.Scope;
import hu.juranyi.zsolt.rte.data.parser.State;
import hu.juranyi.zsolt.rte.data.template.Template;
import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;
import hu.juranyi.zsolt.rte.data.template.unit.TemplateUnit;

import java.util.List;
import java.util.Stack;

public class Parser extends Component {

	// TODO variable storages

	// TODO ??? merge State POJO > Scope POJO, State = scopes + vars
	private final Stack<State> states = new Stack<State>();

	private String ftuId() {
		return ftuId(states.peek().getFtu());
	}

	private String ftuId(FromTillUnit ftu) {
		return ftu.getId();
	}

	public void parse(String input, Template template)
			throws ParseFailedException {
		validate(input, template);
		// TODO figure out return type
		// TODO initialize variable storages
		states.clear();
		pushState(template.getRoot(), input);
		while (!states.isEmpty()) {
			processCurrentState(input);
		}
		// TODO produce output
	}

	private void processCurrentState(String input) {
		State state = states.peek();
		List<TemplateUnit> units = state.getFtu().getUnits();
		int next = state.getNextUnitIndex();
		if (next >= units.size()) {
			LOG.info("[POP] Processing completed of {}", ftuId());
			states.pop();
			if (states.isEmpty()) {
				LOG.info("Parsing completed.");
			} else {
				LOG.info("[PEEK] Processing {}", ftuId());
			}
		} else {
			processCurrentUnit(units.get(next), input);
			state.setNextUnitIndex(++next);
		}
	}

	private void processCurrentUnit(TemplateUnit templateUnit, String input) {
		if (templateUnit instanceof FromTillUnit) {
			pushState((FromTillUnit) templateUnit, input);
		} else {
			LOG.info("Processing {} ({})", templateUnit.getId(), ftuId());
			// TODO else process unit (command, pattern)
		}
	}

	private void pushState(FromTillUnit ftu, String input) {
		// XXX variable insertion into ftu.from.pattern should be here
		ScopeFinder sf = Components.getComponent(ScopeFinder.class);
		Scope scope = sf.find(null, ftu, input);
		if (null == scope) {
			LOG.info("[SKIP] Skipping {}, scope not found.", ftuId(ftu));
		} else {
			State state = new State(ftu, scope);
			states.push(state);
			LOG.info("[PUSH] Processing {}", ftuId());
			// XXX extracting values from header can be here
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
