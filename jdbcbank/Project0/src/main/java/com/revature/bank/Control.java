package com.revature.bank;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.model.Admin;
import com.revature.model.Person;
import com.revature.model.User;
import com.revature.service.AdminService;
import com.revature.service.TransactionService;
import com.revature.service.UserService;


public class Control {
	
	private static final Logger logger = Logger.getLogger(Control.class);
	
	public static Scanner sc = new Scanner(System.in);
	
	public static Person current = null;

	
	public static void main(String[] args) {

		if(AdminService.emptySet()) {
			addFirst();
		}
		while(true) {
			if(current!=null) {
				welcomePage();			
			}	
			else directory();
		}
	}
	

	
	// When no user logged in, asks for user creation or sign-in
	public static void directory() {
		boolean wrongLetter;
		do {
			wrongLetter = false;
			System.out.print("Type 'L' for Login or 'N' for new User: ");
			String direct = sc.next();
			if (direct.equals("L")) login();
			else if (direct.equals("N")) {
				createUser();
			}
			else {
				System.out.println("Only enter one of the approved letters.");
				wrongLetter = true;
			}
		}while (wrongLetter);	
	}
	
	// User login
	public static void login() {
//		boolean wrongLetter;
//		String type;
		// Input user type
//		do{
//			wrongLetter = false;
//			System.out.println("Enter 'A' for Admin or 'U' for User: ");
//			type = sc.next();
//			if (!type.equals("A") && !type.equals("U")) {
//				System.out.println("Only enter one of the approved letters.");
//				wrongLetter = true;
//				type = sc.next();
//			}
//		} while (wrongLetter);
		// Input user name & password, checks against appropriate HashMap
		// Tests if user's account is approved. If so, logs them in
		System.out.print("Enter username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
//		if(type.equals("A")) {
//			if (Admin.adminMap.containsKey(username) && 
//					password.equals(Admin.adminMap.get(username).password)){				
//				if(Admin.adminMap.get(username).approved) {
//					currentAdmin = Admin.adminMap.get(username);
		Person person = UserService.getPerson(username);
		if(UserService.getPerson(username)!=null) {
			if(person.getPassword().equals(UserService.getPasswordHash(new User(username, password)))) {
				
				if(UserService.isApproved(person)&&(UserService.getPerson(username).getClass().getName().equals("com.revature.model.Admin")||!UserService.isLocked(person))) {
					current = UserService.getPerson(username);
					welcomePage();
				}
				else {
					if(!UserService.isApproved(person)) {
						System.out.println("You will need to wait for admin approval to log in.");
					}
					else if(UserService.isLocked(person)) {
						System.out.println("Your account has been locked. An admin must unlock your account.");
					}
				}
			}	
		}
		else System.out.println("The type/username/password combination does not exist.");
//				}
		
			//}
		}
//		else if(type.equals("U")) {
//			if (User.userMap.containsKey(username) && 
//					password.equals(User.userMap.get(username).password)){				
//				if(User.userMap.get(username).approved) {
//					if(!User.userMap.get(username).locked) {
//						currentUser = User.userMap.get(username);
//						welcomePage();
//					}
//					else System.out.println("Your account has been locked. "
//							+ "An admin must unlock your account.");
//				}
//				else System.out.println("You will need to wait for admin approval to log in.");
//			}
//			else System.out.println("The type/username/password combination does not exist.");
//		}
		
		
		
	//}
	
	// Logs user out
	public static void logOut() {
		current = null;
		System.out.println("You have logged out.");
		System.out.println();
	}
	
	// If administrative hash map empty, allows user to create the first account
	public static void addFirst() {
		System.out.println("Welcome! You're the first user!");
		System.out.println("An administrative account will be created for you.");
		System.out.print("Create username (no spaces allowed): ");
		String username = sc.next();
		System.out.print("Create password (no spaces allowed: ");
		String password = sc.next();
		Admin first = new Admin (username, password);
		System.out.println("Account added?" + AdminService.insertAdmin(first));
		AdminService.approvePerson(first.getUsername());
		current = first;
		System.out.println("You have been added.");
	}
	
	// Account creation. Checks if account already exists so as to not replace prior user in hash map
	public static void createUser() {
		boolean wrongLetter;
		do{
			wrongLetter = false;
			System.out.println("Enter a username (no spaces): ");
			String username = sc.next();
			System.out.println("Enter a password (no spaces): ");
			String password = sc.next();
			if(UserService.getPerson(username)!=null) {
				System.out.println("Account already exists.");
			}
			System.out.print("Type 'A' for Admin or 'U' for User: ");
			String userType=sc.next();
			if(userType.equals("A")) {
				Admin admin = new Admin(username, password);
				AdminService.insertAdmin(admin);
				System.out.println("You will need to wait for admin approval to log in.");
			}
			else if (userType.equals("U")) {
				User user = new User(username, password);
				UserService.insertUser(user);
				System.out.println("You will need to wait for admin approval to log in.");
			}
			else {
				System.out.println("Only enter one of the approved letters.");
				wrongLetter=true;
			}
		}while(wrongLetter);
	}
	
	// When user logs in, this is what they will see
	public static void welcomePage() {
		boolean wrongLetter;
		System.out.println();
		// User page
		if(current!=null) {
			if(current.getClass().getName().equals("com.revature.model.User")) {
			System.out.println("Welcome, "+current.getUsername() + ".");
			System.out.println("You are logged in.");			
			do{
				wrongLetter = false;
				System.out.println("Enter 'D' for deposit, 'W' for withdrawal.");
				System.out.println("'B' for balance, 'T' for transactions");
				System.out.println("'O' for log out.");
				String entry = sc.next();
				// Deposit cash
				if(entry.equals("D")) {
					System.out.println("How much do you wish to deposit?");
					try {
						double depositAmount = sc.nextDouble();
						UserService.deposit((User) current, depositAmount);
					}catch(InputMismatchException ime) {
						System.out.println("Only numbers!");
						logger.warn(ime.getMessage());
					}
				}
				// Withdraw cash
				else if(entry.equals("W")) {
					System.out.println("How much do you wish to withdraw?");
					try {
						double withdrawAmount = sc.nextDouble();
						UserService.withdraw((User) current, withdrawAmount);
					}catch(InputMismatchException ime) {
						System.out.println("Only numbers!");
						logger.warn(ime.getMessage());
					}
				}
				// Get balance
				else if(entry.equals("B")) {
					DecimalFormat df = new DecimalFormat("###.##");
					System.out.println("Your current balance is: " + df.format(UserService.getBalance((User) current)) + ".");
				}
				// List of transactions
				else if(entry.equals("T")) {
					List<String> transactions = TransactionService.transactions((User) current);
					if(transactions!=null) {
						for(String t:transactions) {
							System.out.println(t);
						}
					}
				}
				// Log out
				else if(entry.equals("O")) logOut();
				else {
					System.out.println("Only enter one of the approved letters.");
					wrongLetter = true;
				}
			}while(wrongLetter);
		}
		// Admin page
		else {
			System.out.println("Welcome, "+ current.getUsername() + ".");
			System.out.println("You are logged in.");
			
				do{
					wrongLetter = false;
					System.out.println("Enter 'A' to approve users, 'L' to lock users");
					System.out.println("'U' to unlock users, 'O' for log out.");
					String entry = sc.next();
					// Approve users
					if(entry.equals("A")) {
						// List unapproved users and admins
						System.out.println("Unapproved users:");
						List<String> unapproved = AdminService.getUnapproved();
						if(unapproved!=null) {
							for(String u: unapproved) {
								System.out.println(u);
							}
						}
						System.out.println();
						System.out.println("Enter the username you'd like to approve.");
						String username = sc.next();
						if(UserService.getPerson(username)!=null) {
							if(username.equals(UserService.getPerson(username).getUsername())) {
								AdminService.approvePerson(username);
								System.out.println(username + " has been approved.");
							}
						}
						else System.out.println(username + " is not a valid username.");
					}		
					// Lock users
					else if(entry.equals("L")) {
						System.out.println("Unlocked users:");
						List<String> unlocked = AdminService.getUnlocked();
						if (unlocked!=null) {
							for(String u:unlocked) {
								System.out.println(u);
							}
						}
						System.out.println();
						System.out.println("Enter the username of the account you wish to lock.");
						String lockName = sc.next();
						if(UserService.getPerson(lockName)!=null) {
							AdminService.lockUser(lockName);
							System.out.println(lockName + "'s account has been locked.");
						}
						else System.out.println("That is not a valid username.");
					}
					// Unlock users
					else if(entry.equals("U")) {
						System.out.println("Locked users:");
						List<String> locked = AdminService.getLocked();
						if(locked!=null) {
							for(String l:locked) {
								System.out.println(l);
							}
						}
						System.out.println();
						System.out.println("Enter the username of the account you wish to unlock.");
						String unlockName = sc.next();
						if(UserService.getPerson(unlockName)!=null) {
							AdminService.unlockUser(unlockName);
							System.out.println(unlockName + "'s account has been locked.");
						}
						else System.out.println("That is not a valid username.");
					}
					// Log out
					else if(entry.equals("O")) logOut();
					else {
						System.out.println("Only enter one of the approved letters.");
						wrongLetter = true;
					}
				}while(wrongLetter);
			}
		}
	}
}
