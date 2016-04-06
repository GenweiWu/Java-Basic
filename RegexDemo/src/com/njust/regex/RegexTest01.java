package com.njust.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest01 {

	public static void main(String[] args) {
		String source = "1aaa21bbb21ccc2";

		Pattern pattern = Pattern.compile("1(?<name>[a-z]*)2");
		Matcher matcher = pattern.matcher(source);
		source = matcher.replaceAll("项目${name}");

		System.out.println(source);
		//项目aaa项目bbb项目ccc
	}
}
