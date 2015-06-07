package hu.juranyi.zsolt.rte.core.parser;

import hu.juranyi.zsolt.rte.data.parser.Scope;
import hu.juranyi.zsolt.rte.data.parser.State;
import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;

import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternProcessor extends Component {

	private void extractVariables(MatchResult result, State state) {
		// TODO xtract vars
	}

	public Scope findScope(FromTillUnit ftu, State state) {

		MatchResult from = processPattern(ftu.getFromLine().getPattern(), state);
		if (null == from) {
			LOG.info("No match for FROM of {}", ftu.getId());
			return null;
		}

		MatchResult till = processPattern(ftu.getTillLine().getPattern(), state);
		if (null == till) {
			LOG.info("No match for TILL of {}", ftu.getId());
			return null;
		}

		Scope scope = new Scope(ftu, from.start(), till.start());
		// XXX TILL line is exclusive, but it could be a setting in tpl
		LOG.info("Scope for {} is {}..{}", ftu.getId(), scope.getStart(),
				scope.getEnd());
		return scope;

	}

	public MatchResult processPattern(Pattern pattern, State state) {
		if (null == pattern || null == state.getInput() || null == state
				|| state.getInput().isEmpty()) {
			LOG.debug("Some input is null or missing.");
			return null;
		}
		// XXX insert variables into pattern
		Scope scope = state.getScopes().peek();
		Matcher matcher = pattern.matcher(state.getInput());
		matcher.region(scope.getStart(), scope.getEnd());
		if (matcher.find()) {
			MatchResult result = matcher.toMatchResult();
			LOG.info("Pattern /{}/ found at position {}", pattern,
					matcher.start());
			extractVariables(result, state);
			return result;
		} else {
			LOG.info("Pattern /{}/ not found.", pattern);
			return null;
		}
	}
}
