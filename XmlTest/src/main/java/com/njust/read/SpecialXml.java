package com.njust.read;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class SpecialXml {
	public static void main(String[] args) throws Exception {
		SAXReader reader = new SAXReader();
		Document doc = reader.read(new File("src/a.xml"));

		Element book1 = (Element) doc.selectSingleNode("//data/book1");
		String name1 = book1.attributeValue("name");
		System.out.println(name1);
		String text1 = book1.getTextTrim();
		System.out.println(text1);

		Element book2 = (Element) doc.selectSingleNode("//data/book2");
		String text2 = book2.getTextTrim();
		System.out.println(text2);
	}
}
