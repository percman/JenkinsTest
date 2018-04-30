package com.revature.application;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.Dao.AccountDao;
import com.revature.Dao.AccountDaoImpl;
import com.revature.model.User;

public class App {
	static AccountDaoImpl a = new AccountDaoImpl();
	final static Logger log = Logger.getLogger(App.class);

	public static void main(String[] args) {
		run();

	}

	public static void run() {
		Scanner sc = new Scanner(System.in);
		String un = null, pw = null;

		System.out.println("_________________________________");
		System.out.println("|        International	         |");
		System.out.println("|	 Bank of David   	 |");
		System.out.println("|________________________________|");
		System.out.println("|________________________________|");
		System.out.println("|________________________________|");
		System.out.println("|**NewUsers Enter (1) to Register|");
		System.out.println("");

		while (true) {
			System.out.println("        Please Sign in");
			System.out.println("                   ");
			System.out.print("Enter Email:  ");
			un = sc.nextLine();
			switch (un) {
			case "1":
				System.out.println("***REGISTRATION***");
				System.out.println("Enter your email address:");
				un = sc.nextLine();
				System.out.println("");
				User u = a.getUserInfo(un);
				if (u == null) {
					System.out.println("Your Email is not registered with our Bank");
					a.openAccount("dfirst", "dlast", un, "password", 0, 0, 0);
					System.out.println("Email Now Registered");
					System.out.println("Initial Password:  password");
					System.out.println("Please sign in and update password");
					break;
				} else {
					System.out.println("Email Address Already Registered!");
					break;
				}
			case "2":
				System.out.println("Enter Admin Email Address");
				un = sc.nextLine();
				User au = a.getUserInfo(un);
				if (un.equals("1")) {
					System.out.println("");
				} else if (au == null) {
					System.out.println("Email Address not registered");
					a.openAccount("dfirst", "dlast", un, "password", 1, 0, 1);
					System.out.println("Email Now Registered");
					System.out.println("Initial Password:  password");
					System.out.println("Please sign in and update password");
					break;
				} else {
					System.out.println("Email Already Registered!");
					break;
				}
			default:
				User ul = a.getUserInfo(un);
				if (ul == null) {
					System.out.println("Email Address not registered....");
				} else {
					System.out.println("Welcome Back, " + un);
					while (true) {
						System.out.print("Enter Password: ");
						pw = sc.nextLine();
						if (pw.equals(ul.getPw())) {
							System.out.println("Access Granted");
							if (ul.getAdmin()) {
								admin(ul);
							} else {
								customer(ul);
							}
						} else {
							System.out.println("Wrong Password!");
						}
					}
				}
			}

		}
	}

	public static void customer(User u) {
		log.info("Logged In as" + u.getU_name());

		String input = null;
		int accountnum = u.getId();
		Scanner sc = new Scanner(System.in);
		if (u.isActive()) {

			while (true) {
				System.out.println("Please select a number from the menu below");
				System.out.println("(1) Make a Deposit:");
				System.out.println("(2) Make a Withdraw:");
				System.out.println("(3) View Account Balance:");
				System.out.println("(4) Transfer Funds:  ");
				System.out.println("                       ");
				System.out.println("  **Account Settings**");
				System.out.println("(5) Change First Name");
				System.out.println("(6) Change Last Name");
				//System.out.println("(7) Change Email Address");
				System.out.println("(8) Change Password");
				System.out.println("(EXIT)               ");

				input = sc.nextLine();
				switch (input) {

				case "5":
					System.out.println("Enter New First Name:       ");
					a.firstNameChange(accountnum, sc.nextLine());
					break;
				case "6":
					System.out.println("Enter New Last Name:       ");
					a.lastNameChange(accountnum, sc.nextLine());
					break;
				case "7":
					System.out.println("Enter New Email Address:      ");
					String new_u = sc.nextLine();
					u = a.getUserInfo(new_u);
					if (u == null) {
						a.emailChange(accountnum, new_u);
					} else {
						System.out.println("Email Address Already Registered!");
					}

				case "8":
					System.out.println("New Password:       ");
					a.passwordChange(accountnum, sc.nextLine());
					break;
				case "1":
					System.out.println("Deposit Amount:      ");
					double d = sc.nextDouble();
					a.deposit(accountnum, d);
					u.setBalance(u.getBalance() + d);
					u.getBalance();
					break;
				case "2":
					System.out.println("Withdrawal Amount:     ");
					double da = sc.nextDouble();
					a.withdrawal(accountnum, da);
					u.setBalance(u.getBalance() - da);
					u.getBalance();
					break;
				case "4":
					System.out.println("Transfer to Account #:   ");
					int i = sc.nextInt();
					System.out.println("Transfer Amount:     ");
					double db = sc.nextDouble();
					a.transfer(accountnum, i, db);
					u.setBalance(u.getBalance() - db);
					u.getBalance();
					break;
				case "3":
					AccountDao ad = new AccountDaoImpl();
					System.out.println("Account Balance:" + ad.checkBalance(accountnum));
					break;
				case "EXIT":
					log.info("User" + u.getU_name() + " logged out.");
					System.out.println("Goodbye!");
					System.exit(0);

				default:
					System.out.println("");

				}
			}
		} else {
			System.out.println("Our Sincerest apologies, Your account is still pending approval..");
			run();
		}
	}

	public static void admin(User u) {
		log.info("Logged in as " + u.getU_name() + " *Administor*");

		String input = null;
		int accountnum = u.getId();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Please select a number from the menu below");
			System.out.println("(1) Make a Deposit:");
			System.out.println("(2) Make a Withdraw:");
			System.out.println("(3) View Account Balance:");
			System.out.println("(4) Transfer Funds:  ");
			System.out.println("                       ");
			System.out.println("  **Account Settings**");
			System.out.println("(5) Change First Name");
			System.out.println("(6) Change Last Name");
			//System.out.println("(7) Change Email Address");
			System.out.println("(8) Change Password");
			System.out.println("(9) Approve Customer Registration");
			System.out.println("(EXIT)                         ");

			input = sc.nextLine();
			switch (input) {

			case "5":
				System.out.println("Enter New First Name.");
				a.firstNameChange(accountnum, sc.nextLine());
				break;
			case "6":
				System.out.println("Enter New Last Name.");
				a.lastNameChange(accountnum, sc.nextLine());
				break;
			case "7":
				System.out.println("Enter New Email Address:");
				u = a.getUserInfo(sc.nextLine());
				break;
			case "8":
				System.out.println("Enter New Password:");
				a.passwordChange(accountnum, sc.nextLine());
				break;
			case "1":
				System.out.println("Deposit Amount: ");
				a.deposit(accountnum, sc.nextDouble());
				break;
			case "2":
				System.out.println("Withdrawal Amount: ");
				a.withdrawal(accountnum, sc.nextDouble());
				break;
			case "4":
				System.out.println("Transfer to Account #:   ");
				int i = sc.nextInt();
				System.out.println("Transfer Amount:");
				a.transfer(accountnum, i, sc.nextDouble());
				break;
			case "3":
				AccountDao ad = new AccountDaoImpl();
				System.out.println("Account Balance:" + ad.checkBalance(accountnum));
				break;
			
			case "EXIT":
				System.out.println("Goodbye!");
				System.exit(0);
				break;
			case "9":
				System.out.println("Enter Account Number to be approved:");
				a.active(sc.nextInt());
				break;
			default:
				System.out.println("");
			}

		}
	}
}

