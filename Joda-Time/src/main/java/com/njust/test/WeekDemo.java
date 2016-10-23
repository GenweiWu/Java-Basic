package com.njust.test;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * 获取日期所在的周
 * Created by WGW on 2016/10/22.
 */
public class WeekDemo {
    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static void main(String[] args) {
        getWeek("2015-12-27 00:00:00");
        getWeek("2015-12-28 00:00:00");
        getWeek("2016-01-04 00:00:00");
        getWeek("2016-01-11 00:00:00");
    }

    private static void getWeek(String dateTimeStr) {

        DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_TIME_PATTERN);
        DateTime dateTime = formatter.parseDateTime(dateTimeStr);

        DateTime firstDayOfWeek = dateTime.withDayOfWeek(DateTimeConstants.MONDAY);
        DateTime lastDayOfWeek = dateTime.withDayOfWeek(DateTimeConstants.SUNDAY);

        String info = String.format("date:%s ,with [%s ~ %s]", dateTimeStr, firstDayOfWeek.toString(DATE_PATTERN), lastDayOfWeek.toString(DATE_PATTERN));
        System.out.println(info);
    }
}
