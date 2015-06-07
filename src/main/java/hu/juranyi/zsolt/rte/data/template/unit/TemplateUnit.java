package hu.juranyi.zsolt.rte.data.template.unit;

import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;

public abstract class TemplateUnit {
	private final TemplateLine templateLine;
	private final String id;

	public TemplateUnit(TemplateLine templateLine) {
		this.templateLine = templateLine;
		this.id = this.getClass().getSimpleName() + " @ line "
				+ templateLine.getRawLine().getNumber();
	}

	public String getId() {
		return id;
	}

	public TemplateLine getTemplateLine() {
		return templateLine;
	}

	@Override
	public String toString() {
		return "TemplateUnit [templateLine=" + templateLine + ", id=" + id
				+ "]";
	}

}
