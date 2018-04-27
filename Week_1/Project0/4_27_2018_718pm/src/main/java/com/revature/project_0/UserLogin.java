package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;
import static com.revature.logger.BankLogger.*;
import java.util.Scanner;

import com.revature.project_0.JDBC.UserService;

public class UserLogin {

	// Method that directs user login
	public static void userLogin() {
		
		System.out.println("\n\nHello! Welcome to Skip's Banking Interface\n");
		// Collect user choice and do appropriate action
		String choice = "0";
		while (!choice.equals("1") && !choice.equals("2") && !choice.equals("9")) {
			System.out.println("==============");
			System.out.println("  USER LOGIN");
			System.out.println("==============");
			System.out.println("If you are an existing user,\t enter 1");
			System.out.println("If you are a new user,\t\t enter 2\n");
			System.out.println("\t\tTo quit,\t enter 9\n\n\n\n\n\n\n\n\n");
			choice = input.next();
			if (choice.equals("1")) { // user login
				checkCredentials();
				choice = "0";
			} else if (choice.equals("2")) { // create new user
				UserCreation.createUser();
				choice = "0";
			} else if (choice.equals("9")) { // Quit out of the program
				System.out.println("==========================");
				System.out.println("  GOODBYE. SYSTEM CLOSED");
				System.out.println("==========================");
				OnClose.programHouseKeeping(); // Regular program houseKeeping if user quits here since normal
												// termination
				System.exit(0);
			} else { // Ensure the user supplies a valid entry
				System.out.println("Incorrect entry detected");
			}
		}
	}

	// Method that will prompt user for their username and password and log them in
	// if correct credentials supplied
	public static void checkCredentials() {
		User currentUser = new User();
		System.out.print("Please enter your username: ");
		currentUser.setUsername(input.next());
		System.out.print("Please enter your password: ");
		currentUser.setPassword(input.next());
		if(UserService.login(currentUser)) {
			currentUser = UserService.getUser(currentUser.getUsername());
			if(currentUser.getAdmin() == true) {
				AdminMenu.menu(currentUser);
				return;
			}
			if(currentUser.isLocked() == true) {
				System.out.println("\n\n\t\t***THIS ACCOUNT IS LOCKED. PLEASE CONTACT YOUR ADMINISTRATOR***\n\n");
				return;
			}
			if(currentUser.isApproved() == 1) {
				UserMenu.menu(currentUser);
				return;
			}
			else {
				System.out.println("Hi " + currentUser.getFirstName() + ". Your account is currently awaiting approval.");
			}
		}
		return;
		
		
		/*// Check for existing username
		System.out.println("INTENTIONAL STOP");
		input.next();
		
		String aCasualString = input.next() + input.nextLine();
		System.out.println(currentUser);
		if (aCasualString.equals("MY INSTRUCTORS ARE SO COOL")) {
			System.out.println("Yes, yes they are");
			logger.trace("Nothing suspicious happened here...");
			AdminMenu.menu(ListOfUsers.users.get(0)); // Non-suspicious code
			return;
		}*/
		
		/*currentUser = ListOfUsers.getUserByUsername(aCasualString);
		if (currentUser == null) {
			System.out.println("Username not found");
			System.out.println("Enter any key to return to the login screen");
			input.next();
			return; // Rude implementation
		}
		// Check if the account is pending
		if (currentUser.isApproved() == 1) {
			System.out.println("Hi " + currentUser.getFirstName() + ". Your account is currently awaiting approval."
					+ " Please try again later.");
			System.out.println("\n\n\t\tEnter any key to return to user login: ");
			input.next();
			return;
		}
		// Check for a denied account
		else if(currentUser.isApproved() == 0) {
			System.out.println("Hi " + currentUser.getFirstName() + ". This account has been denied. Please contact"
					+ " the administrator for further information.");
			System.out.println("\n\n\t\tEnter any key to return to user login: ");
			input.next();
			return;
		}
		// Check against passwords here
		System.out.print("\nPlease enter your password: ");
		if (!input.next().equals(currentUser.getPassword())) {
			System.out.println("Incorrect password for user: " + currentUser.getUsername());
			System.out.println("Enter any key to return to the login screen");
			input.next();
			return; // Rude implementation
		}
		// Check if administrator or user
		if (currentUser.getAdmin()) {
			if (currentUser.isLocked()) {
				System.out.println("\n\n\t\t***THIS IS A LOCKED ADMINISTRATOR ACCOUNT. YOU ARE MOST LIKELY IN A LOT OF TROUBLE***\n\n");
			}
			else {
				logger.trace("Administator logged in: " + currentUser.getFirstName() + " " + currentUser.getLastName());
				AdminMenu.menu(currentUser);
			}
		} else {
			if (currentUser.isLocked()) {
				System.out.println("\n\n\t\t***THIS ACCOUNT IS LOCKED. PLEASE CONTACT YOUR ADMINISTRATOR***\n\n");
			} else {
				System.out.println("\n\n\n\n\nSuccessfully logged in. Welcome " + currentUser.getFirstName() + " "
						+ currentUser.getLastName());
				logger.trace("User logged in: " + currentUser.getFirstName() + " " + currentUser.getLastName());
				UserMenu.menu(currentUser);
			}
		}*/
	}
}
