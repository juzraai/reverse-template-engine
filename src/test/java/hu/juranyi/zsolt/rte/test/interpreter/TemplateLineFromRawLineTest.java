package hu.juranyi.zsolt.rte.test.interpreter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hu.juranyi.zsolt.rte.core.interpreter.InterpretationFailedException;
import hu.juranyi.zsolt.rte.core.interpreter.Interpreter;
import hu.juranyi.zsolt.rte.core.interpreter.Interpreters;
import hu.juranyi.zsolt.rte.data.template.line.FromLine;
import hu.juranyi.zsolt.rte.data.template.line.NoteLine;
import hu.juranyi.zsolt.rte.data.template.line.PatternHolderLine;
import hu.juranyi.zsolt.rte.data.template.line.PatternLine;
import hu.juranyi.zsolt.rte.data.template.line.RawLine;
import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;
import hu.juranyi.zsolt.rte.data.template.line.TillLine;

import org.junit.Test;

public class TemplateLineFromRawLineTest {

	private static TemplateLine i(String input)
			throws InterpretationFailedException {
		return ir.interpret(new RawLine(1, input));
	}

	private static Interpreter<RawLine, TemplateLine> ir = Interpreters
			.interpreterFor(RawLine.class, TemplateLine.class);

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForEmptyFromPattern()
			throws InterpretationFailedException {
		i("@FROM //");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForEmptyInput() throws InterpretationFailedException {
		i("");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForEmptyPattern() throws InterpretationFailedException {
		i("//");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForEmptyTillPattern()
			throws InterpretationFailedException {
		i("@TILL //");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForInvalidFromPattern()
			throws InterpretationFailedException {
		i("@FROM /{/");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForInvalidPattern()
			throws InterpretationFailedException {
		i("/{/");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForInvalidTillPattern()
			throws InterpretationFailedException {
		i("@TILL /{/");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForMultilineInput()
			throws InterpretationFailedException {
		i("@NOTE\n@NOTE");
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForNullInput() throws InterpretationFailedException {
		ir.interpret(null);
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForNullLine() throws InterpretationFailedException {
		i(null);
	}

	@Test(expected = InterpretationFailedException.class)
	public void exceptionForUnrecognizedLine()
			throws InterpretationFailedException {
		i("Unknown line format");
	}

	@Test
	public void getBackRawLine() throws InterpretationFailedException {
		RawLine r = new RawLine(1, "@NOTE");
		RawLine g = ir.interpret(r).getRawLine();
		assertTrue(g == r);
	}

	@Test
	public void recognizeFromLineWithoutPattern()
			throws InterpretationFailedException {
		TemplateLine tl = i("@FROM");
		assertTrue(tl instanceof FromLine);
	}

	@Test
	public void recognizeFromLineWithPattern()
			throws InterpretationFailedException {
		TemplateLine tl = i("@FROM /pattern/");
		assertTrue(tl instanceof FromLine);
		assertEquals("pattern", ((PatternHolderLine) tl).getPattern()
				.toString());
	}

	@Test
	public void recognizeNoteLine() throws InterpretationFailedException {
		TemplateLine tl = i("@NOTE");
		assertTrue(tl instanceof NoteLine);
	}

	@Test
	public void recognizePatternLine() throws InterpretationFailedException {
		TemplateLine tl = i("/pattern/");
		assertTrue(tl instanceof PatternLine);
		assertEquals("pattern", ((PatternLine) tl).getPattern().toString());
	}

	@Test
	public void recognizeTillLineWithoutPattern()
			throws InterpretationFailedException {
		TemplateLine tl = i("@TILL");
		assertTrue(tl instanceof TillLine);
	}

	@Test
	public void recognizeTillLineWithPattern()
			throws InterpretationFailedException {
		TemplateLine tl = i("@TILL /pattern/");
		assertTrue(tl instanceof TillLine);
		assertEquals("pattern", ((PatternHolderLine) tl).getPattern()
				.toString());
	}

	@Test
	public void trimInput() throws InterpretationFailedException {
		TemplateLine tl = i("\t @FROM \t ");
		assertTrue(tl instanceof FromLine);
	}

}
