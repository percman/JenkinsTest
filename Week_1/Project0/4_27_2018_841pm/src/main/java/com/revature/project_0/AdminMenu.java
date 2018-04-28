package com.revature.project_0;

import static com.revature.scanner.BankScanner.*;
import static com.revature.logger.BankLogger.*;
import java.util.ConcurrentModificationException;

import com.revature.project_0.JDBC.UserService;

public class AdminMenu {

	/*
	 * public static void menu(MainAdmin admin) { System.out.println("Main Admin");
	 * } under construction
	 */

	// All of the admins options are here
	public static void menu(User admin) {
		System.out.println("\n\n\n\n\nAdministrator priviledges detected. Special Access and permissions granted.\n");
		// Scanner input = new Scanner(System.in);

		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("======================");
			System.out.println("  ADMINISTRATOR MENU");
			System.out.println("======================");
			System.out.println("To view all existing users\t\tEnter 1");
			System.out.println("To view pending account requests\tEnter 2");
			System.out.println("To view total statement\t\t\tEnter 3");
			System.out.println("To view all transactions\t\tEnter 4");
			System.out.println("For user menu\t\t\t\tEnter 5");
			System.out.println("\n\n\t\tTo log out\t\tEnter 9");
			System.out.println("\n\n\n\n\n\n\n\n\n\n");
			choice = input.next();
			System.out.println(""); // whitespace
			switch (choice) {
			case "1": { // View all accounts
				optionOne(admin);
				choice = "0";
				break;
			}
			case "2": { // View all pending account requests
				optionTwo(admin);
				choice = "0";
				break;
			}
			case "3": { // View total balance
				System.out.println("=========================");
				System.out.println("  CURRENT TOTAL BALANCE");
				System.out.println("=========================");
				double totalBalance = 0;
				for (User user : ListOfUsers.users) {
					totalBalance += Double.parseDouble(user.getAccountBalance());
				}
				System.out.println("Current bank balance: $" + totalBalance);
				System.out.println("Session transactions total: " + "UNIMPLEMENTED");
				System.out.println();
			}
			case "4": { // View total transactions/all transactions
				System.out.println("Not implemented");
				break;
			}
			case "5": { // let the admin access his or her user menu
				logger.trace("User: " + admin.getFirstName() + " " + admin.getLastName() + " logged in.");
				UserMenu.menu(admin);
				break;
			}
			default: { // Ensure correct entries
				System.out.println("\n\nPlease enter a valid menu option\n\n");
			}
			case "9": { // Log out
				System.out.println("Successfully logged out. Bye " + admin.getFirstName());
				logger.trace("Admin: " + admin.getFirstName() + " " + admin.getLastName() + " logged out.");
				return;
			}

			}

		}
		// Secret menu option
	}

	// SubMenu of option 1
	public static void optionOne(User admin) {

		// Scanner input = new Scanner(System.in);
		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("==================");
			System.out.println("  EXISTING USERS");
			System.out.println("==================");
			for (User user : UserService.getAllUsers()) {
				if(user.isApproved() == 1) {
					System.out.println(user);
				}
			}
			System.out.println("===================");
			System.out.println("  ACCOUNT OPTIONS");
			System.out.println("===================");
			System.out.println("To lock an account, Enter 1 followed by the number of the"
					+ " account you want to lock (eg 1 4 to lock account 4)");
			System.out.println("To unlock an account, Enter 2 followed by the number of the"
					+ " account you want to unlock (eg 2 4 to unlock account 4)");
			System.out.println("To delete an account, Enter 3 followed by the number of the"
					+ " account you want to delete (eg 3 2 to delete account 2)");
			System.out.println("To promote an account to admin status, Enter 4 followed by the number of the"
					+ " account you want to promote (eg 4 5 to promote account 5)");
			System.out.println("To demote an account to user status, Enter 5 followed by the number of the"
					+ " account you want to demote (eg 5 3 to demote account 3)");
			System.out.println("\n\t\tTo return to main admin menu\t Enter 9\n\n");
			choice = input.next();
			switch (choice) {

			
			
			case "1": { // Lock a single account
				choice = input.nextLine();
				try {
					if(UserService.lockUser(Integer.parseInt(choice.replaceAll(" ", "")))) {
						logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " locked an account.");
					}
					break;
				} catch (NumberFormatException nfe) { // This will detect if a non integer is input by the user
					System.out.println("Incorrect entry detected. You must input 2 numbers seperated by a space");
					logger.info("Entry error");
					break;
				}
			}

			
			
			case "2": { // Unlock a single account
				choice = input.nextLine();
				try {
					if(UserService.unlockUser(Integer.parseInt(choice.replaceAll(" ", "")))) {
						logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " unlocked an account.");
					}
					break;
				} catch (NumberFormatException nfe) { // This will detect if a non integer is input by the user
					System.out.println("Incorrect entry detected. You must input 2 numbers seperated by a space");
					logger.info("Entry error");
					break;
				}
			}

			
			
			case "3": { // Delete an account
				choice = input.nextLine();
				try {
					if(UserService.deleteUser(Integer.parseInt(choice.replaceAll(" ", "")))) {
						logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " deleted an account.");
					}
					break;
				} catch (NumberFormatException nfe) { // This will detect if a non integer is input by the user
					System.out.println("Incorrect entry detected. You must input 2 numbers seperated by a space");
					logger.info("Entry error");
					break;
				} 
			}
			
			
			
			/*case "4": { // Promote an account to admin
				choice = input.nextLine();
				try {
					if (ListOfUsers.users.indexOf(admin) == 0) { // Only give this access to the main admin
						if (ListOfUsers.users.indexOf(admin) + 1 == Integer.parseInt(choice.replaceAll(" ", ""))) {
							System.out.println("You are already an Admin!"); // Don't allow changes to the main admin
						} else {
							ListOfUsers.users.get(Integer.parseInt(choice.replaceAll(" ", "")) - 1).promoteAdmin();
							System.out.println("Account promoted to Admin!"); // Unlock user
							logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
									+ " promoted an account.");
						}
						break;
					} else {
						System.out.println("\n\n\t\t***YOU DO NOT HAVE ACCESS TO THIS FEATURE***");
						logger.info("Administator: " + admin.getFirstName() + " " + admin.getLastName()
								+ " denied access.");
						break;
					}
				} catch (NumberFormatException nfe) { // This will detect if a non integer is input by the user
					System.out.println("Incorrect entry detected. You must input 2 numbers seperated by a space");
					logger.info("Entry error");
					break;
				} catch (IndexOutOfBoundsException ioobe) { // This will detect if the user tries to access an index not
															// on the list
					System.out.println("\nThat user does not exist!\n\n");
					logger.info("Entry error");
					break;
				}
			}
			case "5": { // Demote an admin
				choice = input.nextLine();
				try {
					if (ListOfUsers.users.indexOf(admin) == 0) { // Only give this access to the main admin
						if (ListOfUsers.users.indexOf(admin) + 1 == Integer.parseInt(choice.replaceAll(" ", ""))) {
							System.out.println("You cannot demote yourself!"); // Don't allow changes to the main admin
						} else {
							ListOfUsers.users.get(Integer.parseInt(choice.replaceAll(" ", "")) - 1).demoteAdmin();
							System.out.println("Account demoted to user!"); // Unlock user
							logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
									+ " demoted an account.");
						}
						break;
					} else {
						System.out.println("\n\n\t\t***YOU DO NOT HAVE ACCESS TO THIS FEATURE***");
						logger.info("Administator: " + admin.getFirstName() + " " + admin.getLastName()
								+ " denied access.");
						break;
					}
				} catch (NumberFormatException nfe) { // This will detect if a non integer is input by the user
					System.out.println("Incorrect entry detected. You must input 2 numbers seperated by a space");
					logger.info("Entry error");
					break;
				} catch (IndexOutOfBoundsException ioobe) { // This will detect if the user tries to access an index not
															// on the list
					System.out.println("\nThat user does not exist!\n\n");
					logger.info("Entry error");
					break;
				}
			}*/
			default: { // catch entry errors
				System.out.println("\n\nPlease enter a valid menu option");
				break;
			}
			case "9": { // return to main admin menu
				return;
			}
			}

		}
	}

	// SubMenu of optionTwo
	public static void optionTwo(User admin) {

		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("====================");
			System.out.println("  PENDING ACCOUNTS");
			System.out.println("====================");
			for (User user : UserService.getAllUsers()) {
				if(user.isApproved() == 0) {
					System.out.println(user);
				}
			}
			System.out.println("===================");
			System.out.println("  ACCOUNT OPTIONS");
			System.out.println("===================");
			System.out.println("To approve a single account, Enter 1 followed by the number of the "
					+ "account that you wish to approve (eg 1 7 to approve account 7)");
			System.out.println("To approve all accounts, Enter 2");
			System.out.println("To deny a single account, Enter 3 followed by the number of the "
					+ "account that you wish to approve (eg 3 4 to deny account)");
			System.out.println("To deny all accounts, Enter 4");
			System.out.println("You view denied accounts, Enter 5");
			System.out.println("To permanently delete all denied accounts, Enter 6");
			System.out.println("To restore denied accounts back to pending, Enter 7");
			System.out.println("\n\t\tTo return to administrator menu, Enter 9\n\n\n\n");
			choice = input.next();
			switch (choice) {
			
			
			
			case "1": { // Approve a single account
				choice = input.nextLine();
				try {
					if(UserService.approveUser(Integer.parseInt(choice.replaceAll(" ", "")))) {
						logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " approved an account.");
					}
					break;
				} catch (NumberFormatException nfe) {
					System.out.println("Incorrect entry detected. You must input 2 numbers. Spacing does not matter");
					logger.info("Entry error");
					break;
				}
			}
			
			
			
			case "2": { // Approve all pending accounts
				for (User user : UserService.getAllUsers()) {
					if (user.isApproved() == 0) {
						UserService.approveUser(user.getAccountNumber());
					}
				}
				logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " approved all pending accounts.");
				break;
			}
			
			
			
			case "3": { // Deny a single account
				choice = input.nextLine();
				try {
					if(UserService.denyUser(Integer.parseInt(choice.replaceAll(" ", "")))) {
						logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " denied an account.");
					}
					break;

				} catch (NumberFormatException nfe) {
					System.out.println("Incorrect entry detected. You must input 2 numbers. Spacing does not matter");
					logger.info("Entry error");
					break;
				} 
			}
			
			
			
			case "4": { // Deny all pending accounts
				for (User user : ListOfUsers.users) {
					if (user.isApproved() == 0) {
						UserService.denyUser(user.getAccountNumber());
					}
				}
				logger.trace(
						"Administator: " + admin.getFirstName() + " " + admin.getLastName() + " denied all accounts.");
				System.out.println("All pending account denied");
				break;
			}
			
			
			
			
			case "5": { // View denied accounts
				System.out.println("===================");
				System.out.println("  DENIED ACCOUNTS");
				System.out.println("===================");
				for (User user : UserService.getAllUsers()) {
					if (user.isApproved() == -1) {
						System.out.println(user);
					}
				}
				break;
			}
			
			
			
			case "6": { // Delete denied accounts
				for (User user : UserService.getAllUsers()) {
					if (user.isApproved() == -1) {
						UserService.deleteUser(user.getAccountNumber());
					}
				}
				logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " deleted denied accounts.");
				break;
			}

			
			
			case "7": { // Restore denied accounts
				for (User user : UserService.getAllUsers()) {
					if (user.isApproved() == -1) {
						UserService.pendUser(user.getAccountNumber());
					}
				}
				logger.trace("Administator: " + admin.getFirstName() + " " + admin.getLastName()
						+ " restored denied accounts.");
				break;
			}
			
			
			
			default: { // Catch entry errors
				System.out.println("\n\nPlease enter a valid menu option");
				break;
			}
			
			
			
			case "9": { // return to main admin menu
				return;
			}
			}

		}
	}
}
