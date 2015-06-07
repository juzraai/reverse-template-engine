package hu.juranyi.zsolt.rte.core.parser;

import hu.juranyi.zsolt.rte.util.Log;

import org.slf4j.Logger;

public abstract class Component {

	protected final Logger LOG = Log.forClass(this.getClass());

	protected void produceException(String message, Object... parameters)
			throws ParseFailedException {
		String s = String.format(message, parameters);
		LOG.error(s);
		throw new ParseFailedException(s);
	}

}
