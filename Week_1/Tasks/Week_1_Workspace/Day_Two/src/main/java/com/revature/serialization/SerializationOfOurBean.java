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
			in = new ObjectInputStream (new FileInputStream(new File(file.getPath())));
			return (Person) in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				// ALWAYS CLOSE REXOURCES
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		
		// Here we will serialize the person
		Person p = new Person("Amelia", 24, LocalDate.of(1994, 4, 18), 123456789);
		System.out.println(p);
		System.out.println(p.getSSN());
		System.out.println(p.getDOB());
		
		serializePerson(p, new File("src/main/resources/amelia.txt"));
		
		// Now we will deserialize the person
		Person serializedPerson = deserializePerson(new File("src/main/resources/amelia.txt"));
		System.out.println(serializedPerson);
		System.out.println(serializedPerson.getSSN());
		System.out.println(serializedPerson.getDOB());
	}
}
