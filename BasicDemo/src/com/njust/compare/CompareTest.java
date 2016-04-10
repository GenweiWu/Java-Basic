package com.njust.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTest {
	public static void main(String[] args) {
		test01();
	}

	private static void test01() {
		Person p1 = new Person("a", 3);
		Person p2 = new Person("b", 1);
		Person p3 = new Person("c", 4);
		Person p4 = new Person("d", 2);
		Person p5 = new Person("e", 5);

		List<Person> personList = new ArrayList<>();
		personList.add(p3);
		personList.add(p1);
		personList.add(p5);
		personList.add(p2);
		personList.add(p4);

		showPersonInfo(personList);

		// 默认排序
		System.out.println("默认排序");
		Collections.sort(personList);
		showPersonInfo(personList);

		// 自定义排序
		System.out.println("自定义排序");
		Collections.sort(personList, new Comparator<Person>() {

			@Override
			public int compare(Person o1, Person o2) {
				int age1 = o1.getAge();
				int age2 = o2.getAge();
				return age1 - age2;
			}
		});
		showPersonInfo(personList);
	}

	private static void showPersonInfo(List<Person> personList) {
		for (Person person : personList) {
			System.out.println(person);
		}

	}
}
