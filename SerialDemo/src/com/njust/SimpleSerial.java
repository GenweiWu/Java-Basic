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
		// write
		File file = new File("temp/person.out");

		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(
				file));
		Person person = new Person("zhangsan", 24, Gender.MALE);
		out.writeObject(person);
		out.close();

		// read
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
		Object obj = in.readObject();
		in.close();
		System.out.println(obj);
	}
}
