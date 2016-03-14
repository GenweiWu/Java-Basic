package com.xpath;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class Dom4jXPathDemo {
	public static void main(String[] args) throws DocumentException {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("xpath_read.xml"));

		test01(doc);

		text02(doc);
		text02a(doc);

		text03(doc);
	}

	private static void text03(Document doc) {
		Element peopleEle = (Element) doc.selectSingleNode("//people");
		/**
		 * 相对于纯粹的xpath读取方式，dom4j无法指定属性的返回值
		 */
		String age = peopleEle.attributeValue("age");
		String male = peopleEle.attributeValue("male");
		String name = peopleEle.attributeValue("name");
		System.out.println("age:" + age + ",male:" + male + ",name:" + name);
		// age:33,male:true,name:Dave
	}

	private static void text02a(Document doc) {
		/**
		 * doc.selectSingleNode获取单个节点；如果有多个值，则返回第一个
		 */
		Element bookEle = (Element) doc.selectSingleNode("//books/book");
		String name = bookEle.attributeValue("name");
		String text = bookEle.getText();
		System.out.println("name:" + name + ",text:" + text);
		// name:333,text:CCC
	}

	private static void text02(Document doc) {
		Element booksEle = (Element) doc.selectSingleNode("//books");
		/**
		 * element.element("")的方法可以读取节点的子节点
		 */
		Element bookEle = booksEle.element("book");
		String name = bookEle.attributeValue("name");
		String text = bookEle.getText();
		System.out.println("name:" + name + ",text:" + text);
		// name:333,text:CCC
	}

	private static void test01(Document doc) {
		/**
		 * 通过selectNodes方法来获取一组节点
		 */
		@SuppressWarnings("unchecked")
		List<Element> books = doc.selectNodes("//book");
		for (Element bookEle : books) {
			/**
			 * 通过Element来操作;
			 * <p>
			 * attribute获取属性
			 * <p>
			 * getText来读取基本值
			 */
			String name = bookEle.attribute("name").getValue();
			String text = bookEle.getText();
			System.out.println("name:" + name + ",text:" + text);
			// name:111,text:AAA
			// name:222,text:BBB
			// name:333,text:CCC
		}

	}
}
