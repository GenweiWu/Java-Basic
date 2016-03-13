package com.xpath;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 总结：
 * <p>
 * 1、五种返回类型，包括上面的四种+Node本身，另外，double无法强转为int类型
 * <p>
 * 2、 区分 //book 和 //books//book 的区别
 * <p>
 * 3、使用@读取属性 ，使用text()读取基本值
 */
public class XpathDemo {

	public static void main(String[] args) throws Exception {
		read();
	}

	private static void read() throws Exception {
		// 初始化
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(new File("xpath_read.xml"));

		XPathFactory factory = XPathFactory.newInstance();
		XPath xPath = factory.newXPath();

		/**
		 * 返回类型之NodeList
		 */
		NodeList bookNode = (NodeList) xPath.evaluate("/bookstore/book", doc,
				XPathConstants.NODESET);
		for (int i = 0; i < bookNode.getLength(); i++) {
			Node node = bookNode.item(i);
			/**
			 * 使用@来读取属性
			 */
			String name = xPath.evaluate("@name", node);
			/**
			 * 使用text()来读取文本节点
			 */
			String content = xPath.evaluate("text()", node);
			System.out.println(name + "," + content);
			// 111,AAA
			// 222,BBB
		}

		NodeList bookNode2 = (NodeList) xPath.evaluate("//book", doc,
				XPathConstants.NODESET);
		for (int i = 0; i < bookNode2.getLength(); i++) {
			Node node = bookNode2.item(i);
			String name = xPath.evaluate("@name", node);
			String content = xPath.evaluate("text()", node);
			System.out.println(name + "," + content);
			// 111,AAA
			// 222,BBB
			// 333,CCC
		}

		/**
		 * 返回类型之Node
		 */
		Node booksNode = (Node) xPath.evaluate("//books//book", doc,
				XPathConstants.NODE);

		String name = xPath.evaluate("@name", booksNode);
		String content = xPath.evaluate("text()", booksNode);
		System.out.println(name + "," + content);// 333,CCC

		Node peopleNode = (Node) xPath.evaluate("//people", doc,
				XPathConstants.NODE);
		/**
		 * 没法直接强转int java.lang.Double cannot be cast to java.lang.Integer
		 */
		// int age = (int) xPath.evaluate("@age", peopleNode,
		// XPathConstants.NUMBER);

		/**
		 * 返回类型之Double,Boolean,String
		 */
		double age = (double) xPath.evaluate("@age", peopleNode,
				XPathConstants.NUMBER);
		boolean isMale = (boolean) xPath.evaluate("@male", peopleNode,
				XPathConstants.BOOLEAN);
		name = (String) xPath.evaluate("@name", peopleNode,
				XPathConstants.STRING);
		System.out.println("age:" + age + " ,isMale:" + isMale + " ,name:"
				+ name);
		// age:33.0 ,isMale:true ,name:Dave
	}
}
