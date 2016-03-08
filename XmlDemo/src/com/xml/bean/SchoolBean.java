package com.xml.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 学校的bean对象
 * 
 * @author wgw
 * 
 */
public class SchoolBean
{
	/**
	 * 班级对象的集合
	 */
	List<ClassBean> classes = new ArrayList<ClassBean>();

	public List<ClassBean> getClasses()
	{
		return classes;
	}

	public void clearClasses()
	{
		this.classes.clear();
	}

	public void addClass(ClassBean classBean)
	{
		this.classes.add(classBean);
	}

	@Override
	public String toString()
	{
		return "[classes=" + "\n" + classes.toString() + "]";
	}

}
