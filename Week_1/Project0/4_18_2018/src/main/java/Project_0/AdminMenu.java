package Project_0;

import java.util.Scanner;

public class AdminMenu {

	public static void menu(User admin) {
		System.out.println("Administrator priviledges detected. How may I serve you today your excellency?");
		Scanner input = new Scanner(System.in);

		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("To view all users\t\tEnter 1");
			choice = input.next();
			switch (choice) {
			case "1": { // View all accounts // Obtain info on each account // Create a new account
				// Delete an account // Lock an account // Promote Admin // Demote Admin
				for (User user : ListOfUsers.users) {
					System.out.println((ListOfUsers.users.indexOf(user) + 1) + ". " + user.toString());
				}
				menuContinued(admin);
				choice = "0";
				break;
			}
			case "2": {
				// View total transactions/all transactions
			}
			case "3": {
				// View total balance
			}
			case "4": {

			}
			case "9": {
				System.out.println("Successfully logged out. Bye " + admin.getFirstName()
						+ "! I patiently await your return" + " oh great one!");
				return;
			}
			default: {

			}

			}

		}

		// Obtain info on each account
		// Create a new account
		// Delete an account
		// Lock an account
		// Secret menu option
		// Promote Admin
		// Demote Admin
	}

	public static void menuContinued(User admin) {

		Scanner input = new Scanner(System.in);
		String choice = "";
		while (choice != "1" && choice != "2" && choice != "3" && choice != "4" && choice != "9") {
			System.out.println("\nTo lock an account, Enter 1 followed by the number of the"
					+ " account you want to lock (eg 1 4 to lock account 4)");
			System.out.println("To unlock an account, Enter 2 followed by the number of the"
					+ " account you want to unlock (eg 2 4 to unlock account 4)");
			System.out.println("To delete an account, Enter 3 followed by the number of the"
					+ " account you want to delete (eg 3 2 to delete account 2)");
			System.out.println("To promote an account to admin status, Enter 4 followed by the number of the"
					+ " account you want to promote (eg 4 5 to promote account 5)");
			System.out.println("To demote an account to user status, Enter 5 followed by the number of the"
					+ " account you want to demote (eg 5 3 to demote account 3)");
			System.out.println("\n\t\tTo return to main admin menu\t Enter 9");
			choice = input.next();
			switch (choice) {
			case "1": {
				choice = input.nextLine();
				try { // NEED TO SOLVE FOR WHEN DOUBLE DIGIT ACCOUNTS
					if (ListOfUsers.users.indexOf(admin) + 1 == Integer.parseInt(choice.replaceAll(" ", ""))) {
						System.out.println("You cannot lock yourself!");
					}
					else{
						ListOfUsers.users.get(Integer.parseInt(choice.replaceAll(" ", ""))-1).lock();
						System.out.println("Account locked!");
					}
					break;

				} catch (NumberFormatException nfe) {
					System.out.println("Incorrect entry detected. You must input 2 numbers. Spacing does not matter");
					break;
				}
			}
			case "2": {
				choice = input.nextLine();
				try { // NEED TO SOLVE FOR WHEN DOUBLE DIGIT ACCOUNTS
					if (ListOfUsers.users.indexOf(admin) + 1 == Integer.parseInt(choice.replaceAll(" ", ""))) {
						System.out.println("You are not locked!");
					}
					else{
						ListOfUsers.users.get(Integer.parseInt(choice.replaceAll(" ", ""))-1).unlock();
						System.out.println("Account unlocked!");
					}
					break;

				} catch (NumberFormatException nfe) {
					System.out.println("Incorrect entry detected. You must input 2 numbers. Spacing does not matter");
					break;
				}

			}
			case "3": {

			}
			case "4": {

			}
			case "5": {

			}
			case "9": {
				return;
			}
			}

		}
	}
}
