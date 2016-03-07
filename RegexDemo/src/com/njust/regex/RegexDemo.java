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
		// ���ú��Դ�Сд
		Pattern pattern = Pattern.compile("a*b", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("aB");
		System.out.println(matcher.matches());// true
	}

	/**
	 * Matcher.find()
	 * <p>
	 * ���ǰһ���ҵ���ģʽƥ���������,����δ���������к�ʼ����
	 */
	private static void test02() {
		Pattern pattern = Pattern.compile("a\\d*z");
		Matcher matcher = pattern.matcher("a1234z,a9999z");
		System.out.println(matcher.find());// true
		System.out.println(matcher.find());// true
		System.out.println(matcher.find());// false
	}

	/**
	 * ��ĸ���
	 * <p>
	 * group()������ǰһ��ƥ���������find()���ĵ�0��
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
	 * ��ĸ���
	 * <p>
	 * ����������Ҫ�����������Ż��ֵ�������ʽ������ͨ������������顣
	 * <p>
	 * ��Ŵ�0��ʼ���м���С���žͱ�ʾ�м����飬���������Ƕ�ף� ���Ϊ0�ı�ʾ�������ʽ�����Ϊ1�ı�ʾ��һ���飬�������ơ�
	 * <p>
	 * ���磺A(B)C(D)E����ʽ�������飬��0��ABCDE����1��B����2��D��
	 * <p>
	 * A((B)C)(D)E����ʽ�������飺��0��ABCDE����1��BC����2��B����3��C����4��D��
	 * 
	 * group(i)������ǰһ��ƥ���������find()���ĵ�i��
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
