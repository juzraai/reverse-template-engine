package hu.juranyi.zsolt.rte.data.template.line;

public class RawLine {

	private final int number;
	private final String line;

	public RawLine(int number, String line) {
		this.number = number;
		this.line = line;
	}

	public String getLine() {
		return line;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "RawLine [number=" + number + ", line=" + line + "]";
	}

}
