package com.njust.read;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import java.io.File;
import java.util.List;

/**
 *
 */
public class XpathDemo1 {
    public static void main(String[] args) throws DocumentException, SAXException {

        SAXReader saxReader = new SAXReader();

        //1、忽略dtd校验
        saxReader.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);

        Document doc = saxReader.read(new File("src/b.xml"));
        Element bNode = (Element) doc.selectSingleNode("//b[last()]");
        //System.out.println(bNode.asXML());

        //2、选择当前节点下的符合要求节点

        //虽然是当前节点的selectNodes方法，但是不加当前的xpath,则仍然会选出全部的节点。
        //List<Element> list = bNode.selectNodes("//row[x='555']");
        //System.out.println(list);

        //.标识当前节点
        List<Element> xNodeList = bNode.selectNodes(".//row/x[text()='555']");
        for (Element element : xNodeList) {
            Element nextSibling = getNextElement(element);
            //System.out.println(nextSibling.asXML());
            nextSibling.setText("bingo");
        }

        System.out.println(doc.asXML());
    }

    //获取下一个兄弟节点
    public static Element getNextElement(Element element) {
        if (element == null) {
            return null;
        }

        if (element.isRootElement()) {
            return null;
        }

        return (Element) element.selectSingleNode("./following-sibling::*[1]");
    }

    //获取上一个兄弟节点
    public static Element getPreElement(Element element) {
        if (element == null) {
            return null;
        }

        if (element.isRootElement()) {
            return null;
        }

        return (Element) element.selectSingleNode("./preceding-sibling::*[1]");
    }
}
