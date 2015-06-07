package hu.juranyi.zsolt.rte.data.parser;

import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;

public class State {

	private final FromTillUnit ftu;
	private final Scope scope;
	private int nextUnitIndex = 0;

	public State(FromTillUnit ftu, Scope scope) {
		this.ftu = ftu;
		this.scope = scope;
	}

	public FromTillUnit getFtu() {
		return ftu;
	}

	public int getNextUnitIndex() {
		return nextUnitIndex;
	}

	public void setNextUnitIndex(int nextUnitIndex) {
		this.nextUnitIndex = nextUnitIndex;
	}

	@Override
	public String toString() {
		return "State [ftu=" + ftu + ", scope=" + scope + ", nextUnitIndex="
				+ nextUnitIndex + "]";
	}

}
