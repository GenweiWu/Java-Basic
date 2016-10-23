package com.njust.test;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Joda-time 之Date-Time
 * Created by WGW on 2016/10/15.
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        //test01();
        test02();
//        test03();
    }

    private static void test02() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
        DateTime dateTime = formatter.parseDateTime("2016-11-15 08:24:35");
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));

        DateTime startDateTime = dateTime.withTimeAtStartOfDay();
        System.out.println(startDateTime.toString("yyyy-MM-dd HH:mm:ss"));
    }

    private static void test03() {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dateTime = formatter.parseDateTime("2016-11-15");
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss", Locale.CHINA));
    }

    private static void test01() {
        DateTime dateTime = DateTime.now();
        System.out.println(dateTime.toString("yyyy-MM-dd"));

        Date date = dateTime.toDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(simpleDateFormat.format(date));
    }

}
