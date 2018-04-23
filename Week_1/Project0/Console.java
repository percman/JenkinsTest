package com.revature.bankapplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {

	private String fileName = "src/main/resources/users.txt";
	private File file = new File(fileName);
	private BankDatabase data = new BankDatabase();
	static Scanner input = new Scanner(System.in);
	private Customer customerUser;
	private Admin adminUser;

	public void deserializeData(File file) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			data = (BankDatabase) in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public void serializeData(BankDatabase data, File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(data);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}

	public void start() {

		if (file.length() == 0)
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
				Customer tempCustomer = new Customer("Eric", "password");
				data.getLoginInfo().put("Eric", "password");
				data.getCustomer().put(tempCustomer.getUsername(), tempCustomer);
				data.getApprovalList().add(tempCustomer);
				Admin tempAdmin = new Admin("David", "password");
				data.getLoginInfo().put("David", "password");
				data.getAdmin().put(tempAdmin.getUsername(), tempAdmin);
				out.writeObject(data);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		deserializeData(file);
		menu();
	}

	public void menu() {

		System.out.println("David's Bank");
		System.out.println("==========================");
		System.out.println("Main Menu: ");
		System.out.println("1. Log In");
		System.out.println("2. Sign Up for New Account");
		System.out.println("3. Exit" + "\n");
		System.out.println("Please enter a number corresponding to the options.");

		String option = input.nextLine();
		while (option.length() == 0) {
			option = input.nextLine();
		}
		while (!option.equals("1") && !option.equals("2") && !option.equals("3")) {
			System.out.println("Please select 1, 2, or 3");
			option = input.nextLine();
		}

		if (option.equals("1")) {
			logIn();
		} else if (option.equals("2")) {
			signUp();
		} else if (option.equals("3")) {
			exit();
		}
	}

	public void logIn() {
		boolean isCustomer = false;
		System.out.println("LOG IN");
		System.out.println("=========================");
		System.out.println("Please enter User Name: ");
		String username = input.nextLine();
		while (username.length() == 0) {
			username = input.nextLine();
		}

		System.out.println("Please enter your Password: ");
		String password = input.nextLine();
		while (password.length() == 0) {
			password = input.nextLine();
		}

		if (!data.getLoginInfo().containsKey(username) || !data.getLoginInfo().get(username).equals(password)) {
			System.out.println("Username or password are incorrect.");
			logIn();
		}

		if (data.getLoginInfo().containsKey(username) && data.getLoginInfo().get(username).equals(password)) {
			if (data.getCustomer().containsKey(username)) {
				customerUser = data.getCustomer().get(username);
				isCustomer = true;
			} else if (data.getAdmin().containsKey(username)) {
				// currentUser = data.getAdmin().get(username);
				adminUser = data.getAdmin().get(username);
			}

			if (isCustomer) {

				if (customerUser.getRejected() == true) {
					System.out.println("Sorry, your account has been rejected." + "\n");
					menu();
				} else if (customerUser.getApprovalCode() == -1) {
					System.out.println("Please wait for account approval." + "\n\n");
					menu();
				} else if (customerUser.getLockCode() == 1) {
					System.out.println("Your account is currently locked. Please contact the bank.");
					menu();
				} else {
					System.out.println("Welcome" + customerUser.getUsername());
					customerMenu();
				}
			} else if (adminUser.getIsAdmin() == true) {

				System.out.println("Welcome " + adminUser.getUsername() + "\n");
				adminMenu();
			}

			else {
				System.out.println("Log In or password are incorrect.");
				logIn();
			}
		}
	}

	public void signUp() {

		System.out.print("Input a userame: " + "\n");
		String username = input.next();
		while (username.length() == 0) {
			username = input.next();
		}

		System.out.println("Input a password: ");
		String password = input.next();
		while (password.length() == 0) {
			password = input.next();
		}

		if (data.getLoginInfo().containsKey(username)) {
			System.out.println("That username is taken, please choose another one.");
			signUp();
		} else {
			Customer newCustomer = new Customer(username, password);
			data.getCustomer().put(username, newCustomer);
			data.getLoginInfo().put(username, password);
			data.getApprovalList().add(newCustomer);
			System.out.println("Sign Up successful! Please wait for account approval." + "\n\n");
			menu();
		}
	}

	public void exit() {

		System.out.println("Console will now terminate.");
		serializeData(data, file);
		input.close();
	}

	public void customerMenu() {

		System.out.println("Customer Menu");
		System.out.println("===================");
		System.out.println("1. Deposit Funds");
		System.out.println("2. Withdraw Funds");
		System.out.println("3. View Balance");
		System.out.println("4. Log Out");
		String option = input.nextLine();

		while (!option.equals("1") && !option.equals("2") && !option.equals("3") && !option.equals("4")) {
			System.out.println("Please enter a number corresponding to the options.");
			option = input.nextLine();
		}

		if (option.equals("1")) {
			System.out.println("Enter the amount you want to deposit: ");
			String deposit = input.nextLine();
			while (deposit.length() == 0) {
				deposit = input.nextLine();
			}

			boolean depositNotValid = true;
			while (depositNotValid) {
				for (int i = 0; i < deposit.length(); i++) {
					if (!Character.isDigit(deposit.charAt(i))) {
						char c = deposit.charAt(i);
						if (c != '.') {
							System.out.println("Please enter a valid amount, for example 21.50");
							deposit = input.nextLine();
						}
						if (c == '.') {
							if (i != deposit.length() - 3) {
								System.out.println("Please enter a valid amount, for example 21.50: ");
								deposit = input.nextLine();
							}
						}
						if (c == '.') {
							if (i == deposit.length() - 3)
								depositNotValid = false;
						}

					} else if (Character.isDigit(deposit.charAt(i))) {
						depositNotValid = false;
					}
				}
			}
			if (!depositNotValid) {
				BigDecimal depositAmount = new BigDecimal(deposit);
				BigDecimal balance = customerUser.getAccountBalance();
				balance = balance.add(depositAmount);
				customerUser.setAccountBalance(balance);

				customerMenu();
			}
		}

		if (option.equals("2")) {
			System.out.println("Enter the amount you want to withdraw: ");
			String withdrawal = input.nextLine();
			boolean withdrawalNotValid = true;
			BigDecimal withdrawalAmount = new BigDecimal("0");
			BigDecimal balance = customerUser.getAccountBalance();

			while (withdrawalNotValid) {
				for (int i = 0; i < withdrawal.length(); i++) {
					if (!Character.isDigit(withdrawal.charAt(i))) {
						char c = withdrawal.charAt(i);
						if (c != '.') {
							System.out.println("Please enter a valid amount, for example 21.50");
							withdrawal = input.nextLine();
						}
						if (c == '.') {
							if (i != withdrawal.length() - 3) {
								System.out.println("Please enter a valid amount, for example 21.50: ");
								withdrawal = input.nextLine();
							}
						}
						if (c == '.') {
							if (i == withdrawal.length() - 3)
								withdrawalNotValid = false;
							withdrawalAmount = new BigDecimal(withdrawal);
						}

					} else if (Character.isDigit(withdrawal.charAt(i))) {
						withdrawalNotValid = false;
						withdrawalAmount = new BigDecimal(withdrawal);
					}
				}
			}

			while (balance.compareTo(withdrawalAmount) < 0) {
				System.out.println("Insufficient funds, please withdraw a valid amount: ");
				withdrawal = input.nextLine();
				withdrawalAmount = new BigDecimal(withdrawal);

			}
			if (balance.compareTo(withdrawalAmount) >= 0) {
				withdrawalNotValid = false;
			}

			balance = balance.subtract(withdrawalAmount);
			customerUser.setAccountBalance(balance);
			customerMenu();

		}

		if (option.equals("3"))

		{
			BigDecimal balance = customerUser.getAccountBalance();
			System.out.println("Your current balance is : " + balance);
			customerMenu();
		}

		if (option.equals("4")) {
			exit();
		}

	}

	public void adminMenu() {

		System.out.println("Administer Menu");
		System.out.println("===================================");
		System.out.println("1. Approve/Reject pending accounts");
		System.out.println("2. Lock/Unlock accounts");
		System.out.println("3. Log Out");
		String option = input.nextLine();

		while (option.length() == 0) {
			option = input.nextLine();
		}
		while (!option.equals("1") && !option.equals("2") && !option.equals("3")) {
			System.out.println("Please enter a number corresponding to the options");
		}

		if (option.equals("1")) {
	
			for (int i = 0; i < data.getApprovalList().size(); i++) {
				if (data.getApprovalList().get(i).getApprovalCode() == -1
						&& data.getApprovalList().get(i).getRejected() == false) {
					System.out.println(data.getApprovalList().get(i).getUsername() + " is waiting for approval");
					System.out.println("To approve enter a, to reject enter r.");
					String approval = input.nextLine();
					while (approval.length() == 0) {
						System.out.println("Please enter a or r: ");
						approval = input.nextLine();
					}
					while (!approval.equals("r") && !approval.equals("a")) {
						System.out.println("Please enter a or r: ");
						approval = input.nextLine();
					}
					if (approval.equals("a")) {
						data.getApprovalList().get(i).setApprovalCode(1);
						System.out.println("Account has been approved.");
					}
					if (approval.equals("r")) {
						data.getApprovalList().get(i).setRejected(true);
						System.out.println("Account has been rejected.");
					}

				} else {
					System.out.println("There are no accounts waiting for approval." + "\n");
					adminMenu();
				}
			}
			adminMenu();
		}

		if (option.equals("2")) {
			System.out.println("To lock or unlock a customer account, enter account's username: ");
			String user = input.nextLine();
			while (user.length() == 0) {
				user = input.nextLine();
			}
			if (data.getCustomer().containsKey(user)) {
				System.out.println("To lock account enter l, to unlock account enter u: ");
				String lockUnlock = input.nextLine();
				while (lockUnlock.length() == 0) {
					lockUnlock = input.nextLine();
				}
				while (!lockUnlock.equals("l") && !lockUnlock.equals("u")) {
					System.out.println("Please enter l or u: ");
					lockUnlock = input.nextLine();
				}
				if (lockUnlock.equals("l")) {
					data.getCustomer().get(user).setLockCode(1);
					System.out.println("Account is unlocked.");
					adminMenu();
				}
				if (lockUnlock.equals("u")) {
					data.getCustomer().get(user).setLockCode(-1);
					System.out.println("Account has been locked.");
					adminMenu();
				}
			}

		}

		if (option.equals("3")) {
			exit();
		}
	}

}
