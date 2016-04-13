package com.njust.compare;

public class Person implements Comparable<Person> {
	private String name;

	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public int compareTo(Person o) {
		return this.name.compareTo(o.getName());
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

	// 此处为了测试，故意让compareTo方法+equals方法不一致
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			Person another = (Person) obj;
			int diff = this.age - another.getAge();
			return diff > 0;
		}
		return false;
	}

}
