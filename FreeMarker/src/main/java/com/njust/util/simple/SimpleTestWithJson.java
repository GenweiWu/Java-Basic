package com.njust.util.simple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.njust.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 简单测试freemarker用法
 * --使用json传递参数
 */
public class SimpleTestWithJson {

    public static void main(String[] args) {

        File templateDir = new File("src/main/resources");

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode = mapper.createObjectNode();

        objectNode.put("name", "大卫");
        objectNode.put("isMale", true);
        objectNode.put("age", 25);

        SimpleDateFormat sdt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectNode.putPOJO("date", sdt.format(new Date()));

        try {
            FreeMarkerUtil freeMarkerUtil = new FreeMarkerUtil(templateDir);
            System.out.println(freeMarkerUtil.doTest("simpleWithJson.ftl", objectNode));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
