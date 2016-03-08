package com.xml.dom;

import java.io.File;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xml.NodeConstant;
import com.xml.XmlUtil;
import com.xml.bean.ClassBean;
import com.xml.bean.SchoolBean;
import com.xml.bean.StudentBean;
import com.xml.util.ConvertUtil;

public class DomUtil implements XmlUtil
{
	/**
	 * 读取xml文件，得到bean对象
	 * 
	 * @param xmlFilePath
	 * @return 读入成功则返回学校对象，如果过程中出现错误则返回null
	 */
	public SchoolBean readXml(String xmlFilePath)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();

		Document document = null;
		try
		{
			DocumentBuilder builder = dbFactory.newDocumentBuilder();

			document = builder.parse(new File(xmlFilePath));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

		// 得到根节点
		Element root = document.getDocumentElement();
		assert (root.getNodeName().equals(NodeConstant.NODE_SCHOOL));
		// 做转换
		SchoolBean schoolBean = new SchoolBean();

		// 得到子节点-班级
		NodeList classes = root.getElementsByTagName(NodeConstant.NODE_CLASS);
		for (int i = 0; i < classes.getLength(); i++)
		{
			Element student = (Element) classes.item(i);
			// 对节点做 转换
			schoolBean.addClass(parseClassNode(student));
		}

		return schoolBean;
	}

	// 将班级节点转换为对象的班级bean对象
	private ClassBean parseClassNode(Element classElement)
	{
		ClassBean classBean = new ClassBean();

		// 得到班级的属性班级名
		String className = classElement
				.getAttribute(NodeConstant.NODE_CLASS_NAME);
		classBean.setClassName(className);

		// 得到班级的子节点-学生
		NodeList students = classElement
				.getElementsByTagName(NodeConstant.NODE_STUDENT);
		for (int i = 0; i < students.getLength(); i++)
		{
			Element student = (Element) students.item(i);
			classBean.addStudent(parseStudentNode(student));
		}

		return classBean;
	}

	// 将学生节点转换为对象的学生bean对象
	private StudentBean parseStudentNode(Element stuElement)
	{
		StudentBean stuBean = new StudentBean();

		// 姓名节点
		String name = stuElement.getAttribute(NodeConstant.NODE_STUDENT_NAME);
		stuBean.setName(name);

		// 学生节点
		String birthDayStr = stuElement.getTextContent();
		Date birthday = ConvertUtil.string2Date(birthDayStr);
		stuBean.setBirthDay(birthday);

		return stuBean;
	}

	/**
	 * 将SchoolBean对象保存的内容保存到对应路径的xml文件
	 * 
	 * @param targetXmlPath
	 * @param school
	 * @return 写入xml文件成功则返回true，否则返回false
	 */
	public boolean writeToXml(String targetXmlPath, SchoolBean school)
	{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try
		{
			builder = dbFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e)
		{
			System.err.println("write xml file " + targetXmlPath + "[FAILED]!"
					+ e.toString());
			return false;
		}
		Document document = builder.newDocument();

		// 创建根节点
		Element root = document.createElement(NodeConstant.NODE_SCHOOL);

		// 创建子节点班级
		for (ClassBean tempClass : school.getClasses())
		{
			root.appendChild(parseClassBean(document, tempClass));
		}
		document.appendChild(root);

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = null;
		try
		{
			transformer = transformerFactory.newTransformer();
		}
		catch (TransformerConfigurationException e)
		{
			System.err.println("write xml file " + targetXmlPath + "[FAILED]!"
					+ e.toString());
			return false;
		}
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");

		// 把document对象用一个DOMSource对象包装起来
		Source xmlSource = new DOMSource(document);

		// 建立一个存储目标对象
		Result outputTarget = new StreamResult(new File(targetXmlPath));

		try
		{
			transformer.transform(xmlSource, outputTarget);
		}
		catch (TransformerException e)
		{
			System.err.println("write xml file " + targetXmlPath + "[FAILED]!"
					+ e.toString());
			return false;
		}
		return true;
	}

	private Element parseClassBean(Document document, ClassBean classBean)
	{
		Element classEle = document.createElement(NodeConstant.NODE_CLASS);
		classEle.setAttribute(NodeConstant.NODE_CLASS_NAME,
				classBean.getClassName());
		for (StudentBean stu : classBean.getStudents())
		{
			classEle.appendChild(parseStudentBean(document, stu));
		}

		return classEle;
	}

	private Element parseStudentBean(Document document, StudentBean studentBean)
	{
		Element stuEle = document.createElement(NodeConstant.NODE_STUDENT);

		String name = studentBean.getName();
		stuEle.setAttribute(NodeConstant.NODE_STUDENT_NAME, name);

		Date birthday = studentBean.getBirthDay();
		stuEle.setTextContent(ConvertUtil.date2String(birthday));

		return stuEle;
	}
}
