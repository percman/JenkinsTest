package Project_0;

import java.util.Scanner;

public class UserLogin {

	// Method that directs user login
	public static void userLogin() {

		System.out.println("Hello! Welcome to _INSERT_NAME_HERE_\n");
		System.out.println("If you are an existing user,\t enter 1");
		System.out.println("If you are a new user,\t\t enter 2\n");
		System.out.println("\t\tTo quit,\t enter 9\n");
		// Scanner to collect input
		Scanner input = new Scanner(System.in);
		// Collect user choice and do appropriate action
		String choice = "0";
		while (!choice.equals("1") && !choice.equals("2") && !choice.equals("9")) {
			choice = input.next();
			if (choice.equals("1")) { // user login
				checkCredentials();
			} else if (choice.equals("2")) { // create new user
				System.out.println("Create new user method here");
			} else if (choice.equals("9")) { // Quit out of the program
				OnClose.programHouseKeeping(); // Regular program houseKeeping if user quits here since normal termination
				System.exit(0);
			} else { // Ensure the user supplies a valid entry
				System.out.println("Incorrect entry detected.\n Existing users enter 1.\n New users enter 2.\n Enter 9 to quit.");
			}
		}
		input.close();
	}
	
	// Method that will prompt user for their username and password and log them in if correct credentials supplied
	public static void checkCredentials() {
		Scanner input = new Scanner(System.in);
		System.out.print("Please enter your username: ");
		// Check against usernames here
		User currentUser = new User();
		currentUser = ListOfUsers.searchUsers(input.nextLine());	
		if(currentUser == null) {
			System.out.println("Username not found");
			return; // Rude implementation
		}
		// Check against passwords here
		System.out.print("\nPlease enter your password: ");
		if(!input.nextLine().equals(currentUser.getPassword())) {
			System.out.println("Incorrect password for user: " + currentUser.getUserName());
			return; // Rude implementation
		}
		System.out.println("Successfully logged in. Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName());
	}
}
