package com.njust;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SimpleSerial {
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {
		Person person = new Person("zhangsan", 24, Gender.MALE);
		test(person);

		Person2 person2 = new Person2("zhangsan", 24, Gender.MALE);
		test(person2);
	}

	private static void test(final Object obj) throws IOException,
			FileNotFoundException, ClassNotFoundException {
		// write
		File file = new File("temp/person.out");

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		out.writeObject(obj);
		out.close();

		// read
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		Object readObj = in.readObject();
		in.close();
		System.out.println(readObj);
	}
}
