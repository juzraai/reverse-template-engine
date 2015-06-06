package hu.juranyi.zsolt.rte.data.template.line;

import java.util.regex.Pattern;

public class PatternLine extends PatternHolderLine {

	public PatternLine(RawLine rawLine, Pattern pattern) {
		super(rawLine, pattern);
	}

	@Override
	public String toString() {
		return "PatternLine [toString()=" + super.toString() + "]";
	}

}
