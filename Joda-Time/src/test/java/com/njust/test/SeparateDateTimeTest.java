package com.njust.test;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Created by WGW on 2016/11/24.
 */
public class SeparateDateTimeTest {

    @Test
    public void test01() {
        Date uDate = new Date();
        java.sql.Date date = new java.sql.Date(uDate.getTime());
        java.sql.Time time = new java.sql.Time(uDate.getTime());

        //三者是相等的，java只是针对sql.date和sql.time将对应的方法屏蔽了，并没有 uDate = sDate+ sTimey一说
        System.out.println(uDate.getTime());
        System.out.println(date.getTime());
        System.out.println(time.getTime());

        //failed
        Date sumDate = new Date(date.getTime() + time.getTime());
        Assert.assertEquals(uDate, sumDate);

    }
}
