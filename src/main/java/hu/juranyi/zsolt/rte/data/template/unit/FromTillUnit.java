package hu.juranyi.zsolt.rte.data.template.unit;

import hu.juranyi.zsolt.rte.data.template.line.FromLine;
import hu.juranyi.zsolt.rte.data.template.line.TillLine;

import java.util.ArrayList;
import java.util.List;

public class FromTillUnit extends TemplateUnit {

	private FromLine fromLine;
	private TillLine tillLine;
	private final List<TemplateUnit> units = new ArrayList<TemplateUnit>();

	public FromLine getFromLine() {
		return fromLine;
	}

	public TillLine getTillLine() {
		return tillLine;
	}

	public List<TemplateUnit> getUnits() {
		return units;
	}

	public void setFromLine(FromLine fromLine) {
		this.fromLine = fromLine;
	}

	public void setTillLine(TillLine tillLine) {
		this.tillLine = tillLine;
	}

	@Override
	public String toString() {
		return "FromTillUnit [fromLine=" + fromLine + ", tillLine=" + tillLine
				+ ", units=" + units + ", toString()=" + super.toString() + "]";
	}

}
