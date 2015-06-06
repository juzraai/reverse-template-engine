package hu.juranyi.zsolt.rte.core.interpreter;

import hu.juranyi.zsolt.rte.data.template.Template;
import hu.juranyi.zsolt.rte.data.template.line.FromLine;
import hu.juranyi.zsolt.rte.data.template.line.NoteLine;
import hu.juranyi.zsolt.rte.data.template.line.RawLine;
import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;
import hu.juranyi.zsolt.rte.data.template.line.TillLine;
import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;
import hu.juranyi.zsolt.rte.data.template.unit.SequentialUnit;

import java.util.Stack;

public class TemplateFromString extends Interpreter<String, Template> {

	private Stack<FromTillUnit> ftuStack;

	private void handleFromLine(int number, FromLine fromLine) {
		FromTillUnit ftu = new FromTillUnit();
		ftu.setFromLine(fromLine);
		ftuStack.peek().getUnits().add(ftu);
		ftuStack.push(ftu);
	}

	private void handleOtherLine(int number, TemplateLine templateLine) {
		// TODO sequential unit will b abstract due to specialization!
		SequentialUnit su = new SequentialUnit(templateLine);
		ftuStack.peek().getUnits().add(su);
	}

	private void handleTillLine(int number, TillLine tillLine)
			throws InterpretationFailedException {
		if (1 == ftuStack.size()) {
			produceException("Unexpected @TILL in line %d.", number);
		}
		ftuStack.peek().setTillLine(tillLine);
		ftuStack.pop();
	}

	private void initializeStack() {
		ftuStack = new Stack<FromTillUnit>();
		FromTillUnit ftu = new FromTillUnit();
		ftu.setFromLine(new FromLine(new RawLine(0, "@FROM"), null));
		ftu.setTillLine(new TillLine(new RawLine(0, "@TILL"), null));
		ftuStack.push(ftu);
	}

	@Override
	protected Template interpretImpl(String input)
			throws InterpretationFailedException {
		initializeStack();
		int i = 0;
		for (String line : input.split("\n")) {
			++i;
			verifyStackIsNotEmpty(i);
			interpretLine(i, line);
		}
		verifyStackIsNotEmpty(-1);
		verifyStackSizeIs1();
		return new Template(ftuStack.pop());
	}

	private void interpretLine(int number, String line)
			throws InterpretationFailedException {

		if (line.trim().isEmpty()) {
			return;
		}

		RawLine rawLine = new RawLine(number, line);
		TemplateLine templateLine = Interpreters.interpreterFor(RawLine.class,
				TemplateLine.class).interpret(rawLine);

		LOG.info("Line {} is {}", number, templateLine);

		if (null == templateLine) { // just in case
			return;
		} else if (templateLine instanceof FromLine) {
			handleFromLine(number, (FromLine) templateLine);
		} else if (templateLine instanceof TillLine) {
			handleTillLine(number, (TillLine) templateLine);
		} else if (!(templateLine instanceof NoteLine)) {
			handleOtherLine(number, templateLine);
		}
	}

	@Override
	protected String validateInput(String input)
			throws InterpretationFailedException {
		return nullCheck(input);
	}

	private void verifyStackIsNotEmpty(int number)
			throws InterpretationFailedException {
		if (null == ftuStack) {
			produceException("Implementation fail: FTU stack is not initialized.");
		}
		if (ftuStack.isEmpty()) {
			produceException(
					"Implementation fail: FTU stack is empty at line %d",
					number);
		}
	}

	private void verifyStackSizeIs1() throws InterpretationFailedException {
		if (ftuStack.size() > 1) {
			produceException("There are unclosed @FROM-@TILL blocks.");
		}
	}

}
