package com.njust.util.list;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.njust.util.FreeMarkerUtil;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试freemarker中list+map用法
 */
public class ListTestWithJson {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static void main(String[] args) {


        File templateDir = new File("src/main/resources");

        ArrayNode arrayNode = MAPPER.createArrayNode();
        arrayNode.add("111");
        arrayNode.add("222");
        arrayNode.add("333");

        Map<String, Object> dataMap = new HashMap<>();
        //list1:iterator
        dataMap.put("arrayNode1", arrayNode.iterator());
        //list2:to list<ObjectNode>
        List<String> nodeList = new ArrayList<>();
        for (JsonNode node : arrayNode) {
            nodeList.add(node.asText());
        }
        dataMap.put("arrayNode2", nodeList);

        //map
        ObjectNode myHashNode = MAPPER.createObjectNode();
        myHashNode.put("name", "AAA");
        myHashNode.put("age", "BBB");
        dataMap.put("myHash", myHashNode);


        try {
            FreeMarkerUtil freeMarkerUtil = new FreeMarkerUtil(templateDir);
            System.out.println(freeMarkerUtil.doTest("listWithJson.ftl", dataMap));
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }
    }
}
