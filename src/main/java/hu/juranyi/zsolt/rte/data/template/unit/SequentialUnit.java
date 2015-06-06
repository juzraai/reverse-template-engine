package hu.juranyi.zsolt.rte.data.template.unit;

import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;

public class SequentialUnit extends TemplateUnit {
	// TODO make it abstract, specialize
	// TODO más név kéne, operativ vagy command...

	private final TemplateLine templateLine;

	public SequentialUnit(TemplateLine templateLine) {
		this.templateLine = templateLine;
	}

	public TemplateLine getTemplateLine() {
		return templateLine;
	}

	@Override
	public String toString() {
		return "SequentialUnit [templateLine=" + templateLine + ", toString()="
				+ super.toString() + "]";
	}
}
