package Project_0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class ListOfUsers {

	static List<User> users = new ArrayList<>();
	private static int numberOfUsers;

	// This will return the user object that matches the supplied username
	public static User getUserByUsername(String username) {
		
		for (User user : users) {
			if (username.equals(user.getUsername())) {
				return user;
			}
		}
		return null;
	}
	
	// This will check if username already exists somewhere in the list
	public static boolean usernameExists(String username) {
		
		for (User user : users) {
			if (username.equals(user.getUsername())) {
				return true;
			}
		}
		return false;
	}
	
	// This will return the number of items in the list with isApproved set to 1 or pending
	public static int numberOfPending() {
		
		int numberOfPending = 0;
		for (User user : users) {
			if (user.isApproved() == 1) {
				numberOfPending++;
			}
		}
		return numberOfPending;
	}

	// This will add a new user to the list
	public static void addUser(User newUser) {

		users.add(newUser);
		setNumberOfUsers(getNumberOfUsers() + 1);
	}

	// This will read in serialized users
	public static void deserializeUsers() {

		if (getNumberOfUsers() == 0)
			return;
		// Read in each user from the specified file
		try (ObjectInputStream input = new ObjectInputStream(
				new FileInputStream(new File("src/main/resources/Users.txt")))) {
			for (int i = 0; i < getNumberOfUsers(); i++) {
				 users.add((User) input.readObject());				 
			}
			Initialization.logger.trace("Deserialization complete...");
		} catch (IOException ioe) {
			Initialization.logger.warn("There was an error reading user information from the system.");
		} catch (ClassNotFoundException cnfe) {
			Initialization.logger.warn("There was an error getting user class information from the system.");
		}
	}

	// This will serialize users to a file
	public static void serializeUsers() {

		if (getNumberOfUsers() == 0)
			return;
		// Write each user to the specified file
		try (ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(new File("Src/main/resources/Users.txt")));) {
			for (User user : users) {
				output.writeObject(user);
			}
			Initialization.logger.trace("Serialization complete...");
		} catch (IOException ioe) {
			Initialization.logger.warn("There was an error storing user information to the system.");
		}
	}

	public static int getNumberOfUsers() {
		return numberOfUsers;
	}

	public static void setNumberOfUsers(int numberOfUsers) {
		ListOfUsers.numberOfUsers = numberOfUsers;
	}
}
