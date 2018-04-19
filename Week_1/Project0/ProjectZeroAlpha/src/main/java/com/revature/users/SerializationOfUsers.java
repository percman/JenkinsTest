package com.revature.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.LinkedList;

import com.revature.readwrite.ReadWrite;

public class SerializationOfUsers extends ReadWrite{

	public static File userFile = new File("src/main/resources/users.txt");
	public static File serializedUserFile = new File("src/main/resources/serializeduser.txt");
	
	public static void serializeUser(ProjectUsers u, File toFile) {
		
		try {
			out = new ObjectOutputStream(new FileOutputStream(toFile.getPath()));
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
	
	public static void deserializeUser(File fromFile) {
		try {
			in = new ObjectInputStream(new FileInputStream(fromFile.getPath()));
			ProjectUsers deserializedUser = (ProjectUsers) in.readObject();
			System.out.println(deserializedUser);
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
	}

	public static ProjectUsers returnDeserializedUser(File fromFile) {
		try {
			in = new ObjectInputStream(new FileInputStream(fromFile.getPath()));
			ProjectUsers deserializedUser = (ProjectUsers) in.readObject();
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
	
	public static HashMap<Integer, ProjectUsers> hashSetUserData(File fromFile) {
		HashMap<Integer, ProjectUsers> hashUsers = new HashMap<>();
		LinkedList<String> userData = new LinkedList<>();
		int count = lineCount(fromFile);
		
		tempFile.delete();
		
		try {
			
			for(int i = 0; i < count; i++) {
				hashUsers.put(i, returnDeserializedUser(fromFile));
				userData.add(readFirstLine(fromFile));
				deleteContentOfFile(readFirstLine(fromFile), fromFile);
			}
			
			for(int i = 0; i < (count - 1); i++) {
				writeToExistingFile(userData.removeFirst(), fromFile);
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
