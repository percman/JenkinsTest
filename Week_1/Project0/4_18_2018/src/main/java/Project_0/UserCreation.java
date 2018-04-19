package Project_0;

import java.util.Scanner;

public class UserCreation {

	public static void createUser() {
		// Create a user here
		System.out.println("Welcome new user!");
		Scanner input = new Scanner(System.in);
		User newUser = new User();
		System.out.print("\nPlease enter your first name: ");
		newUser.setFirstName(input.nextLine());
		System.out.print("Please enter your last name: ");
		newUser.setLastName(input.nextLine());
		System.out.print("Please create a username: ");
		newUser.setUserName(input.nextLine());
		System.out.print("Please set your password: ");
		newUser.setPassword(input.next());
		
		System.out.println("\n\nThanks " + newUser.getFirstName() + ". Below are your account number and starting balance.");
		newUser.setAccountNumber((int)(Math.random() * 1000000));
		System.out.println("Account number: " + newUser.getAccountNumber());
		newUser.setAccountBalance(0.00f);
		System.out.println("Account balance: $" + newUser.getAccountBalance());
		
		ListOfUsers.addUser(newUser);
	}
}
