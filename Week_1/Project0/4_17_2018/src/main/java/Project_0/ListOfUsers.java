package Project_0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ListOfUsers {
	
	static User[] users = new User[100]; // Array implementation may change if we need more space or dynamic space
	static int numberOfUsers;
	
	public static User searchUsers(String username) {
		for (int i = 0; i < numberOfUsers; i++) {
			if (username.equals(users[i].getUserName())) {
				return users[i];
			}
		}
		return null;
	}
	
	public static void deserializeUsers() {
		
		if (numberOfUsers == 0) return;
		// Read in each user from the specified file
		for (int i = 0; i < numberOfUsers; i++) {
			try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(new File("src/main/resources/Users.txt")))) {		
				users[i] = (User)input.readObject();
			} catch (IOException ioe) {
				System.out.println("There was an error getting user information from the system."
						+ " Please call (406)366-3972 for assistance");
			} catch (ClassNotFoundException cnfe) {
				System.out.println("There was an error getting user information from the system."
						+ " Please call (406)366-3972 for assistance");
			}
		}
	}
	
	public static void serializeUsers() {
		
		if (numberOfUsers == 0) return;
		// Write each user to the specified file
		for (int i = 0; i < numberOfUsers; i++) {
			try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(new File("Src/main/resources/Users.txt")));) {
				output.writeObject(users[i]);
			} catch (IOException ioe) {
				System.out.println("There was an error storing user information to the system."
						+ " Please call (406)366-3972 for assistance");
			}
		}
	}
}
