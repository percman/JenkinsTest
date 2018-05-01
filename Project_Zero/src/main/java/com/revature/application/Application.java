package com.revature.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.util.Scanner;
import org.apache.log4j.Logger;

import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.User;
import com.revature.service.AdminService;
import com.revature.service.CustomerService;
import com.revature.service.UserService;

import CustomException.LoginException;


public class Application {

	private static final Logger logger = Logger.getLogger(Application.class);
	static Scanner input = new Scanner(System.in);
	private String fileName = "src/main/resources/users.txt";
	private File file = new File(fileName);
	private BankDatabase data = new BankDatabase();
	private User user0;
	private User user;
	private Customer customer;
	private Admin admin;
	
	public void deserializeData(File file) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(fileName));
			data = (BankDatabase) in.readObject();
		} catch (IOException ioe) {
			logger.warn(ioe.getMessage());
		} catch (ClassNotFoundException cnfe) {
			logger.warn("Class not found.");
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				logger.warn(ioe.getMessage());
			}
		}
	}

	public void serializeData(BankDatabase data, File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(data);
		} catch (IOException ioe) {
			logger.warn(ioe.getMessage());
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
				logger.warn(ioe.getMessage());
			}
		}
	}
	public void start() {

		
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


		user0 = new User(username, password);
		user = UserService.login(user0);
		/*try {
			UserService.login(user0);
			if (UserService.login(user0) == null) {
				System.out.println("Please enter correct Log In information.");
				logIn();
				throw new LoginException();
			}
			else {
				user = UserService.login(user0);
			}
		} catch (LoginException le) {
			System.out.println("Incorrect Log In information.");
		}*/
		
		if (user.getTypeId() == 2) {
			customer = CustomerService.getCustomer(username);
			if (customer.getRejected() == 1) {
				System.out.println("Sorry, your account has been rejected." + "\n");
				menu();
			}
			else if (customer.getApprovalCode() == 0) {
				System.out.println("Please wait for account approval." + "\n\n");
				menu();
			}
			else if (customer.getLockCode() == 1) {
				System.out.println("Your account is currently locked. Please contact the bank.");
				menu();
			}
			else {
				logger.info("Customer " + customer.getUsername() + " has logged in.");
				System.out.println("Welcome " + customer.getUsername());
				customerMenu();
			}
		}	
		else {
			admin = AdminService.getAdmin(username);
			logger.info("Admin " + admin.getUsername() + "has logged in.");
			System.out.println("Welcome " + admin.getUsername() + "\n");
			adminMenu();
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
		
		user = new User(username, password, 2);
		
		
		if (UserService.getUser(username) ==  null) {
			Customer newCustomer = new Customer(username, password);
			UserService.insertUser(user);
			CustomerService.insertCustomer(newCustomer);
			System.out.println("Sign Up successful! Please wait for account approval." + "\n\n");
			menu();
		}
		else {
			System.out.println("That username is taken, please choose another one.");
			signUp();
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
				BigDecimal balance = customer.getAccountBalance();
				balance = balance.add(depositAmount);
				customer.setAccountBalance(balance);
				CustomerService.updateCustomer(customer);
				logger.info(customer.getUsername() + " has deposited " + depositAmount);
				customerMenu();
			}
		}

		if (option.equals("2")) {
			System.out.println("Enter the amount you want to withdraw: ");
			String withdrawal = input.nextLine();
			boolean withdrawalNotValid = true;
			BigDecimal withdrawalAmount = new BigDecimal("0");
			BigDecimal balance = customer.getAccountBalance();

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
			customer.setAccountBalance(balance);
			CustomerService.updateCustomer(customer);
			logger.info(customer.getUsername() + "has withdrawn " + withdrawalAmount);
			customerMenu();

		}

		if (option.equals("3"))

		{
			BigDecimal balance = customer.getAccountBalance();
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
			for (int i = 0; i < CustomerService.getAllCustomers().size(); i++) {
				if (CustomerService.getAllCustomers().get(i).getApprovalCode() == 0
						&& CustomerService.getAllCustomers().get(i).getRejected() == 0) {
					System.out.println(CustomerService.getAllCustomers().get(i).getUsername() + " is waiting for approval");
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
						customer = CustomerService.getAllCustomers().get(i);
						customer.setApprovalCode(1);
						System.out.println("Account has been approved.");
						CustomerService.updateCustomer(customer);
					}
					if (approval.equals("r")) {
						customer = CustomerService.getAllCustomers().get(i);
						customer.setRejected(1);
						System.out.println("Account has been rejected.");
						CustomerService.updateCustomer(customer);
					}

				} else {
					System.out.println("There are no accounts waiting for approval." + "\n");
					adminMenu();
				}
			}
			adminMenu();
		}

		if (option.equals("2")) {
			System.out.println("Here is a list of current customers with unlocked accounts: ");
			for (int i = 0; i < CustomerService.getAllCustomers().size(); i++) {
				if (CustomerService.getAllCustomers().get(i).getLockCode() == 0) {
				System.out.print(CustomerService.getAllCustomers().get(i).getUsername() + ", ");
				}
			}
			System.out.println();
			System.out.println("Here is a list of current customers with locked accounts: ");
			for (int i = 0;  i< CustomerService.getAllCustomers().size(); i++) {
				if (CustomerService.getAllCustomers().get(i).getLockCode() == 1) {
					System.out.print(CustomerService.getAllCustomers().get(i).getUsername() + ", ");
				}
			}
			System.out.println();
			System.out.println("To lock or unlock a customer account, enter account's username: ");
			String user = input.nextLine();
			while (user.length() == 0) {
				user = input.nextLine();
			}
			
			for (int i = 0; i < CustomerService.getAllCustomers().size(); i++) {
				int j = 0;
				j++;
				if (CustomerService.getAllCustomers().get(i).getUsername().equals(user)) {
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
						customer = CustomerService.getAllCustomers().get(i);
						customer.setLockCode(1);
						CustomerService.updateCustomer(customer);
						System.out.println("Account is locked.");
						adminMenu();
					}
					if (lockUnlock.equals("u")) {
						customer = CustomerService.getAllCustomers().get(i);
						customer.setLockCode(0);
						CustomerService.updateCustomer(customer);
						System.out.println("Account has been unlocked.");
						adminMenu();
					}
					
				}
				if (j == CustomerService.getAllCustomers().size() - 1) {
					System.out.println("Please enter a correct username that matches exactly.");
					adminMenu();
				}	
			}
		
		}

		

		if (option.equals("3")) {
			exit();
		}
	}
}
