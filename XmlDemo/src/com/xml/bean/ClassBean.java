package com.xml.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 班级bean对象,对象多个学生
 * 
 * @author wgw
 * 
 */
public class ClassBean
{
	/**
	 * 班级名
	 */
	private String className;

	/**
	 * 学生对象的集合
	 */
	private List<StudentBean> students = new ArrayList<StudentBean>();

	public ClassBean()
	{
		className = "unknow";
	}

	public ClassBean(String className)
	{
		super();
		this.className = className;
	}

	public String getClassName()
	{
		return className;
	}

	public void setClassName(String className)
	{
		this.className = className;
	}

	public List<StudentBean> getStudents()
	{
		return students;
	}

	public void clearStudents()
	{
		this.students.clear();
	}

	public void addStudent(StudentBean stu)
	{
		this.students.add(stu);
	}

	@Override
	public String toString()
	{
		return "[className=" + className + ", students=" + students + "]\n";
	}

}
