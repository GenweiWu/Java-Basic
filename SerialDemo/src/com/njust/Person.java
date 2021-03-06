package com.njust;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Person implements Serializable {

	private static final long serialVersionUID = 9066561578579619797L;

	@Getter
	private String name;
	@Getter
	transient private int age;
	@Getter
	private Gender gender;

	public Person(String name, int age, Gender gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException {
		in.defaultReadObject();
		age = in.readInt();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.defaultWriteObject();
		out.writeInt(age);
	}


}
