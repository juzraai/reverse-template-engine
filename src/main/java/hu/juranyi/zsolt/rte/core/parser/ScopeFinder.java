package hu.juranyi.zsolt.rte.core.parser;

import hu.juranyi.zsolt.rte.data.parser.Scope;
import hu.juranyi.zsolt.rte.data.template.unit.FromTillUnit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScopeFinder extends Component {

	public Scope find(Scope parentScope, FromTillUnit fromTillUnit, String input) {
		if (null == parentScope) {
			parentScope = new Scope(0, input.length());
		}

		int start = parentScope.getStart();
		int end = parentScope.getEnd();

		Pattern from = fromTillUnit.getFromLine().getPattern();
		if (null != from) {
			Matcher m = from.matcher(input);
			m.region(start, end);
			if (m.find()) {
				start = m.start();
			} else {
				LOG.info("FROM pattern not found in input for unit {}",
						fromTillUnit);
				return null;
			}
		}

		Pattern till = fromTillUnit.getTillLine().getPattern();
		if (null != till) {
			Matcher m = till.matcher(input);
			m.region(start, end);
			if (m.find()) {
				end = m.start();
				// XXX TILL line is exclusive, but it could be a setting in tpl
			} else {
				LOG.info("TILL pattern not found in input for unit {}",
						fromTillUnit);
				return null;
			}
		}
		LOG.info("Found scope: {} .. {}", start, end);
		return new Scope(start, end);
	}
}