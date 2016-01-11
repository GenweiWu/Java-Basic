package com.njust.demo1;

import com.njust.demo1.CustomAnnotation.ColorEnum;

public class TestAnnotation
{
	@CustomAnnotation
	public void printDefault()
	{

	}

	@CustomAnnotation(name = "张三", age = 25, hobbies = { "跑步", "看电影" }, favoriteColor = ColorEnum.Green)
	public void defaultRandom()
	{

	}
}
