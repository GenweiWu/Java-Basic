package com.njust.util.list;

import com.njust.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试freemarker中list+map用法
 */
public class ListTest {
    public static void main(String[] args) {

        File templateDir = new File("src/main/resources");

        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("sequence", Arrays.asList("aa", "bb", "cc"));

        Map<String, String> map = new HashMap<>();
        map.put("a", "AA");
        map.put("b", "BB");
        map.put("c", "CC");
        dataMap.put("myHash", map);

        try {
            FreeMarkerUtil freeMarkerUtil = new FreeMarkerUtil(templateDir);
            System.out.println(freeMarkerUtil.doTest("list.ftl", dataMap));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
