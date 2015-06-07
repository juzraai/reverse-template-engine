package hu.juranyi.zsolt.rte.core.parser;

import java.util.HashMap;
import java.util.Map;

public class Components {

	public static <C extends Component> C getComponent(Class<C> c) {
		return c.cast(m.get(c));
	}

	public static <C extends Component> void registerComponent(Class<C> type,
			C instance) {
		m.put(type, instance);
	}

	private static final Map<Class<? extends Component>, Component> m = new HashMap<Class<? extends Component>, Component>();

	static {
		registerComponent(Parser.class, new Parser());
		registerComponent(ScopeFinder.class, new ScopeFinder());
	}
}
