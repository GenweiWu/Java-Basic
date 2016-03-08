package com.xml.bean;

import java.util.Date;

import com.xml.util.ConvertUtil;

/**
 * 学生的bean对象
 * 
 * @author wgw
 * 
 */
public class StudentBean
{
	/**
	 * 学生的姓名
	 */
	private String name;

	/**
	 * 学生的出生日期
	 */
	private Date birthDay;

	public StudentBean()
	{
		name = "unknow";
		birthDay = null;
	}

	public StudentBean(String name, Date birthDay)
	{
		super();
		this.name = name;
		this.birthDay = birthDay;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public Date getBirthDay()
	{
		return birthDay;
	}

	public void setBirthDay(Date birthDay)
	{
		this.birthDay = birthDay;
	}

	@Override
	public String toString()
	{
		return "[name=" + name + ", birthDay="
				+ ConvertUtil.date2String(birthDay) + "]";
	}

}
