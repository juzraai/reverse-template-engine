package hu.juranyi.zsolt.rte.data.parser;

import java.util.Stack;

public class State {

	private final String input;
	private final Stack<Scope> scopes = new Stack<Scope>();

	// TODO inner & output variables (initialize here)

	public State(String input) {
		this.input = input;
	}

	public String getInput() {
		return input;
	}

	public Stack<Scope> getScopes() {
		return scopes;
	}

}
