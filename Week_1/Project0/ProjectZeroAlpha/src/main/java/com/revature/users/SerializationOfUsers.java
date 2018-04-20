package com.revature.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.revature.readwrite.ReadWrite;

public class SerializationOfUsers extends ReadWrite{

	// File paths declared here so they can easily be changed later 
	public static File userFile = new File("src/main/resources/users.txt");
	public static File serializedUserFile = new File("src/main/resources/serializeduser.txt");
	
	// Serializes a given user
	// takes User 'u' and uses the hashCode to write to File 'resource'
	public static void serializeUser(User u, File resource) {
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(resource.getPath()));
			out.writeObject(u);
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				//System.out.println("Resource successfully closed");
			}
		}
	}


	// Deserializes a given user (return data)
	// takes User 'u' from File 'resource' and uses the hashCode to return the User data
	public static User returnDeserializedUser(File resource) {
		try {
			in = new ObjectInputStream(new FileInputStream(resource.getPath()));
			User deserializedUser = (User) in.readObject();
			return deserializedUser;
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
				//System.out.println("Resource successfully closed");
			}
		}
		return null;
	}
	
	// Deserializes all user data into a HashMap for parsing 
	// * very inefficient way to do this, but it works for now *
	// ** having problems with tempFile, so decided to store all userData into LinkedList **
	// deserializes the text from File 'resource', and stores it in a hashMap 
	// * by reading in, line by line, into LinkedList<String>, then writing it back later * 
	public static HashMap<Integer, User> hashMapUserData(File resource) {
		HashMap<Integer, User> hashUsers = new HashMap<>();
		ArrayList<String> userData = new ArrayList<>();
		int count = lineCount(resource);
		
		// just to ensure extra data isn't accidently written into user data, such as null
		tempFile.delete();
		
		try {
			
			for(int i = 0; i < (count - 1); i++) {
				hashUsers.put(i, returnDeserializedUser(resource));
				userData.add(readFirstLine(resource));
				deleteContentOfFile(readFirstLine(resource), resource);
			}
			
			for(int i = 0; i < (count - 1); i++) {
				writeToExistingFile(userData.get(i), resource);
			}
			
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				//System.out.println("Resource successfully closed");
			}
		}
		return hashUsers;
	}
	
}
