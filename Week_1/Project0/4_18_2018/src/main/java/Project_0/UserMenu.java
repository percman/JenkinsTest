package Project_0;

import java.util.Scanner;

public class UserMenu {
	public static void menu(User user) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("\nplease select from 1 of the following options");
			System.out.println("To display account information\tEnter 1");
			System.out.println("To make a deposit\t\tEnter 2");
			System.out.println("To make a withdrawal\t\tEnter 3");
			System.out.println("For account management\t\tEnter 4\n");
			System.out.println("\t\tTo log out\tEnter 9");
			choice = input.next();
			switch (choice) {
			case "1": {
				System.out.println("Account information:");
				System.out.println("Account Number: " + user.getAccountNumber());
				System.out.println("Account Holder Name: " + user.getFirstName() + " " + user.getLastName());
				System.out.println("Account username: " + user.getUserName());
				System.out.println("Current Balance: $" + user.getAccountBalance());
				System.out.println("\n\n\t\tEnter any key to return to user menu");
				input.next();
				System.out.println("\n\n\n\n\n\n");
				choice = "0";
				break;
			}
			case "2": {
				System.out.println("How much would you like to deposit?");
				System.out.println("Account Balance: $" + user.getAccountBalance());
				try{
					String deposit = input.next() + input.nextLine();
					float newBalance = user.getAccountBalance() + Float.parseFloat(deposit);
					user.setAccountBalance(newBalance);
					System.out.println("\t\tDeposited!");
				} catch (NumberFormatException nfe) {
					System.out.println("Please enter a valid number for deposit");
				} finally {
					choice = "0";
				}
				break;
			}
			case "3": {

			}
			case "4": {

			}
			case "9": {
				System.out.println("Successfully logged out. Bye " + user.getFirstName() + "!");
				return;
			}
			default: {

			}

			}

		}
	}
}
