package com.xml.dom4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.xml.NodeConstant;
import com.xml.XmlUtil;
import com.xml.bean.ClassBean;
import com.xml.bean.SchoolBean;
import com.xml.bean.StudentBean;
import com.xml.util.ConvertUtil;

public class Dom4jUtil implements XmlUtil
{

	@SuppressWarnings("unchecked")
	@Override
	public SchoolBean readXml(String xmlFilePath)
	{
		SAXReader saxReader = new SAXReader();
		Document document = null;
		SchoolBean schoolBean = null;
		try
		{
			document = saxReader.read(new File(xmlFilePath));
			// 班级节点
			Element schoolEle = document.getRootElement();
			schoolBean = new SchoolBean();
			// 处理子节点-班级
			List<Element> classes = schoolEle.elements(NodeConstant.NODE_CLASS);
			for (Element classEle : classes)
			{
				schoolBean.addClass(parseClassBean(document, classEle));
			}

		}
		catch (DocumentException e)
		{
			System.err.println(e.toString());
			return null;
		}
		return schoolBean;
	}

	/**
	 * 将单个班级节点 [变成] 单个班级对象
	 * 
	 * @param document
	 * @param classEle
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private ClassBean parseClassBean(Document document, Element classEle)
	{
		ClassBean classBean = new ClassBean();
		// 班级名
		Attribute classAttr = classEle.attribute(NodeConstant.NODE_CLASS_NAME);
		classBean.setClassName(classAttr.getValue());

		// 班级子节点-学生
		List<Element> students = classEle.elements(NodeConstant.NODE_STUDENT);
		for (Element stuEle : students)
		{
			classBean.addStudent(parseStudent(document, stuEle));
		}
		return classBean;
	}

	/**
	 * 将单个学生节点 [变成] 单个学生对象
	 * 
	 * @param document
	 * @param stuEle
	 * @return
	 */
	private StudentBean parseStudent(Document document, Element stuEle)
	{
		StudentBean studentBean = new StudentBean();
		// 学生姓名
		Attribute stuNameAttr = stuEle
				.attribute(NodeConstant.NODE_STUDENT_NAME);
		studentBean.setName(stuNameAttr.getValue());
		// 学生文本子节点-生日
		String dateStr = stuEle.getText();
		studentBean.setBirthDay(ConvertUtil.string2Date(dateStr));

		return studentBean;
	}

	@Override
	public boolean writeToXml(String targetXmlPath, SchoolBean school)
	{
		Document document = DocumentHelper.createDocument();
		// 根节点
		Element schoolEle = document.addElement(NodeConstant.NODE_SCHOOL);
		// 添加班级节点
		for (ClassBean classBean : school.getClasses())
		{
			Element classEle = schoolEle.addElement(NodeConstant.NODE_CLASS);
			classEle.addAttribute(NodeConstant.NODE_CLASS_NAME,
					classBean.getClassName());
			// 添加班级子节点-学生节点
			for (StudentBean studentBean : classBean.getStudents())
			{
				Element stuEle = classEle.addElement(NodeConstant.NODE_STUDENT);

				// 添加学生属性-学生姓名
				String studentName = studentBean.getName();
				stuEle.addAttribute(NodeConstant.NODE_STUDENT_NAME, studentName);

				// 添加学生子节点-生日
				Date bithday = studentBean.getBirthDay();
				String birthdayStr = ConvertUtil.date2String(bithday);
				stuEle.addText(birthdayStr);
			}
		}

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("utf-8");
		format.setIndent(false);
		try
		{

			XMLWriter writer = new XMLWriter(new FileWriter(targetXmlPath),
					format);
			writer.write(document);
			writer.close();
		}
		catch (IOException e)
		{
			System.err.println(e.toString());
			return false;
		}
		return true;
	}

}
