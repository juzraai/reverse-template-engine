package hu.juranyi.zsolt.rte.data.template.unit;

import hu.juranyi.zsolt.rte.data.template.line.PatternLine;

public class PatternUnit extends SequentialUnit {

	public PatternUnit(PatternLine templateLine) {
		super(templateLine);
	}

	public PatternLine getPatternLine() {
		return (PatternLine) super.getTemplateLine();
	}

	@Override
	public String toString() {
		return "PatternUnit [toString()=" + super.toString() + "]";
	}

}
