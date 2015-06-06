package hu.juranyi.zsolt.rte;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.SimpleLogger;

public class Log {

	public enum Level {
		DEBUG, ERROR, INFO, TRACE, WARN;
	}

	public static Logger forClass(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}

	public static void setDateTimeFormat(String format) {
		System.getProperties().setProperty(SimpleLogger.DATE_TIME_FORMAT_KEY,
				format);
	}

	public static void setDefaultLogLevel(Level level) {
		System.getProperties().setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY,
				level.name().toLowerCase());
	}

	public static void setLevelInBrackets(boolean b) {
		System.getProperties().setProperty(SimpleLogger.LEVEL_IN_BRACKETS_KEY,
				Boolean.toString(b));
	}

	public static void setLogFile(String path) {
		System.getProperties().setProperty(SimpleLogger.LOG_FILE_KEY, path);
	}

	public static void setLogLevel(Class<?> clazz, Level level) {
		System.getProperties().setProperty(
				SimpleLogger.LOG_KEY_PREFIX + clazz.getName(),
				level.name().toLowerCase());
	}

	public static void setShowDateTime(boolean b) {
		System.getProperties().setProperty(SimpleLogger.SHOW_DATE_TIME_KEY,
				Boolean.toString(b));
	}

	public static void setShowLogName(boolean b) {
		System.getProperties().setProperty(SimpleLogger.SHOW_LOG_NAME_KEY,
				Boolean.toString(b));
	}

	public static void setShowShortLogName(boolean b) {
		System.getProperties().setProperty(
				SimpleLogger.SHOW_SHORT_LOG_NAME_KEY, Boolean.toString(b));
	}

	public static void setShowThreadName(boolean b) {
		System.getProperties().setProperty(SimpleLogger.SHOW_THREAD_NAME_KEY,
				Boolean.toString(b));
	}

	static {
		setDateTimeFormat("yyyy-MM-dd HH:mm:ss");
		setDefaultLogLevel(Level.TRACE);
		setLevelInBrackets(true);
		setShowDateTime(true);
		setShowShortLogName(true);
		setShowThreadName(false);
	}
}
