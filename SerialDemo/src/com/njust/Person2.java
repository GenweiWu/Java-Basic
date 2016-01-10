package com.njust;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Person2 implements Externalizable {

	private static final long serialVersionUID = 9066561578579619797L;

	@Getter
	private String name;
	@Getter
	transient private int age;
	@Getter
	private Gender gender;

	public Person2() {
		System.out.println("default constructor!");
	}

	public Person2(String name, int age, Gender gender) {
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

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		// TODO Auto-generated method stub
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// TODO Auto-generated method stub
	}

}
