package com.njust.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式之特殊字符的转义
 * <p>
 * 请匹配以下特殊字符： <code>
 * ~`!@#$%^&*()-=_+[]{};:'"\|,.<>/?
 * </code>
 * 
 * @author WGW
 * 
 */
public class RegexSpecial {

	private static final String REGEX = "[~`!@#\\$%\\^&\\*\\(\\)\\-=_\\+\\[\\]\\{\\};:\'\"\\\\|,\\.<>\\/\\?]+";

	public static boolean matchSpecial(String str) {
		Pattern pattern = Pattern.compile(REGEX);
		Matcher matcher = pattern.matcher(str);
		return str != null && matcher.matches();
	}
}
