package com.xml.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertUtil
{
	/**
	 * 转换成功返回Date类型，否则返回null
	 * 
	 * @param dateStr
	 * @return
	 */
	public static Date string2Date(String dateStr)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		try
		{
			return sdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * date转换为String
	 * 
	 * @param date
	 * @return
	 */
	public static String date2String(Date date)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		return sdf.format(date);
	}
}
