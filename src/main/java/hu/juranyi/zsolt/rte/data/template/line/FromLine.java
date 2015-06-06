package hu.juranyi.zsolt.rte.data.template.line;

import java.util.regex.Pattern;

public class FromLine extends PatternHolderLine {

	public FromLine(RawLine rawLine, Pattern pattern) {
		super(rawLine, pattern);
	}

	@Override
	public String toString() {
		return "FromLine [toString()=" + super.toString() + "]";
	}

}
