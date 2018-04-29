package com.revature.user;

import static com.revature.scanner.BankScanner.input;

import com.revature.admin.Admin;
import com.revature.admin.AdminMenu;
import com.revature.factory.UserFactory;
import com.revature.factory.UserInterface;
import com.revature.jdbc.UserService;
import com.revature.project_0.OnClose;

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
				UserInterface userType = UserFactory.getUserType(2);
				if(userType instanceof Admin) {
					AdminMenu.menu(currentUser);
					return;
				}
				return;
			}
			if(currentUser.isLocked() == true) {
				System.out.println("\n\n\t\t***THIS ACCOUNT IS LOCKED. PLEASE CONTACT YOUR ADMINISTRATOR***\n\n");
				return;
			}
			if(currentUser.isApproved() == 1) {
				UserInterface userType = UserFactory.getUserType(1);
				if(userType instanceof User) {
					UserMenu.menu(currentUser);
					return;
				}		
			}
			else {
				System.out.println("\n\n\t\tHi " + currentUser.getFirstName() + ". Your account is currently awaiting approval\n\n");
			}
		}
		return;
	}
}
		
	