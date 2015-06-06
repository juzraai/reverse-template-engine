package hu.juranyi.zsolt.rte.core.interpreter;

import hu.juranyi.zsolt.rte.Log;

import org.slf4j.Logger;

public abstract class Interpreter<INPUT, OUTPUT> {

	protected final Logger LOG = Log.forClass(this.getClass());

	public final OUTPUT interpret(INPUT input)
			throws InterpretationFailedException {
		LOG.debug("received  input: {}", input);
		input = validateInput(input);
		LOG.debug("validated input: {}", input);
		OUTPUT output = interpretImpl(input);
		LOG.debug("produced output: {}", output);
		return output;
	}

	protected abstract OUTPUT interpretImpl(INPUT input)
			throws InterpretationFailedException;

	protected INPUT nullCheck(INPUT input) throws InterpretationFailedException {
		if (null == input) {
			throw new InterpretationFailedException("Input is null.");
		} else {
			return input;
		}
	}

	protected void produceException(String message, Object... parameters)
			throws InterpretationFailedException {
		String s = String.format(message, parameters);
		LOG.error(s);
		throw new InterpretationFailedException(s);
	}

	protected abstract INPUT validateInput(INPUT input)
			throws InterpretationFailedException;
}
