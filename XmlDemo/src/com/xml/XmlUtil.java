package com.xml;

import com.xml.bean.SchoolBean;

public interface XmlUtil
{
	/**
	 * 
	 * @param xmlFilePath
	 *            读取的xml文件的路径
	 * @return 读取成功返回学校对象，否则返回null
	 */
	public SchoolBean readXml(String xmlFilePath);

	/**
	 * 
	 * @param targetXmlPath
	 *            写入xml文件的文件路径
	 * @param school
	 *            要写入xml文件的学校对象信息
	 * @return 写入成功则返回true，否则返回false
	 */
	public boolean writeToXml(String targetXmlPath, SchoolBean school);

}
