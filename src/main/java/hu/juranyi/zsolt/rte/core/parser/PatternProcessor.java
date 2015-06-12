package hu.juranyi.zsolt.rte.core.parser;

import hu.juranyi.zsolt.rte.data.parser.Scope;
import hu.juranyi.zsolt.rte.data.parser.State;
import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;

import java.util.Set;
import java.util.TreeSet;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternProcessor extends Component {

	public Set<String> extractGroupNames(String regex) {
		// http://stackoverflow.com/questions/15588903/get-group-names-in-java-regex
		Set<String> namedGroups = new TreeSet<String>();
		Matcher m = Pattern.compile("\\(\\?<([a-zA-Z][a-zA-Z0-9]*)>").matcher(
				regex);
		while (m.find()) {
			namedGroups.add(m.group(1));
		}
		return namedGroups;
	}

	protected void extractVariables(Matcher matcher, Pattern pattern,
			State state) {
		Set<String> groups = extractGroupNames(pattern.pattern());
		for (String group : groups) {
			String value = matcher.group(group);
			if (null != value) {
				LOG.info("Value mapped: '{}' = '{}'", group, value);
				state.getInnerVariables().put(group, value);
			}
		}
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

	protected Pattern injectVariables(Pattern pattern, State state) {
		String p = pattern.pattern();
		Pattern groupPattern = Pattern
				.compile("\\(\\((?<v>[a-zA-Z][a-zA-Z0-9]*)\\)\\)");
		Matcher m = groupPattern.matcher(p);
		while (m.find()) {
			String group = m.group("v");
			String value = state.getInnerVariables().get(group);
			value = null == value ? "" : value;
			p = p.substring(0, m.start()) + value
					+ p.substring(m.end(), p.length());
			LOG.debug("Injecting variable '{}' = '{}', new pattern: /{}/",
					group, value, p);
		}
		return Pattern.compile(p);
	}

	public MatchResult processPattern(Pattern pattern, State state) {
		if (null == pattern || null == state || null == state.getInput()
				|| state.getInput().isEmpty()) {
			LOG.debug("Some input is null or missing.");
			return null;
		}
		pattern = injectVariables(pattern, state);
		Scope scope = state.getScopes().peek();
		Matcher matcher = pattern.matcher(state.getInput());
		matcher.region(scope.getStart(), scope.getEnd());
		if (matcher.find()) {
			MatchResult result = matcher.toMatchResult();
			LOG.info("Pattern /{}/ found at position {}..{}", pattern,
					matcher.start(), matcher.end());
			extractVariables(matcher, pattern, state);
			return result;
		} else {
			LOG.info("Pattern /{}/ not found.", pattern);
			return null;
		}
	}
}
