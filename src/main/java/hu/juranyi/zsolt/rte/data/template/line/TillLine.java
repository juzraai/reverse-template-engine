package hu.juranyi.zsolt.rte.data.template.line;

import java.util.regex.Pattern;

public class TillLine extends PatternHolderLine {

	public TillLine(RawLine rawLine, Pattern pattern) {
		super(rawLine, pattern);
	}

	@Override
	public String toString() {
		return "TillLine [toString()=" + super.toString() + "]";
	}

}
