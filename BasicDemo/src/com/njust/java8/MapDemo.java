package com.njust.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 给你展示一些java8的好用法
 */
public class MapDemo {

    @Test
    public void TestMapGet_old() {
        Map<String, List<String>> map = new HashMap<>();

        List<String> list = map.get("111");
        if (list == null) {
            list = new ArrayList<>();
            map.put("111", list);
        }
        list.add("111Value");

        System.out.println(map);
    }

    @Test
    public void TestMapGet_new() {
        Map<String, List<String>> map = new HashMap<>();

        List<String> list = map.computeIfAbsent("111", k -> new ArrayList<>());
        list.add("111Value");

        System.out.println(map);
    }
}
