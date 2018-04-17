package com.revature.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class SerializingAndDeserializingBeans {

	
	static ObjectOutputStream out = null;
	static ObjectInputStream in = null;
	
	public static void serializeObject(Person person, File toFile) {
		try {
			out = new ObjectOutputStream(new FileOutputStream(toFile.getPath()));
			out.writeObject(person);
			System.out.println("Object successfully serialized to " + toFile.getName());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				System.out.println("Resource successfully closed");
			}
		}
	}
	
	public static void deserializeObject(File fromFile) {
		try {
			in = new ObjectInputStream(new FileInputStream(fromFile.getPath()));
			Person deserializedPerson = (Person) in.readObject();
			System.out.println(deserializedPerson);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} catch (ClassNotFoundException cfne) {
			System.err.println(cfne.getMessage());
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				System.out.println("Resource successfully closed");
			}
		}
	}
	
	public static Person returnDeserializedObject(File fromFile) {
		try {
			in = new ObjectInputStream(new FileInputStream(fromFile.getPath()));
			Person deserializedPerson = (Person) in.readObject();
			return deserializedPerson;
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} catch (ClassNotFoundException cfne) {
			System.err.println(cfne.getMessage());
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				System.out.println("Resource successfully closed");
			}
		}
		return null;
	}
	
	public static void main(String... args) {
//		Person p = new Person("William", "Gentry", 123456789, LocalDate.of(1993, 1, 14), 55555);
//		serializeObject(p, new File("src/main/resources/person.txt"));
		Person p = returnDeserializedObject(new File("src/main/resources/person.txt"));
		System.out.println(p);
	}
}
