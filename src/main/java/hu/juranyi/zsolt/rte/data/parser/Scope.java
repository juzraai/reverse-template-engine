package hu.juranyi.zsolt.rte.data.parser;

import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;

public class Scope {

	private final FromTillUnit ftu;
	private final int start;
	private final int end;
	private int nextUnit = 0;

	public Scope(FromTillUnit ftu, int start, int end) {
		this.ftu = ftu;
		this.start = start;
		this.end = end;
	}

	public int getEnd() {
		return end;
	}

	public FromTillUnit getFtu() {
		return ftu;
	}

	public int getNextUnit() {
		return nextUnit;
	}

	public int getStart() {
		return start;
	}

	public void setNextUnit(int nextUnit) {
		this.nextUnit = nextUnit;
	}

	@Override
	public String toString() {
		return "NewScope [ftu=" + ftu + ", start=" + start + ", end=" + end
				+ ", nextUnit=" + nextUnit + "]";
	}

}
