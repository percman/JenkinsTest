package com.revature.bank;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.exception.InvalidInputException;
import com.revature.exception.NotValidUserException;
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
		try {
		do {
			
			wrongLetter = false;
			System.out.print("Type 'L' for Login or 'N' for new User: ");
			String direct = sc.next();
			if (direct.equals("L")) login();
			else if (direct.equals("N")) {
				createUser();
			}
			else {
				wrongLetter = true;
				throw new InvalidInputException("Only enter one of the approved letters.");
			}
			
		}while (wrongLetter);	
		}catch(InvalidInputException iie) {
			iie.printStackTrace();
			logger.warn(iie.getMessage());
		}
	}
	
	// User login
	public static void login() {
		// Tests if user's account is approved. If so, logs them in
		System.out.print("Enter username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		Person person = UserService.getPerson(username);
		try {
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
		else {
			throw new NotValidUserException("The username/password combination does not exist.");
		}
		}catch(NotValidUserException nvue) {
			nvue.printStackTrace();
			logger.warn(nvue.getMessage());
		}
	}

	
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
		System.out.print("Create username (no spaces allowed, min. 2 characters): ");
		String username = sc.next();
		System.out.print("Create password (no spaces allowed: ");
		String password = sc.next();
		Admin first = new Admin (username, password);
		AdminService.approvePerson(first.getUsername());
		current = first;
	}
	
	// Account creation. Checks if account already exists so as to not replace prior user in hash map
	public static void createUser() {
		boolean wrongLetter;
		do{
			wrongLetter = false;
			System.out.println("Enter a username (no spaces, min. 2 characters): ");
			String username = sc.next();
			System.out.println("Enter a password (no spaces): ");
			String password = sc.next();
			if(UserService.getPerson(username)!=null) {
				System.out.println("Account already exists.");
				break;
			}
			System.out.print("Type 'A' for Admin or 'U' for User, 'P' for Previous: ");
			String userType=sc.next();
			try {
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
				else if(userType.equals("P")) {
					break;
				}
				else {
					wrongLetter=true;
					throw new InvalidInputException("Only enter one of the approved letters.");
				}
			}catch(InvalidInputException iie) {
				iie.getStackTrace();
				logger.warn(iie.getMessage());
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
			try {
				do{
					wrongLetter = false;
					System.out.println("Enter 'D' for deposit, 'W' for withdrawal");
					System.out.println("'B' for balance, 'T' for transactions");
					System.out.println("'O' for log out");
					String entry = sc.next();
					// Deposit cash
					if(entry.equals("D")) {
						System.out.println("How much do you wish to deposit?");
						try {
							double depositAmount = sc.nextDouble();
							UserService.deposit((User) current, depositAmount);
						}catch(InputMismatchException ime) {
							ime.printStackTrace();
							logger.warn("Only numbers!");
							sc.next();
						}
					}
					// Withdraw cash
					else if(entry.equals("W")) {
						System.out.println("How much do you wish to withdraw?");
						try {
							double withdrawAmount = sc.nextDouble();
							UserService.withdraw((User) current, withdrawAmount);
						}catch(InputMismatchException ime) {
							ime.printStackTrace();
							logger.warn("Only numbers!");
							sc.next(); 
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
						wrongLetter = true;
						throw new InvalidInputException("Only enter one of the approved letters.");
					}
				}while(wrongLetter);
			}catch(InvalidInputException iie) {
				iie.getStackTrace();
				logger.warn(iie.getMessage());
			}
		}
		// Admin page
		else {
			System.out.println("Welcome, "+ current.getUsername() + ".");
			System.out.println("You are logged in.");
			try {
				do{
					wrongLetter = false;
					System.out.println("Enter 'A' to approve users, 'L' to lock users");
					System.out.println("'U' to unlock users, 'O' for log out");
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
						try {
						System.out.println();
						System.out.println("Enter the username you'd like to approve");
						System.out.println("Or enter 'P' for previous");
						String username = sc.next();
						if(UserService.getPerson(username)!=null) {
							if(username.equals(UserService.getPerson(username).getUsername())) {
								AdminService.approvePerson(username);
								System.out.println(username + " has been approved.");
							}
						}
						else if (username.equals("P")) break;
						else {
							throw new NotValidUserException(username + " is not a valid username.");
						}
						}catch(NotValidUserException nvue) {
							nvue.printStackTrace();
							logger.warn(nvue.getMessage());
						}
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
						try {
							System.out.println();
							System.out.println("Enter the username of the account you wish to lock");
							System.out.println("Or enter 'P' for previous");
							String lockName = sc.next();
							if (lockName.equals("P")) break;
							if(UserService.getPerson(lockName)!=null) {
								AdminService.lockUser(lockName);
								System.out.println(lockName + "'s account has been locked.");
							}
							else {
								throw new NotValidUserException("That is not a valid username.");
							}
						}catch(NotValidUserException nvue) {
							nvue.printStackTrace();
							logger.warn(nvue.getMessage());
						}
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
						try {
							System.out.println();
							System.out.println("Enter the username of the account you wish to unlock");
							System.out.println("Or enter 'P' for previous");
							String unlockName = sc.next();
							if(unlockName.equals("P")) break;
							if(UserService.getPerson(unlockName)!=null) {
								AdminService.unlockUser(unlockName);
								System.out.println(unlockName + "'s account has been unlocked.");
							}
							else {
								throw new NotValidUserException("That is not a valid username.");
							}
						}catch(NotValidUserException nvue) {
							nvue.printStackTrace();
							logger.warn(nvue.getMessage());
						}
					}
					// Log out
					else if(entry.equals("O")) logOut();
					else if(entry.equals("P")) break;
					else {
						wrongLetter = true;
						throw new InvalidInputException("Only enter one of the approved letters.");
					}
				}while(wrongLetter);
			}catch(InvalidInputException iie) {
				iie.getStackTrace();
				logger.warn(iie.getMessage());
			}
			}
		}
	}
}
