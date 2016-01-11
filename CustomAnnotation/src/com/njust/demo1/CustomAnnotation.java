package com.njust.demo1;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 设置只针对方法起作用;
 * <p>
 * 设置为运行时生效;
 * 
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomAnnotation
{
	// 设置默认值
	public String name() default "unknow";

	public int age() default 0;

	public String[] hobbies() default "none";

	public ColorEnum favoriteColor() default ColorEnum.Red;

	public enum ColorEnum
	{
		Red, Green, Blue;
	}
}
