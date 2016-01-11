package com.njust.demo1;

import java.lang.reflect.Method;
import java.util.Arrays;

import com.njust.demo1.CustomAnnotation.ColorEnum;

public class Main
{
	public static void main(String[] args)
	{
		Class<?> cls = TestAnnotation.class;

		for (Method method : cls.getDeclaredMethods())
		{
			if (method.isAnnotationPresent(CustomAnnotation.class))
			{
				CustomAnnotation customAnnotation = method
						.getAnnotation(CustomAnnotation.class);
				System.out.println(method.getName());
				System.out.println(getDetail(customAnnotation));
			}
		}
	}

	private static String getDetail(CustomAnnotation customAnnotation)
	{
		StringBuilder sb = new StringBuilder();

		String name = customAnnotation.name();
		int age = customAnnotation.age();
		String[] hobbies = customAnnotation.hobbies();
		ColorEnum favoriteColor = customAnnotation.favoriteColor();

		sb.append("name=").append(name);
		sb.append(", age=").append(age);
		sb.append(", hobbies=").append(Arrays.toString(hobbies));
		sb.append(", favoriteColor=").append(favoriteColor);
		return sb.toString();
	}
}
