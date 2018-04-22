package com.revature.bank;

import java.io.File;
import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Control {
	
	private static final Logger logger = Logger.getLogger(Control.class);
	
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Serialize.deserializeAdmin(adminFile);
		Serialize.deserializeUser(userFile);
		
		if(Admin.adminMap.isEmpty()) {
			addFirst();
		}
		while(true) {
			if(currentAdmin!=null || currentUser!=null) {
				welcomePage();			
			}	
			else directory();
		}
	}
	
	public static Admin currentAdmin = null;
	public static User currentUser = null;
	public static File adminFile = new File ("src/main/resources/admin.txt");
	public static File userFile = new File("src/main/resources/user.txt");
	
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
		boolean wrongLetter;
		String type;
		// Input user type
		do{
			wrongLetter = false;
			System.out.println("Enter 'A' for Admin or 'U' for User: ");
			type = sc.next();
			if (!type.equals("A") && !type.equals("U")) {
				System.out.println("Only enter one of the approved letters.");
				wrongLetter = true;
				type = sc.next();
			}
		} while (wrongLetter);
		// Input user name & password, checks against appropriate HashMap
		// Tests if user's account is approved. If so, logs them in
		System.out.print("Enter username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		if(type.equals("A")) {
			if (Admin.adminMap.containsKey(username) && 
					password.equals(Admin.adminMap.get(username).password)){				
				if(Admin.adminMap.get(username).approved) {
					currentAdmin = Admin.adminMap.get(username);
					welcomePage();
				}
				else System.out.println("You will need to wait for admin approval to log in.");
			}
			else System.out.println("The type/username/password combination does not exist.");
		}
		else if(type.equals("U")) {
			if (User.userMap.containsKey(username) && 
					password.equals(User.userMap.get(username).password)){				
				if(User.userMap.get(username).approved) {
					if(!User.userMap.get(username).locked) {
						currentUser = User.userMap.get(username);
						welcomePage();
					}
					else System.out.println("Your account has been locked. "
							+ "An admin must unlock your account.");
				}
				else System.out.println("You will need to wait for admin approval to log in.");
			}
			else System.out.println("The type/username/password combination does not exist.");
		}
		
		
		
	}
	
	// Logs user out
	public static void logOut() {
		currentAdmin = null;
		currentUser = null;
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
		first.approved= true;
		Admin.addAdminToMap(username, first);
		Serialize.serializeAdmin(adminFile);
		currentAdmin = first;
		System.out.println("You have been added.");
	}
	
	// Account creation. Checks if account already exists so as to not replace prior user in hash map
	public static void createUser() {
		boolean wrongLetter;
		do{
			wrongLetter = false;
			System.out.print("Type 'A' for Admin or 'U' for User: ");
			String userType=sc.next();
			if(userType.equals("A")) {
				System.out.println("Enter a username (no spaces): ");
				String username = sc.next();
				System.out.println("Enter a password (no spaces): ");
				String password = sc.next();
				if(Admin.adminMap.containsKey(username)) {
					System.out.println("Account already exists.");
				}
				else {
					Admin admin = new Admin(username, password);
					Admin.addAdminToMap(username, admin);
					Serialize.serializeAdmin(adminFile);
					System.out.println("You will need to wait for admin approval to log in.");
				}	
			}
			else if (userType.equals("U")) {
				System.out.println("Enter a username (no spaces): ");
				String username = sc.next();
				System.out.println("Enter a password (no spaces): ");
				String password = sc.next();
				if(User.userMap.containsKey(username)) {
					System.out.println("Accoiunt already exists.");
				}
				else {
					User user = new User(username, password);
					User.addUserToMap(username, user);
					Serialize.serializeUser(userFile);
					System.out.println("You will need to wait for admin approval to log in.");
				}	
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
		if(currentUser!=null) {
			System.out.println("Welcome, "+currentUser.username + ".");
			System.out.println("You are logged in.");
			
			do{
				wrongLetter = false;
				System.out.println("Enter 'D' for deposit, 'W' for withdrawal.");
				System.out.println("'B' for balance, 'O' for log out.");
				String entry = sc.next();
				// Deposit cash
				if(entry.equals("D")) {
					System.out.println("How much do you wish to deposit?");
					try {
						double depositAmount = sc.nextDouble();
						currentUser.deposit(depositAmount);
						Serialize.serializeUser(userFile);
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
						currentUser.withdraw(withdrawAmount);
						Serialize.deserializeUser(userFile);
					}catch(InputMismatchException ime) {
						System.out.println("Only numbers!");
						logger.warn(ime.getMessage());
					}
				}
				// Get balance
				else if(entry.equals("B")) {
					DecimalFormat df = new DecimalFormat("###.##");
					System.out.println("Your current balance is: $" + 
							df.format(currentUser.getBalance()) + ".");
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
			System.out.println("Welcome, "+currentAdmin.username + ".");
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
					for(Map.Entry<String, User> users: User.userMap.entrySet()) {
						if(!users.getValue().approved) System.out.println(users.getKey());
					}
					System.out.println();
					System.out.println("Unapproved admins:");
					for(Map.Entry<String, Admin> admins: Admin.adminMap.entrySet()) {
						if(!admins.getValue().approved) System.out.println(admins.getKey());
					}
					System.out.println();
					boolean error;
					String type;
					do {
						error = false;
						System.out.println("Enter 'U' to approve User or 'A' to approve Admin.");
						type = sc.next();
						if(!type.equals("U") && !type.equals("A")) error = true;
					}while(error);
					// Approve user
					if(type.equals("U")) {
						System.out.println("Enter the username you'd like to unlock.");
						String username = sc.next();
						if(User.userMap.containsKey(username)) {
							User approve = User.userMap.get(username);
							currentAdmin.approveUser(approve);
							Serialize.serializeUser(userFile);
							System.out.println(approve.username + " has been approved.");
						}
						else System.out.println(username + " is not a valid username.");
					}
					// Approve admin
					else {
						System.out.println("Enter the admin username you'd like to unlock.");
						String username = sc.next();
						if(Admin.adminMap.containsKey(username)) {
							Admin approve = Admin.adminMap.get(username);
							currentAdmin.approveAdmin(approve);
							Serialize.serializeAdmin(adminFile);
							System.out.println(approve.username + " has been approved.");
						}
						else System.out.println(username + "is not a valid admin username.");
					}		
				}
				// Lock users
				else if(entry.equals("L")) {
					System.out.println("Unlocked users:");
					for(Map.Entry<String, User> users: User.userMap.entrySet()) {
						if(!users.getValue().locked) System.out.println(users.getKey());
					}
					System.out.println();
					System.out.println("Enter the username of the account you wish to lock.");
					String lockName = sc.next();
					if(User.userMap.containsKey(lockName)) {
						User lockedUser = User.userMap.get(lockName);
						currentAdmin.lockUser(lockedUser);
						Serialize.serializeUser(userFile);
						System.out.println(lockedUser.username + "'s account has been locked.");
					}
					else System.out.println("That is not a valid username.");
				}
				// Unlock users
				else if(entry.equals("U")) {
					System.out.println("Locked users:");
					for(Map.Entry<String, User> users: User.userMap.entrySet()) {
						if(users.getValue().locked) System.out.println(users.getKey());
					}
					System.out.println();
					System.out.println("Enter the username of the account you wish to unlock.");
					String unlockName = sc.next();
					if(User.userMap.containsKey(unlockName)) {
						User unlockedUser = User.userMap.get(unlockName);
						currentAdmin.unlockUser(unlockedUser);
						Serialize.deserializeUser(userFile);
						System.out.println(unlockedUser.username + "'s account has been locked.");
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
