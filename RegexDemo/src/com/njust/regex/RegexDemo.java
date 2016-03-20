package com.njust.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
	public static void main(String[] args) {
		test01();
		test02();
		test03();
		test04();
	}

	/**
	 * Matcher.matchers
	 */
	private static void test01() {
		// 设置忽略大小写
		Pattern pattern = Pattern.compile("a*b", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("aB");
		System.out.println(matcher.matches());// true
	}

	/**
	 * Matcher.find()
	 * <p>
	 * 如果前一次找到与模式匹配的子序列,则这次从这个子序列后开始查找
	 */
	private static void test02() {
		Pattern pattern = Pattern.compile("a\\d*z");
		Matcher matcher = pattern.matcher("a1234z,a9999z");
		System.out.println(matcher.find());// true
		System.out.println(matcher.find());// true
		System.out.println(matcher.find());// false
	}

	/**
	 * 组的概念
	 * <p>
	 * group()：返回前一次匹配操作（如find()）的第0组
	 */
	private static void test03() {
		Pattern pattern = Pattern.compile("a\\d*z");
		Matcher matcher = pattern.matcher("a1234z,a9999z");

		while (matcher.find()) {
			System.out.println(matcher.group());
		}
		// a1234z
		// a9999z
	}

	/**
	 * 组的概念
	 * <p>
	 * 这个概念很重要，组是用括号划分的正则表达式，可以通过编号来引用组。
	 * <p>
	 * 组号从0开始，有几对小括号就表示有几个组，并且组可以嵌套， 组号为0的表示整个表达式，组号为1的表示第一个组，依此类推。
	 * <p>
	 * 例如：A(B)C(D)E正则式中有三组，组0是ABCDE，组1是B，组2是D；
	 * <p>
	 * A((B)C)(D)E正则式中有四组：组0是ABCDE，组1是BC，组2是B；组3是C，组4是D。
	 * 
	 * group(i)：返回前一次匹配操作（如find()）的第i组
	 */
	private static void test04() {
		Pattern pattern = Pattern.compile("(a)\\d*(z)");
		Matcher matcher = pattern.matcher("a1234z,a9999z");

		while (matcher.find()) {
			System.out.println(matcher.groupCount());
			StringBuilder sb = new StringBuilder();
			sb.append(matcher.group(0)).append(" ~ ");
			sb.append(matcher.group(1)).append(" ~ ");
			sb.append(matcher.group(2));
			System.out.println(sb);
		}
		// 2
		// a1234z ~ a ~ z
		// 2
		// a9999z ~ a ~ z
	}
}
