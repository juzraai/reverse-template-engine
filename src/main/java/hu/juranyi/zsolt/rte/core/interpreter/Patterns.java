package hu.juranyi.zsolt.rte.core.interpreter;

import java.util.regex.Pattern;

public class Patterns {

	public static Pattern NOTE_LINE_PATTERN = Pattern.compile("^@NOTE.*",
			Pattern.CASE_INSENSITIVE);

	public static String PATTERN_GROUP_ID = "p";

	public static Pattern PATTERN_LINE_PATTERN = Pattern.compile("^\\/(?<"
			+ PATTERN_GROUP_ID + ">.*)\\/$");

	public static Pattern FROM_LINE_PATTERN = Pattern.compile(
			"^@FROM(\\s\\/(?<" + PATTERN_GROUP_ID + ">.+)\\/)?$",
			Pattern.CASE_INSENSITIVE);

	public static Pattern TILL_LINE_PATTERN = Pattern.compile(
			"^@TILL(\\s\\/(?<" + PATTERN_GROUP_ID + ">.+)\\/)?$",
			Pattern.CASE_INSENSITIVE);

}
