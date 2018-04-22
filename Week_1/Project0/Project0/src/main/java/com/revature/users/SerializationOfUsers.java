package com.revature.users;

import static com.revature.readwrite.ReadWrite.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayDeque;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class SerializationOfUsers {
		
	// Declarations below save time in each method, so they can be closed in the try-catch block 
	public static ObjectOutputStream out = null;
	public static ObjectInputStream in = null;
	public static FileWriter fw = null;
	public static FileReader fr = null;
	public static BufferedWriter bw = null;
	public static BufferedReader br = null;

	
	// File paths declared here so they can easily be changed later 
	public static File userFile = new File("src/main/resources/users.txt");
	public static File serializedUserFile = new File("src/main/resources/serializeduser.txt");
	
	public static final Logger logger = Logger.getLogger(SerializationOfUsers.class);

	/* Serializes a given user
	 * takes User 'u' and uses the hashCode to write to File 'resource'
	 */
	public static void serializeUser(User u, File resource) {
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(resource.getPath()));
			out.writeObject(u);
		} catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
			logger.warn(ioe.getMessage());
        }
	}


	/* Deserializes a given user (return user)
	 * takes User 'u' from File 'resource' and uses the hashCode to return the User data
	 */
	public static User returnDeserializedUser(File resource) {
		try {
			in = new ObjectInputStream(new FileInputStream(resource.getPath()));
			User deserializedUser = (User) in.readObject();
			return deserializedUser;
		} catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
			logger.warn(ioe.getMessage());
        } catch (ClassNotFoundException cfne) {
        	System.out.println("Error reading a class file");
			logger.warn(cfne.getMessage());
		} finally {
			try {
				in.close();
			} catch(IOException ioe) {
	            System.out.println("Error reading file '" + resource.getName() + "'"); 
				logger.warn(ioe.getMessage());
	        } 
		}
		return null;
	}
	
	
	/* Deserializes all user data into a HashMap for parsing
	 * deserializes the text from File 'resource', and stores it in a hashMap
	 * Uses an ArrayDeque because:
	 * 		do not allow null values
	 * 		faster than LinkedList (when used as a queue)
	 * 		order doesn't matter
	 */
	public static HashMap<Integer, User> hashMapUserData(File resource) {
		HashMap<Integer, User> hashUsers = new HashMap<>();
		ArrayDeque<String> userData = new ArrayDeque<>();
		int count = lineCount(resource);		
			
		for(int i = 0; i < (count - 1); i++) {
			hashUsers.put(i, returnDeserializedUser(resource));
			userData.add(readFirstLine(resource));
			deleteContentOfFile(readFirstLine(resource), resource);
		}
				
		for(int i = 0; i < (count - 1); i++) {
			writeToExistingFile(userData.removeLast(), resource);
		}
			
		 
		return hashUsers;
	}
	
}
