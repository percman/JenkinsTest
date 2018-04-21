package Project_0;

import java.util.Scanner;

public class UserMenu {
	public static void menu(User user) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("=============");
			System.out.println("  USER MENU");
			System.out.println("=============");
			System.out.println("To display account information\tEnter 1");
			System.out.println("To make a deposit\t\tEnter 2");
			System.out.println("To make a withdrawal\t\tEnter 3");
			System.out.println("For account management\t\tEnter 4\n");
			System.out.println("\t\tTo log out\tEnter 9");
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			choice = input.next();
			switch (choice) {
			case "1": {
				System.out.println("=======================");
				System.out.println("  ACCOUNT INFORMATION");
				System.out.println("=======================");
				System.out.println("Account Number: " + user.getAccountNumber());
				System.out.println("Account Holder Name: " + user.getFirstName() + " " + user.getLastName());
				System.out.println("Account username: " + user.getUsername());
				System.out.println("Current Balance: $" + user.getAccountBalance());
				System.out.println("\n\n\t\tEnter any key to return to user menu");
				input.next();
				System.out.println("\n\n\n\n\n\n");
				choice = "0";
				break;
			}

			case "2": {
				System.out.println("============");
				System.out.println("  DEPOSITS");
				System.out.println("============");
				System.out.println("How much would you like to deposit?");
				System.out.println("Account Balance: $" + user.getAccountBalance());
				try {
					String deposit = input.next() + input.nextLine();
					double newBalance = Double.parseDouble(user.getAccountBalance()) + Double.parseDouble(deposit);
					user.setAccountBalance(newBalance);
					System.out.println("\t\tDeposited!");
					Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " made a deposit.");
				} catch (NumberFormatException nfe) {
					System.out.println("Please enter a valid number for deposit");
					Initialization.logger.info("Entry error");
				} finally {
					choice = "0";
				}
				break;
			}

			case "3": {
				System.out.println("===============");
				System.out.println("  WITHDRAWALS");
				System.out.println("===============");
				System.out.println("How much would you like to withdraw?");
				System.out.println("Account Balance: $" + user.getAccountBalance());
				try {
					String deposit = input.next() + input.nextLine();
					double newBalance = Double.parseDouble(user.getAccountBalance()) - Double.parseDouble(deposit);
					if (newBalance < 0) {
						System.out.println("\n\n\t\t***INSUFFICIENT FUNDS***");
						Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " insufficient funds.");
					} else {
						user.setAccountBalance(newBalance);
						System.out.println("\t\tWithdrawn!");
						Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " made a withdrawal.");
					}
				} catch (NumberFormatException nfe) {
					System.out.println("Please enter a valid number for deposit");
					Initialization.logger.info("Entry error");
				} finally {
					choice = "0";
				}
				break;
			}

			case "4": {
				optionOne(user);
				choice = "0";
				break;
			}

			case "9": {
				System.out.println("Successfully logged out. Bye " + user.getFirstName() + "!");
				Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " logged out.");
				return;
			}

			default: {

			}

			}

		}
	}

	public static void optionOne(User user) {
		Scanner input = new Scanner(System.in);
		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("======================");
			System.out.println("  ACCOUNT MANAGEMENT");
			System.out.println("======================");
			System.out.println("To change first name, Enter 1 followed by the new name");
			System.out.println("To change last name, Enter 2 followed by the last name");
			System.out.println("To change username, Enter 3 followed by the new username");
			System.out.println("To change password, Enter 4 followed by the new password");
			System.out.println("\t\t\nTo return\tEnter 9");
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			choice = input.next();
			switch (choice) {
			case "1": {
				choice = input.nextLine();
				user.setFirstName(choice.replaceAll(" ", ""));
				System.out.println("First name changed to: " + user.getFirstName());
				Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " first name change.");
				break;
			}
			case "2": {
				choice = input.nextLine();
				user.setLastName(choice.replaceAll(" ", ""));
				System.out.println("Last name changed to: " + user.getLastName());
				Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " last name change.");
				break;
			}
			case "3": {
				choice = input.nextLine();
				user.setUsername(choice.replaceAll(" ", ""));
				System.out.println("Username changed to: " + user.getUsername());
				Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " username change.");
				break;
			}
			case "4": {
				choice = input.nextLine();
				user.setPassword(choice.replaceAll(" ", ""));
				System.out.println("Password changed to: " + user.getPassword());
				Initialization.logger.trace("User: " + user.getFirstName() + " " + user.getLastName() + " password change.");
				break;
			}
			default: {
				System.out.println("Please enter a valid option");
				break;
			}			
			case "9": {
				return;
			}
			}
		}
	}
}
