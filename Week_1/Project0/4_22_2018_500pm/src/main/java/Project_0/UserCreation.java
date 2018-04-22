package Project_0;

import java.util.Scanner;

public class UserCreation {

	public static void createUser() {
		// Create a user here
		System.out.println("=====================");
		System.out.println("  NEW USER CREATION");
		System.out.println("=====================");
		System.out.println("Welcome new user!");
		Scanner input = new Scanner(System.in);
		User newUser = new User();
		
		// Collect new user information
		System.out.print("\nPlease enter your first name: ");
		newUser.setFirstName(input.nextLine());
		System.out.print("Please enter your last name: ");
		newUser.setLastName(input.nextLine());
		System.out.print("Please create a username: ");
			
		// Check if the username already exists
		String username = input.nextLine();
		while (ListOfUsers.usernameExists(username)) {
			System.out.println("\n\t\t" + username + " already exists. \n\nPlease try another username: ");
			username = input.nextLine();
		}
		newUser.setUsername(username);
		
		// Collect more information
		System.out.print("Please set your password: ");
		newUser.setPassword(input.next());
		System.out.println("\n\nThanks " + newUser.getFirstName() + ". Below are your account number and starting balance.");
		newUser.setAccountNumber((int)(Math.random() * 1000000));
		System.out.println("Account number: " + newUser.getAccountNumber());
		newUser.setAccountBalance(0.00f);
		System.out.println("Account balance: $" + newUser.getAccountBalance());
		newUser.setPending();
		System.out.println("\n\t\t***YOUR ACCOUNT MUST BE APPROVED BEFORE YOU CAN ACCESS IT. THIS CAN TAKE UP TO 24 HOURS***\n\n\n\n");
		System.out.println("\t\tEnter any key to return to user login");
		input.next();
			
		// Log and add user to the list
		Initialization.logger.trace("New Account: " + newUser.getFirstName() + " " + newUser.getLastName());
		ListOfUsers.addUser(newUser);
		
	}
}
