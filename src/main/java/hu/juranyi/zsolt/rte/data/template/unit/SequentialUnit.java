package hu.juranyi.zsolt.rte.data.template.unit;

import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;

public abstract class SequentialUnit extends TemplateUnit {

	public SequentialUnit(TemplateLine templateLine) {
		super(templateLine);
	}

	@Override
	public String toString() {
		return "SequentialUnit [toString()=" + super.toString() + "]";
	}

}
