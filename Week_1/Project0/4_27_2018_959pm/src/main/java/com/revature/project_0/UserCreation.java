package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;
import static com.revature.logger.BankLogger.*;

import com.revature.project_0.JDBC.UserService;

public class UserCreation {

	public static void createUser() {
		// Create a user here
		System.out.println("=====================");
		System.out.println("  NEW USER CREATION");
		System.out.println("=====================");
		System.out.println("Welcome new user!");
		User newUser = new User();
		
		// Collect new user information
		System.out.print("\nPlease enter your first name: ");
		newUser.setFirstName(input.next());
		System.out.print("Please enter your last name: ");
		newUser.setLastName(input.next());
		System.out.print("Please create a username: ");
		newUser.setUsername(input.next());
		System.out.print("Please set your password: ");
		newUser.setPassword(input.next());
		System.out.println("\n\nThanks " + newUser.getFirstName());
		System.out.println("\n\t\t***YOUR ACCOUNT MUST BE APPROVED BEFORE YOU CAN ACCESS IT. THIS CAN TAKE UP TO 24 HOURS***\n\n\n\n");
		System.out.println("\t\tEnter any key to return to user login");
		input.next();
			
		// Log and add user to the list
		logger.trace("New Account: " + newUser.getFirstName() + " " + newUser.getLastName());
		UserService.insertUser(newUser);	
	}
}
