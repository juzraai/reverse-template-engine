package hu.juranyi.zsolt.rte.data.template.line;

import java.util.regex.Pattern;

public abstract class PatternHolderLine extends TemplateLine {

	private final Pattern pattern;

	public PatternHolderLine(RawLine rawLine, Pattern pattern) {
		super(rawLine);
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}

	@Override
	public String toString() {
		return "PatternHolderLine [pattern=" + pattern + ", toString()="
				+ super.toString() + "]";
	}
}
