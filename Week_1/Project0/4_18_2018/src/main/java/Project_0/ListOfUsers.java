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
	static int numberOfUsers;

	public static User searchUsers(String username) {
		for (User user : users) {
			if (username.equals(user.getUserName())) {
				return user;
			}
		}
		return null;
	}

	public static void addUser(User newUser) {

		users.add(newUser);
		numberOfUsers++;
	}

	public static void deserializeUsers() {

		if (numberOfUsers == 0)
			return;
		// Read in each user from the specified file
		try (ObjectInputStream input = new ObjectInputStream(
				new FileInputStream(new File("src/main/resources/Users.txt")))) {
			for (int i = 0; i < numberOfUsers; i++) {
				 users.add((User) input.readObject());
			}
		} catch (IOException ioe) {
			System.out.println("There was an error getting user information from the system."
					+ " Please call (406)366-3972 for assistance");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("There was an error getting user information from the system."
					+ " Please call (406)366-3972 for assistance");
		}
	}

	public static void serializeUsers() {

		if (numberOfUsers == 0)
			return;
		// Write each user to the specified file
		try (ObjectOutputStream output = new ObjectOutputStream(
				new FileOutputStream(new File("Src/main/resources/Users.txt")));) {
			for (User user : users) {
				output.writeObject(user);
			}
		} catch (IOException ioe) {
			System.out.println("There was an error storing user information to the system."
					+ " Please call (406)366-3972 for assistance");
		}
	}
}
