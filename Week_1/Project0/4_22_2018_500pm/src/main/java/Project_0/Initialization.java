package Project_0;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;


public class Initialization {

	public static final Logger logger = Logger.getLogger(Initialization.class);
	
	public static void initializeData() {
		// Any initializing code goes here to run before any console output
		logger.trace("Initializing...");
		if(firstTimeSetup()) return;
		readNumberOfUsers();
		ListOfUsers.deserializeUsers();
	}

	private static boolean firstTimeSetup() {
		// If the data file exists then don't run first time setup
		File file = new File("src/main/resources/data.txt");
		if (file.exists()) {
			logger.trace("Skipping first time setup...");
			return false;
		}

		// Create the first user which will be the main admin
		User admin = MainAdmin.getInstance();
		Scanner input = new Scanner(System.in);

		// Set admin information
		System.out.println("====================");
		System.out.println("  FIRST TIME SETUP");
		System.out.println("====================");
		System.out.println("Welcome to the _INSERT_NAME_HERE_\n");
		System.out.print("Please enter your first name: ");
		admin.setFirstName(input.nextLine());
		System.out.print("\nPlease enter your last name: ");
		admin.setLastName(input.nextLine());
		System.out.print("\nPlease enter your username: ");
		admin.setUsername(input.nextLine());
		System.out.print("\nPlease enter a password: ");
		admin.setPassword(input.nextLine());
		admin.promoteAdmin();
		admin.approve();
		
		// Put admin in the users array
		ListOfUsers.users.add(admin);
		ListOfUsers.setNumberOfUsers(ListOfUsers.getNumberOfUsers() + 1);

		System.out.println("\n\n\t\t***ADMINISTRATOR ACCOUNT CREATED***\n\nWhen logging in with these credentials, you will be given"
				+ " special administrator priviledges. First time setup complete.");
		logger.trace("First time setup complete...");
		input.close();
		return true;
	}

	// Read any non-serialized persistent data. Currently only has numberOfUsers
	private static void readNumberOfUsers() {
			// Read the number of users from the data file
			try (FileReader input = new FileReader(new File("src/main/resources/data.txt"))) {
				ListOfUsers.setNumberOfUsers(input.read() - 48);
			} catch (IOException ioe) {
				logger.warn("Error initializing data");
			}
	}
}
