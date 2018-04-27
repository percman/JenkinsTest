package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;
import static com.revature.logger.BankLogger.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import com.revature.project_0.JDBC.UserService;


public class Initialization {
	
	public static void initializeData() {
		// Any initializing code goes here to run before any console output
		logger.trace("Initializing...");
		firstTimeSetup();
		readNumberOfUsers();
		ListOfUsers.deserializeUsers();
	}

	private static void firstTimeSetup() {
		// If the data file exists then don't run first time setup
		/*File file = new File("src/main/resources/data.txt");
		if (file.exists()) {
			logger.trace("Skipping first time setup...");
			return false;
		}*/

		// Check for existing admin
		if(UserService.checkForAdmin()) {
			return;
		}
		
		// Create the first user which will be the main admin
		User admin = MainAdmin.getInstance();
		//Scanner input = new Scanner(System.in);

		// Set admin information
		System.out.println("====================");
		System.out.println("  FIRST TIME SETUP");
		System.out.println("====================");
		System.out.println("Welcome to the Skip's Banking Interface\n");
		System.out.print("Please enter your first name: ");
		admin.setFirstName(input.nextLine());
		System.out.print("\nPlease enter your last name: ");
		admin.setLastName(input.nextLine());
		System.out.print("\nPlease enter your username: ");
		admin.setUsername(input.nextLine());
		System.out.print("\nPlease enter a password: ");
		admin.setPassword(input.nextLine());
		//admin.promoteAdmin();
		//admin.approve();
		
		// Put admin in the users array
		UserService.insertUser(admin);
		admin = UserService.getUser(admin.getUsername());
		UserService.promoteAdmin(admin.getAccountNumber());
		
		//ListOfUsers.users.add(admin);
		//ListOfUsers.setNumberOfUsers(ListOfUsers.getNumberOfUsers() + 1);

		System.out.println("\n\n\t\t***ADMINISTRATOR ACCOUNT CREATED***\n\nWhen logging in with these credentials, you will be given"
				+ " special administrator priviledges. First time setup complete.");
		logger.trace("First time setup complete...");
		//input.close();
	}

	// Read any non-serialized persistent data. Currently only has numberOfUsers
	private static void readNumberOfUsers() {
			// Read the number of users from the data file
			/*try (FileReader input = new FileReader(new File("src/main/resources/data.txt"))) {
				ListOfUsers.setNumberOfUsers(input.read() - 48);
			} catch (IOException ioe) {
				logger.warn("Error initializing data");
			}*/
	}
}
