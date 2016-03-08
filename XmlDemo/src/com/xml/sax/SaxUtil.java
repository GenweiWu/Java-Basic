package com.xml.sax;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;
import org.xml.sax.helpers.DefaultHandler;

import com.xml.NodeConstant;
import com.xml.XmlUtil;
import com.xml.bean.ClassBean;
import com.xml.bean.SchoolBean;
import com.xml.bean.StudentBean;
import com.xml.util.ConvertUtil;

public class SaxUtil extends DefaultHandler implements XmlUtil
{
	private SchoolBean school = null;
	// 班级对象
	private ClassBean tempClassBean = null;
	// 学生对象
	private StudentBean tempStudentBean = null;

	private String preTag = null;

	public SchoolBean getSchool()
	{
		return school;
	}

	@Override
	public SchoolBean readXml(String xmlFilePath)
	{
		SaxUtil handler;
		FileInputStream xmlStream = null;
		try
		{
			xmlStream = new FileInputStream(xmlFilePath);

			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();

			handler = new SaxUtil();
			saxParser.parse(xmlStream, handler);
		}
		catch (Exception e)
		{
			System.err.println(e.toString());
			return null;
		}
		finally
		{
			try
			{
				xmlStream.close();
			}
			catch (IOException e)
			{
				System.err.println(e.toString());
			}
		}

		return handler.getSchool();
	}

	@Override
	public boolean writeToXml(String targetXmlPath, SchoolBean school)
	{
		try
		{
			SAXTransformerFactory tsfFactory = (SAXTransformerFactory) SAXTransformerFactory
					.newInstance();
			TransformerHandler th = tsfFactory.newTransformerHandler();

			Result resultXml = new StreamResult(new FileOutputStream(
					targetXmlPath));
			// 设置xml文件的输出格式
			Transformer transformer = th.getTransformer();
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");

			th.setResult(resultXml);
			// 开始写xml内容
			th.startDocument();

			writeSchoolBean(th, school);

			th.endDocument();
		}
		catch (Exception e)
		{
			System.err.println("write error" + e.toString());
			return false;
		}
		return true;
	}

	/**
	 * 写单个学校节点
	 * 
	 * @param th
	 * @param school
	 *            学校节点
	 * @throws SAXException
	 */
	private void writeSchoolBean(TransformerHandler th, SchoolBean school)
			throws SAXException
	{
		th.startElement("", "", NodeConstant.NODE_SCHOOL, null);
		// 学校子节点 班级
		for (ClassBean classBean : school.getClasses())
		{
			writeClassBean(th, classBean);
		}
		th.endElement("", "", NodeConstant.NODE_SCHOOL);
	}

	/**
	 * 写单个班级子节点
	 * 
	 * @param th
	 * @param classBean
	 *            班级bean对象
	 * @throws SAXException
	 */
	private void writeClassBean(TransformerHandler th, ClassBean classBean)
			throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();

		// 班级名
		attributes.addAttribute("", "", NodeConstant.NODE_CLASS_NAME, "",
				classBean.getClassName());

		th.startElement("", "", NodeConstant.NODE_CLASS, attributes);

		// 班级子节点 学生
		for (StudentBean studentBean : classBean.getStudents())
		{
			writeStudentBean(th, studentBean);
		}

		th.endElement("", "", NodeConstant.NODE_CLASS);

	}

	/**
	 * 写单个学生子节点
	 * 
	 * @param th
	 * @param tudentBean
	 * @throws SAXException
	 */
	private void writeStudentBean(TransformerHandler th, StudentBean studentBean)
			throws SAXException
	{
		AttributesImpl attributes = new AttributesImpl();

		// 学生名
		attributes.addAttribute("", "", NodeConstant.NODE_STUDENT_NAME, "",
				studentBean.getName());
		th.startElement("", "", NodeConstant.NODE_STUDENT, attributes);

		// 学生文本子节点 生日
		String birthday = ConvertUtil.date2String(studentBean.getBirthDay());
		th.characters(birthday.toCharArray(), 0, birthday.length());

		th.endElement("", "", NodeConstant.NODE_STUDENT);
	}

	@Override
	public void startDocument() throws SAXException
	{
		school = new SchoolBean();
		preTag = NodeConstant.NODE_SCHOOL;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException
	{
		if (qName.equalsIgnoreCase(NodeConstant.NODE_CLASS))
		{
			tempClassBean = new ClassBean();
			String className = attributes
					.getValue(NodeConstant.NODE_CLASS_NAME);
			tempClassBean.setClassName(className);

			preTag = NodeConstant.NODE_CLASS;
			return;
		}

		if (qName.equalsIgnoreCase(NodeConstant.NODE_STUDENT))
		{
			tempStudentBean = new StudentBean();
			String studentName = attributes
					.getValue(NodeConstant.NODE_STUDENT_NAME);
			tempStudentBean.setName(studentName);

			preTag = NodeConstant.NODE_STUDENT;
			return;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException
	{
		if (qName.equalsIgnoreCase(NodeConstant.NODE_CLASS))
		{
			school.addClass(tempClassBean);
			return;
		}

		if (qName.equalsIgnoreCase(NodeConstant.NODE_STUDENT))
		{
			tempClassBean.addStudent(tempStudentBean);
			return;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException
	{
		if (preTag == null)
		{
			return;
		}

		String detail = new String(ch, start, length);
		if (preTag.equalsIgnoreCase(NodeConstant.NODE_STUDENT))
		{
			tempStudentBean.setBirthDay(ConvertUtil.string2Date(detail));

			preTag = null;
			return;
		}
	}

}
