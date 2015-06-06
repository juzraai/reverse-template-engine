package hu.juranyi.zsolt.rte.data.template;

import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;

public class Template {

	private final FromTillUnit root;

	public Template(FromTillUnit root) {
		this.root = root;
	}

	public FromTillUnit getRoot() {
		return root;
	}

	@Override
	public String toString() {
		return "Template [root=" + root + "]";
	}

}
