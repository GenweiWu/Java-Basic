package com.xml.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.xml.XmlUtil;
import com.xml.bean.ClassBean;
import com.xml.bean.SchoolBean;
import com.xml.bean.StudentBean;
import com.xml.dom4j.Dom4jUtil;
import com.xml.util.ConvertUtil;

public class Dom4jXmlUtilTest
{
	static SchoolBean testSchool = null;
	static XmlUtil xmlUtil = null;

	@BeforeClass
	public static void setUp()
	{
		testSchool = getTestDate();
		xmlUtil = new Dom4jUtil();
	}

	private static SchoolBean getTestDate()
	{
		// school-class01-stu
		StudentBean stu1_1 = new StudentBean("电饭锅",
				ConvertUtil.string2Date("2012年2月2日"));
		// school-class01
		ClassBean classBean1 = new ClassBean("班级一");
		classBean1.addStudent(stu1_1);

		// school-class02-stu
		StudentBean stu2_1 = new StudentBean("烽火台",
				ConvertUtil.string2Date("2015年5月5日"));
		StudentBean stu2_2 = new StudentBean("千万个合计",
				ConvertUtil.string2Date("2020年2月2日"));
		// school-class02
		ClassBean classBean2 = new ClassBean("班级二");
		classBean2.addStudent(stu2_1);
		classBean2.addStudent(stu2_2);

		// school
		SchoolBean school = new SchoolBean();
		school.addClass(classBean1);
		school.addClass(classBean2);

		return school;
	}

	@Test
	public void testReadXml()
	{
		SchoolBean school = xmlUtil.readXml("dom_read.xml");
		System.out.println(school);
		Assert.assertNotNull(school);
	}

	@Test
	public void testWriteXml()
	{
		boolean writeSuccess = xmlUtil
				.writeToXml("dom4j_write.xml", testSchool);
		Assert.assertTrue(writeSuccess);
	}
}
