package com.njust.regex;

import org.junit.Assert;
import org.junit.Test;

public class RegexSpecialTest {

	private static final String SPECIAL = "~`!@#$%^&*()-=_+[]{};:\'\"\\|,.<>/?";

	@Test
	public void testS_01() {
		boolean actual = RegexSpecial.matchSpecial(SPECIAL);
		Assert.assertEquals(true, actual);
	}

	@Test
	public void testS_02() {
		for (char charcter : SPECIAL.toCharArray()) {
			boolean actual = RegexSpecial.matchSpecial("" + charcter);
			Assert.assertEquals(true, actual);
		}

	}
}
