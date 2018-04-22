package com.revature.users;

import static com.revature.readwrite.ReadWrite.inputLine;
import static com.revature.readwrite.ReadWrite.lineCount;
import static com.revature.readwrite.ReadWrite.writeToAFileFromAFile;
import static com.revature.users.SerializationOfUsers.serializeUser;
import static com.revature.users.SerializationOfUsers.serializedUserFile;
import static com.revature.users.SerializationOfUsers.userFile;

import java.util.HashMap;

/**
 * 
 * @author Adalah
 *
 * Quick note!
 * much of this code is just simple checks on a given user
 * this probably could have been significantly simplified, but this took the least amount of time to think and write
 * 
 * Still getting used to writing in Java, and this was a fun learning curve. 
 * 
 * Code is never complete! 
 *
 */

public class Admin extends User{

	private static final long serialVersionUID = -590741263244204223L;

	/* Prompts a User creation 
	 * Takes a given username, password, and balance, then creates a User 
	 */
	public static void createUser() {
		
		double startingbalance = 0;

		System.out.println("Please enter the username: ");
		String username = inputLine();
		
		System.out.println("Please enter your password (case-sensitive)");
		String password = inputLine();
		
		
		System.out.println("What is the beginning balance?");
		try {
			String balancestring = inputLine();
			Double doubleObject = new Double(balancestring);
			startingbalance = doubleObject.doubleValue();
		} catch (NumberFormatException nfe) {
			System.out.println("That's not even a number!");
			System.out.println("No soup for you!");
		}
		
		// User (username, password, adminstatus, locked, startingbalance);
		User newUser = new User (username, password, false, true, startingbalance);
		
		serializeUser(newUser, serializedUserFile);
		writeToAFileFromAFile(userFile, serializedUserFile);
		
		serializedUserFile.delete();
					
		System.out.println("The new user " + username + " has been created.");
		System.out.println("This account will be locked until an admin approves the account.");
	}

	/* Easy way to have User objects loaded 
	 * Can be used for testing and faster User creation 
	 * Allows for direct input of data to create a User, then saves it
	 */
	public static User addUser(String username, String password, boolean adminstatus, boolean locked, double startingbalance) {
		
		User newUser = new User (username, password, adminstatus, locked, startingbalance);
		
		serializeUser(newUser, serializedUserFile);
		writeToAFileFromAFile(userFile, serializedUserFile);
		
		serializedUserFile.delete();
					
		//System.out.println("The new user " + username + " has been created.");
		
		return newUser;
	}

	/* A simple check for if a User is in the HashMap
	 * Takes HashMap and searches it using a for loop for username
	 */
	public static boolean userExists(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return true;
		
		return false;
	}
	
	/* Checks if a given User has entered their correct password
	 * Takes HashMap 'userHashData' and checks each element for equality to String 'username', then to password
	 * Note: this probably could have been simplified by an AdminCheck or something, but this is easy enough
	 */
	public static boolean passwordCheck(HashMap<Integer, User> userHashData, String username, String password) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				if(userHashData.get(i).getPassword().equals(password))
					return true;
		
		return false;
	}
	
	/* Returns a given User's lock status
	 * Takes HashMap 'userHashData' and checks each element for equality to String 'username', then returns locked status
	 * Note that "locked" and "approved" are stored as the same thing in this program
	 */
	public static boolean lockStatus(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).isLocked();
		
		return true;
	}
	
	
	/* Returns a given User's admin status
	 * takes HashMap 'userHashData' and checks each element for equality to String 'username' 
	 */
	public static boolean adminCheck(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).isAdminStatus();
		
		return false;
	}

	/* Return's a given User's balance
	 * takes HashMap 'userHashData' and checks each element for equality to String 'username', then returns their balance
	 */
	public static double checkBalance(HashMap<Integer, User> userHashData, String username) {
		int count = lineCount(userFile);

		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username))
				return userHashData.get(i).getBalance();
		
		return 0;
	}

	/* Changes a given User's balance in the HashMap and in the stored file
	 * finds the User 'username' in the HashMap, updates their balance in HashMap, then adds the user (using addUser)
	 */
	public static void changeBalance(HashMap<Integer, User> userHashData, String username, double changedAmount) {
		int count = lineCount(userFile);
		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username)) {	
				User updatedUser = new User(userHashData.get(i).getName(), 
						userHashData.get(i).getPassword(), 
						userHashData.get(i).isAdminStatus(), 
						userHashData.get(i).isLocked(), 
						userHashData.get(i).getBalance() + changedAmount);
				userHashData.put(i, updatedUser);
				userFile.delete();
				for(int n = 0; n < (count - 1); n++) {
					addUser(userHashData.get(n).getName(), 
							userHashData.get(n).getPassword(), 
							userHashData.get(n).isAdminStatus(), 
							userHashData.get(n).isLocked(), 
							userHashData.get(n).getBalance());
				}
			}
	}
	
	/* Changes a given User's lock in the HashMap and in the stored file
	 * finds the User 'username' in the HashMap, updates their lock in HashMap, then adds the user (using addUser)
	 */
	public static void userLock(HashMap<Integer, User> userHashData, String username, boolean lock) {
		int count = lineCount(userFile);

		
		for(int i = 0; i < (count - 1); i++)
			if(userHashData.get(i).getName().equals(username)) {	
				User updatedUser = new User(userHashData.get(i).getName(), 
						userHashData.get(i).getPassword(), 
						userHashData.get(i).isAdminStatus(), 
						lock, 
						userHashData.get(i).getBalance());
				userHashData.put(i, updatedUser);
				userFile.delete();
				for(int n = 0; n < (count - 1); n++) {
					addUser(userHashData.get(n).getName(), 
							userHashData.get(n).getPassword(), 
							userHashData.get(n).isAdminStatus(), 
							userHashData.get(n).isLocked(), 
							userHashData.get(n).getBalance());
				}
			}
	}
}
