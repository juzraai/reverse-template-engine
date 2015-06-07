package hu.juranyi.zsolt.rte.data.parser;

public class Scope {

	private final int start;
	private final int end;

	public Scope(int start, int end) {
		this.start = start;
		this.end = end;
	}

	public int getEnd() {
		return end;
	}

	public int getStart() {
		return start;
	}

	@Override
	public String toString() {
		return "Scope [start=" + start + ", end=" + end + "]";
	}

}
