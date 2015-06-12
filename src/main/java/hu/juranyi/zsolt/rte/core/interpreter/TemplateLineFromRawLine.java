package hu.juranyi.zsolt.rte.core.interpreter;

import hu.juranyi.zsolt.rte.data.template.line.FromLine;
import hu.juranyi.zsolt.rte.data.template.line.NoteLine;
import hu.juranyi.zsolt.rte.data.template.line.PatternHolderLine;
import hu.juranyi.zsolt.rte.data.template.line.PatternLine;
import hu.juranyi.zsolt.rte.data.template.line.RawLine;
import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;
import hu.juranyi.zsolt.rte.data.template.line.TillLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateLineFromRawLine extends Interpreter<RawLine, TemplateLine> {

	// TODO test further line types
	@Override
	protected TemplateLine interpretImpl(RawLine input)
			throws InterpretationFailedException {
		String line = input.getLine().trim();

		PatternHolderLine patternLine = patternHolderLine(line,
				Patterns.PATTERN_LINE_PATTERN, PatternLine.class, input);
		if (null != patternLine) {
			return patternLine;
		}

		PatternHolderLine fromLine = patternHolderLine(line,
				Patterns.FROM_LINE_PATTERN, FromLine.class, input);
		if (null != fromLine) {
			return fromLine;
		}

		PatternHolderLine tillLine = patternHolderLine(line,
				Patterns.TILL_LINE_PATTERN, TillLine.class, input);
		if (null != tillLine) {
			return tillLine;
		}

		if (Patterns.NOTE_LINE_PATTERN.matcher(line).matches()) {
			return new NoteLine(input);
		}

		produceException("Unrecognized line format in line %d.",
				input.getNumber());
		return null;
	}

	protected PatternHolderLine patternHolderLine(String line,
			Pattern fromOrTillPattern,
			Class<? extends PatternHolderLine> fromOrTillClass, RawLine input)
			throws InterpretationFailedException {
		Matcher m = fromOrTillPattern.matcher(line);
		if (m.find()) {
			String p = m.group(Patterns.PATTERN_GROUP_ID);
			Pattern pattern = null;
			if (null != p) {
				if (p.trim().isEmpty()) {
					produceException("Pattern is empty in line %d.",
							input.getNumber());
				}
				try {
					pattern = Pattern.compile(p);
				} catch (Exception e) {
					produceException("Pattern is invalid in line %d. %s",
							input.getNumber(), e.getMessage());
				}
			}
			try {
				return fromOrTillClass.getConstructor(RawLine.class,
						Pattern.class).newInstance(input, pattern);
			} catch (Exception e) {
				produceException("Implementation fail: %s", e.getMessage());
				return null;
			}
		} else {
			return null;
		}
	}

	@Override
	protected RawLine validateInput(RawLine input)
			throws InterpretationFailedException {
		if (null == input || null == input.getLine()
				|| input.getLine().trim().isEmpty()) {
			produceException("Input is null or empty: %s",
					String.valueOf(input));
		} else if (input.getLine().contains("\n")) {
			produceException("Input is multiline.");
		}
		return input;
	}

}
