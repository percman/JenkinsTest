package com.revature.users;

import static com.revature.readwrite.ReadWrite.*;
import static com.revature.users.SerializationOfUsers.*;

import java.util.ArrayList;
import java.util.HashMap;


public class Admin extends User{

	private static final long serialVersionUID = -590741263244204223L;

	// Sloppy way to read in user information and write it to a file
	// takes input for each property of User newUser, and serializes it to File 'userFile'
	public static void addUserAsAdmin() {

		System.out.println("Please enter the username: ");
		String username = inputLine();
		
		System.out.println("Please enter your password (case-sensitive)");
		String password = inputLine();
		
		System.out.println("Is this person an admin? T or F");
		String adminstatusstring = inputLine(); 
		boolean adminstatus;
		if(adminstatusstring.equals("T"))
			adminstatus = true;
		else 
			adminstatus = false;
		
		System.out.println("What is the beginning balance?");
		String balancestring = inputLine();
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

	// Checks if a given User has entered their correct password
	// * requires a HashMap to have been created by reading userFile *
	// takes HashMap 'userHashData' and checks each element for equality to String 'username', then to password
	public static boolean passwordCheck(HashMap<Integer, User> userHashData, String username, String password) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				if(userHashData.get(i).getPassword().equals(password))
					return true;
		
		return false;
	}
	
	// Checks if a given User is an Admin
	// * requires a HashMap to have been created by reading userFile *
	// takes HashMap 'userHashData' and checks each element for equality to String 'username' 
	public static boolean adminCheck(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).isAdminStatus();
		
		return false;
	}

	// Return's a given User's balance
	// * requires a HashMap to have been created by reading userFile *
	// takes HashMap 'userHashData' and checks each element for equality to String 'username', then returns their balance
	public static double checkBalance(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);

		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).getBalance();
		
		return 0;
	}

	
	public static void changeBalance(HashMap<Integer, User> userHashData, String username, double changedAmount) {
		int count = lineCount(userFile);
		ArrayList<String> userData = new ArrayList<>();
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username)) {	
				System.out.println(userHashData.get(i));
				userHashData.put(i, new User(userHashData.get(i).getName(), userHashData.get(i).getPassword(), userHashData.get(i).isAdminStatus(), userHashData.get(i).isLocked(), userHashData.get(i).getBalance() + changedAmount));
				System.out.println(userHashData.get(i));
				
				// just to ensure extra data isn't accidently written into user data, such as null
				tempFile.delete();
					
				for(int n = 0; n < (count - 1); n++) {
					if( n == i) {
						serializeUser(userHashData.get(i), userFile);
						userData.add(readFirstLine(serializedUserFile));
					}
					else {
						userData.add(readFirstLine(userFile));
						deleteContentOfFile(readFirstLine(userFile), userFile);
					}
				}
				
				for(int n = 0; n < (count - 1); n++) {
					writeToExistingFile(userData.get(n), userFile);
				}
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
