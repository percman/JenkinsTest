package com.revature.serializable;

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

import com.revature.logstatus.LogHere;
import com.revature.users.User;

public class Serialization {
		
	// Declarations below save time in each method, so they can be closed in the try-catch block 
	public static ObjectOutputStream out = null;
	public static ObjectInputStream in = null;
	public static FileWriter fw = null;
	public static FileReader fr = null;
	public static BufferedWriter bw = null;
	public static BufferedReader br = null;


	/* Serializes a given user
	 * takes User 'u' and uses the hashCode to write to File 'resource'
	 */
	public static void serializeUser(Object o, File resource) {
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(resource.getPath()));
			out.writeObject(o);
		} catch(IOException ioe) {
            System.out.println("Error reading file '" + resource.getName() + "'"); 
            LogHere.warn(ioe.getMessage());
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
			LogHere.warn(ioe.getMessage());
        } catch (ClassNotFoundException cfne) {
        	System.out.println("Error reading a class file");
        	LogHere.warn(cfne.getMessage());
		} finally {
			try {
				in.close();
			} catch(IOException ioe) {
	            System.out.println("Error reading file '" + resource.getName() + "'"); 
	            LogHere.warn(ioe.getMessage());
	        } 
		}
		return null;
	}
	
}
