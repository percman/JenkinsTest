package com.revature.users;

import static com.revature.readwrite.ReadWrite.aquireLine;
import static com.revature.readwrite.ReadWrite.lineCount;
import static com.revature.readwrite.ReadWrite.writeToAFileFromAFile;
import static com.revature.users.SerializationOfUsers.serializeUser;
import static com.revature.users.SerializationOfUsers.serializedUserFile;
import static com.revature.users.SerializationOfUsers.userFile;

import java.util.HashMap;


public class Admin extends User{
	
	// Sloppy way to read in user information and write it to a file
	// takes input for each property of User newUser, and serializes it to File 'userFile'
	public static void addUserAsAdmin() {

		System.out.println("Please enter the username: ");
		String username = aquireLine();
		
		System.out.println("Please enter your password (case-sensitive)");
		String password = aquireLine();
		
		System.out.println("Is this person an admin? T or F");
		String adminstatusstring = aquireLine(); 
		boolean adminstatus;
		if(adminstatusstring.equals("T"))
			adminstatus = true;
		else 
			adminstatus = false;
		
		System.out.println("What is the beginning balance?");
		String balancestring = aquireLine();
		Double doubleObject = new Double(balancestring);
		double startingbalance = doubleObject.doubleValue();
		
		boolean locked = true;
		
		User newUser = new User (username, password, adminstatus, locked, startingbalance);
		
		serializeUser(newUser, serializedUserFile);
		writeToAFileFromAFile(userFile, serializedUserFile);
		
		serializedUserFile.delete();
					
		System.out.println("The new user " + username + " has been created.");
		System.out.println("You will be locked until an admin approves the account.");
	}
	
	// Easy way to have User objects loaded for testing 
	// allows for direct input by taking String etc 
	public static User addUserAsAdmin(String username, String password, boolean adminstatus, boolean locked, double startingbalance) {
		
		User newUser = new User (username, password, adminstatus, locked, startingbalance);
		
		serializeUser(newUser, serializedUserFile);
		writeToAFileFromAFile(userFile, serializedUserFile);
		
		serializedUserFile.delete();
					
		System.out.println("The new user " + username + " has been created.\n");
		
		return newUser;
	}

	// Checks if a given User is an Admin
	// * requires a HashMap to have been created by reading userFile *
	// takes HashMap 'userHashData' and checks each element for equality to String 'username' 
	public static boolean passwordCheck(HashMap<Integer, User> userHashData, String username, String password) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				if(userHashData.get(i).getPassword().equals(password))
					return true;
		
		return false;
	}
	
	public static boolean adminCheck(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).isAdminStatus();
		
		return false;
	}

	public static double checkBalance(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);

		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).getBalance();
		
		return 0;
	}

	public static void changeBalance(HashMap<Integer, User> userHashData, String username, double changedAmount) {
		int count = lineCount(userFile);

		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username)) {
				
			}
	}
	
	public static boolean changeUsername(HashMap<Integer, User> userHashData, String username, String newUsername) {
		int count = lineCount(userFile);

		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username)) {
				return true;
			}
		
		return false;
	}
	
	public static boolean userLock(HashMap<Integer, User> userHashData, String username, String newUsername) {
		int count = lineCount(userFile);

		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username)) {
				return true;
			}
		
		return false;
	}

}
