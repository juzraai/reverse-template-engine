package hu.juranyi.zsolt.rte.data.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class State {

	private final String input;
	private final Stack<Scope> scopes = new Stack<Scope>();
	private final Map<String, String> innerVariables = new HashMap<String, String>();

	// XXX do we need <String,Object> ? for casts?

	// TODO output variables (initialize here)

	public State(String input) {
		this.input = input;
	}

	public Map<String, String> getInnerVariables() {
		return innerVariables;
	}

	public String getInput() {
		return input;
	}

	public Stack<Scope> getScopes() {
		return scopes;
	}

}
