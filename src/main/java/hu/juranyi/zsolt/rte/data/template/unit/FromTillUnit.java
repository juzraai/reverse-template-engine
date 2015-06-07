package hu.juranyi.zsolt.rte.data.template.unit;

import hu.juranyi.zsolt.rte.data.template.line.FromLine;
import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;
import hu.juranyi.zsolt.rte.data.template.line.TillLine;

import java.util.ArrayList;
import java.util.List;

public class FromTillUnit extends TemplateUnit {

	private TillLine tillLine;
	private final List<TemplateUnit> units = new ArrayList<TemplateUnit>();

	public FromTillUnit(TemplateLine templateLine) {
		super(templateLine);
	}

	public FromLine getFromLine() {
		return (FromLine) super.getTemplateLine();
	}

	public TillLine getTillLine() {
		return tillLine;
	}

	public List<TemplateUnit> getUnits() {
		return units;
	}

	public void setTillLine(TillLine tillLine) {
		this.tillLine = tillLine;
	}

	@Override
	public String toString() {
		return "FromTillUnit [tillLine=" + tillLine + ", units=" + units
				+ ", toString()=" + super.toString() + "]";
	}

}
