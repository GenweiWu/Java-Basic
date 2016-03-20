package com.njust.regex;

/**
 * 中划线的匹配
 * <p>
 * 总结：在[]中的-需要进行转义
 */
public class RegexDemo2 {
	public static void main(String[] args) {
		// 假设我们要匹配0，9，以及-（中划线）

		String test = "0-9";
		System.out.println(test.matches("[0-9]+"));// false

		// 因为在[]中，-有特殊意义

		// 方法1：调整-的位置
		System.out.println(test.matches("[-09]+"));// true

		// 方法1：对-进行转义
		System.out.println(test.matches("[0\\-9]+"));// true
	}
}
