package com.njust.number;

import junit.framework.Assert;

import org.junit.Test;

public class MathDemoTest {

	@Test
	public void testCalculate_S01() {
		Assert.assertEquals(2, MathDemo.calculate(1));
		Assert.assertEquals(3, MathDemo.calculate(2));
		Assert.assertEquals(4, MathDemo.calculate(3));
		Assert.assertEquals(5, MathDemo.calculate(4));
		Assert.assertEquals(6, MathDemo.calculate(5));
		Assert.assertEquals(7, MathDemo.calculate(6));
		Assert.assertEquals(8, MathDemo.calculate(7));
		Assert.assertEquals(9, MathDemo.calculate(8));
		Assert.assertEquals(10, MathDemo.calculate(9));
		Assert.assertEquals(11, MathDemo.calculate(10));
	}

	@Test
	public void testCalculate_S02() {
		Assert.assertEquals(13, MathDemo.calculate(11));
		Assert.assertEquals(14, MathDemo.calculate(12));
		Assert.assertEquals(15, MathDemo.calculate(13));
		Assert.assertEquals(16, MathDemo.calculate(14));
		Assert.assertEquals(17, MathDemo.calculate(15));
		Assert.assertEquals(18, MathDemo.calculate(16));
		Assert.assertEquals(19, MathDemo.calculate(17));
		Assert.assertEquals(20, MathDemo.calculate(18));
		Assert.assertEquals(21, MathDemo.calculate(19));
		Assert.assertEquals(22, MathDemo.calculate(20));
	}

	@Test
	public void testCalculate_S03() {
		Assert.assertEquals(110, MathDemo.calculate(100));
		Assert.assertEquals(112, MathDemo.calculate(101));
		Assert.assertEquals(113, MathDemo.calculate(102));
		Assert.assertEquals(114, MathDemo.calculate(103));
		Assert.assertEquals(115, MathDemo.calculate(104));
	}

	@Test
	public void testCalculate_S03_F01() {
		Assert.assertEquals(110, MathDemo.calculate(100));
	}

	@Test
	public void testCalculate_S04() {
		Assert.assertEquals(1100000000, MathDemo.calculate(1000000000));
	}
}
