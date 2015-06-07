package hu.juranyi.zsolt.rte.data.template.unit;

import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;

public class SequentialUnit extends TemplateUnit {
	// TODO make it abstract, specialize
	// TODO más név kéne, operativ vagy command...

	public SequentialUnit(TemplateLine templateLine) {
		super(templateLine);
	}

	@Override
	public String toString() {
		return "SequentialUnit [toString()=" + super.toString() + "]";
	}

}
