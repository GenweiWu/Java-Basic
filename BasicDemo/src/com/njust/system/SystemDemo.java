package com.njust.system;

public class SystemDemo {

	public static void main(String[] args) {

		test03();
	}

	private static void test03() {
		System.out.println(System.out);
		System.out.println(System.err);

	}

	private static void test02() {
		System.setErr(System.out);
		// System.setOut(System.err);
		for (int i = 0; i < 5; i++) {
			System.out.println("+++++");
			System.err.println("-----");
		}
	}

	private static void test01() {

		for (int i = 0; i < 5; i++) {
			System.out.println("+++++");
			// System.out.flush();
			System.err.println("-----");
		}
	}
	// +++++
	// +++++
	// +++++
	// -----
	// -----
	// -----
	// -----
	// -----
	// +++++
	// +++++

}
