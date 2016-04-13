package com.njust.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WhyEquals {

	public static void main(String[] args) {
		List<Person> personList = new ArrayList<>();
		personList.add(new Person("alice", 40));
		personList.add(new Person("bob", 30));
		personList.add(new Person("green", 20));

		// 我们要查找的对象
		Person target = new Person("bob", 0);

		// 第一种方法：List.indexOf ==> 用的equals方法
		int index = personList.indexOf(target);
		System.out.println(index);
		// -1

		// 第二种方法：Collections.binarySearch方法 ==> 用的是
		// 但是binarySearch要求集合是有序的
		Collections.sort(personList);
		index = Collections.binarySearch(personList, target);
		System.out.println(index);
		// 1
	}
}
