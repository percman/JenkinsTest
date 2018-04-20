package com.revature.serialization;

import java.io.Serializable;
import java.time.LocalDate;

public class Person implements Serializable {

	// Serializable is a marker interface => an interface without any methods
	
	
	private static final long serialVersionUID = 1535994046839601447L;
	
	private String name;
	private int age;
	private transient LocalDate DOB; // New to Java 8
	private transient int SSN;
	
	public Person () {}

	public Person(String name, int age, LocalDate dOB, int sSN) {
		super();
		this.name = name;
		this.age = age;
		DOB = dOB;
		SSN = sSN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDOB() {
		return DOB;
	}

	public void setDOB(LocalDate dOB) {
		DOB = dOB;
	}

	public int getSSN() {
		return SSN;
	}

	public void setSSN(int sSN) {
		SSN = sSN;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	} 

}
