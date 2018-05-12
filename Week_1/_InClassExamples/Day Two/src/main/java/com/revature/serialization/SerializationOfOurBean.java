package com.revature.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class SerializationOfOurBean {

	public static void serializePerson(Person p, File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(file.getPath())));
			// This method serialized the provided Person object to the provided File destination
			out.writeObject(p);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				// ALWAYS CLOSE RESOURCES
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	
	public static Person deserializePerson(File file) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			return (Person) in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		Person p = new Person("William", 25, LocalDate.of(1993, 1, 14), 123456789);
		serializePerson(p, new File("src/main/resources/william.txt"));
		Person serializedPerson = deserializePerson(new File("src/main/resources/william.txt"));
		System.out.println(serializedPerson);
		System.out.println(serializedPerson.getSSN());
		System.out.println(serializedPerson.getDOB());
	}
}
