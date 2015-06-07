package hu.juranyi.zsolt.rte.core.interpreter;

import hu.juranyi.zsolt.rte.data.template.Template;
import hu.juranyi.zsolt.rte.data.template.line.RawLine;
import hu.juranyi.zsolt.rte.data.template.line.TemplateLine;

import java.util.HashMap;
import java.util.Map;

public class Interpreters {

	private static class Key {
		private final Class<?> i, o;

		public Key(Class<?> i, Class<?> o) {
			this.i = i;
			this.o = o;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Key other = (Key) obj;
			if (i == null) {
				if (other.i != null)
					return false;
			} else if (!i.equals(other.i))
				return false;
			if (o == null) {
				if (other.o != null)
					return false;
			} else if (!o.equals(other.o))
				return false;
			return true;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((i == null) ? 0 : i.hashCode());
			result = prime * result + ((o == null) ? 0 : o.hashCode());
			return result;
		}
	}

	@SuppressWarnings("unchecked")
	// type safety provided by register method
	public static <I, O> Interpreter<I, O> interpreterFor(Class<I> inputType,
			Class<O> outputType) {
		Interpreter<?, ?> i = pool.get(new Key(inputType, outputType));
		return null == i ? null : (Interpreter<I, O>) i;
	}

	public static <I, O> void registerInterpreter(Class<I> inputType,
			Class<O> outputType, Interpreter<I, O> interpreter) {
		pool.put(new Key(inputType, outputType), interpreter);
	}

	private static final Map<Key, Interpreter<?, ?>> pool = new HashMap<Key, Interpreter<?, ?>>();

	static {
		registerInterpreter(String.class, Template.class,
				new TemplateFromString());
		registerInterpreter(RawLine.class, TemplateLine.class,
				new TemplateLineFromRawLine());
	}
}
