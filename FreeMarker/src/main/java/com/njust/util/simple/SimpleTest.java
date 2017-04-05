package com.njust.util.simple;

import com.njust.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 简单测试freemarker用法
 */
public class SimpleTest {

    public static void main(String[] args) {

        File templateDir = new File("src/main/resources");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("name", "大卫");
        dataMap.put("isMale", true);
        dataMap.put("age", 25);
        dataMap.put("date", new Date());

        try {
            FreeMarkerUtil freeMarkerUtil = new FreeMarkerUtil(templateDir);
            System.out.println(freeMarkerUtil.doTest("simple.ftl", dataMap));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
