package Project_0;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Initialization {

	public static void initializeData() {
		// Any initializing code goes here to run before any console output
		if(firstTimeSetup()) return;
		readNumberOfUsers();
		ListOfUsers.deserializeUsers();
	}

	private static boolean firstTimeSetup() {

		// If the data file exists then don't run first time setup
		File file = new File("src/main/resources/data.txt");
		if (file.exists()) {
			return false;
		}

		// Create the first user which will be the main admin
		User admin = new User();
		Scanner input = new Scanner(System.in);

		// Set admin information
		System.out.println("Welcome to the _NAIN_IWFU_IHGWD_. This is the first time setup.\n");
		System.out.print("Please enter your first name: ");
		admin.setFirstName(input.nextLine());
		System.out.print("\nPlease enter your last name: ");
		admin.setLastName(input.nextLine());
		System.out.print("\nPlease enter your username: ");
		admin.setUserName(input.nextLine());
		System.out.print("\nPlease enter a password: ");
		admin.setPassword(input.nextLine());
		admin.promoteAdmin();
		
		// Put admin in the users array
		ListOfUsers.users.add(admin);
		ListOfUsers.numberOfUsers++;

		System.out.println("\n\nAdmin account created. When logging in with these credentials, you will be given"
				+ " special administrator priviledges. First time setup complete.");
		
		return true;
	}

	private static void readNumberOfUsers() {
			// Read the number of users from the data file
			try (FileReader input = new FileReader(new File("src/main/resources/data.txt"))) {
				ListOfUsers.numberOfUsers = input.read() - 48;
			} catch (IOException ioe) {
				System.out.println("Error initializing data. Please call (406)366-3972 for assistance.");
			}
	}
}
