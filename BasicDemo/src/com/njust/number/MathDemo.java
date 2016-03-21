package com.njust.number;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MathDemo {

	/**
	 * 计算1.1*n
	 * 
	 * @param num
	 * @return
	 */
	public static int calculate(final int num) {
		// return method01(num);
		// return method02(num);
		return method03(num);
	}

	/**
	 * double有精度问题
	 * <p>
	 * 这种方法遇到 100*1.1就由于精度问题失败
	 * 
	 * @param num
	 * @return
	 */
	private static int method01(final int num) {
		double result = num * 1.1;
		// System.out.println(result);

		double temp = Math.ceil(result);
		return (int) temp;
	}

	/**
	 * 这种方法在一定范围内有效
	 * 
	 * @param num
	 * @return
	 */
	private static int method02(final int num) {
		double result = num * 1.1;

		DecimalFormat format = new DecimalFormat("0.00");
		String resultStr = format.format(result);

		result = Double.parseDouble(resultStr);
		// System.out.println(result);

		double temp = Math.ceil(result);
		return (int) temp;
	}

	/**
	 * BigDecimal用double初始化，还是会因为double不精确而不精确
	 * <p>
	 * 建议用string操作
	 * 
	 * @param num
	 * @return
	 */
	private static int method03(int num) {
		BigDecimal b1 = new BigDecimal(num);
		BigDecimal b2 = new BigDecimal("1.1");
		BigDecimal b3 = b1.multiply(b2);

		double result = Math.ceil(b3.doubleValue());
		return (int) result;
		// Double d = new Double(result);
		// return d.intValue();
	}

	public static void main(String[] args) {
		test1();

		test02();
	}

	private static void test02() {
		BigDecimal b1 = new BigDecimal(1.1d);
		System.out.println(b1);
		// 1.100000000000000088817841970012523233890533447265625

		BigDecimal b2 = new BigDecimal("1.1");
		System.out.println(b2);// 1.1
	}

	private static void test1() {
		double result = 1.1 * 100;
		System.out.println(result); // 110.00000000000001
		result = 0.05 + 0.01;
		System.out.println(result);// 0.060000000000000005
	}
}
