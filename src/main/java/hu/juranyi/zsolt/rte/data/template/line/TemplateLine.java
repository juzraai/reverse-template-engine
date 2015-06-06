package hu.juranyi.zsolt.rte.data.template.line;

public class TemplateLine {

	private final RawLine rawLine;
	private final String lineType = this.getClass().getSimpleName();

	public TemplateLine(RawLine rawLine) {
		this.rawLine = rawLine;
	}

	public String getLineType() {
		return lineType;
	}

	public RawLine getRawLine() {
		return rawLine;
	}

	@Override
	public String toString() {
		return "TemplateLine [rawLine=" + rawLine + ", lineType=" + lineType
				+ "]";
	}

}
