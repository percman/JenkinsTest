package com.revature.zero.Project_Zero;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.Serializable;
import org.apache.log4j.Logger;


/**
 * Cameron Skaggs Project Zero
 * March 2017
 *
 */
public class App {

	private static final Logger logger = Logger.getLogger(App.class);
	public static File bankData = new File("src/main/java/users.txt");
	public static int numUsers = 0;
	public static ArrayList<User> userList = User.getUserList(bankData);
	//Initial page for welcoming new and returning users
	public static void welcome (Scanner input) {
		
		System.out.println("Hello, new user? (Y/N) ");
		String response = input.next();
		while ((!response.equals("Y")) & (!response.equals("N"))) {
			System.out.println("Please enter Y or N");
			response = input.next();
		}
		//create new user
		if(response.equals("Y")) {
			System.out.println("Welcome New User");
			newUser(input);
		}
		//returning user
		else {
			System.out.println("Welcome Back");
			returnUser(input);
		}
	}
	//user has already been created
	public static void returnUser(Scanner input) {
		//variable that will be true if the user name is a return
		boolean recognized = false;
		//ask user for user name
		System.out.println("Enter Username: ");
		String userName = input.next();
		for(User u : userList) {
			if(u.getName().equals(userName)) {
				System.out.println("welcome back " + userName);
				recognized = true;
				if (u.isAdmin()) {
					adminMethod(input, u);
				}
				else {
					//is this user approved?
					if (!u.isApproved()) {
						System.out.println("Your account is not yet approved");
					}
					//is this user locked?
					if (u.isLocked()) {
						System.out.println("Your account is currently locked");
					}
					if (u.isApproved() & !u.isLocked()) {
						userMethod(u, input);
					}
				}
			}
		}
		if (recognized == false) {
			System.out.println("user name not recognized " + userName);
		}
	}
	//method for creating a new user
	@SuppressWarnings("resource")
	public static void newUser(Scanner input) {
		boolean taken = true;
		while (taken == true) {	
			taken = false;
			System.out.println("Create Username: ");
			String userName = input.next();
			for(User u : userList) {
				//check if the user name is taken
				if (u.getName().equals(userName)) {
					System.out.println("Username " + userName + " is taken");
					taken = true;
				}
			}
			if(taken == false) {
				System.out.println("Username " + userName + " is available");
				taken = false;
				if (numUsers == 0) {
					numUsers++;
					newAdmin(userName, input);
				}
				else {
					User user = new User(userName, 0, false, false, false);
					userList.add(user);
					User.serializeUser(userList, bankData);
					System.out.println("Please wait to be approved by admin");
				}
			}
		}
	}	
	public static void newAdmin(String userName, Scanner input) {
		User user = new User(userName, 0, true, false, true);
		userList.add(user);
		User.serializeUser(userList, bankData);
		adminMethod(input, user);
	}
	public static void userMethod(User u, Scanner input) throws InputMismatchException {
		int response = 0;
			while (response !=4) {
				System.out.println("Would you like to:");
				System.out.println("Deposit money? (1)");
				System.out.println("Withdraw money? (2)");
				System.out.println("View current balance? (3)");
				System.out.println("Quit (4)");

				try {
					response = input.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Invalid value");
					logger.warn(u.getName() + " entered a bad value" , e);
					return;
				}

				while (response > 4 | response < 1) {
					System.out.println("Please enter a valid number");
					response = input.nextInt();
				}
				if (response == 1) {
					System.out.println("Deposit money");
					depositMoney(u);
				}
				if (response == 2) {
					System.out.println("Withdraw money");
					withdrawMoney(u);
				}
				if (response == 3) {
					System.out.println("View balance");
					viewBalance(u);
				}
			}
	}
	/**************************************
	 * Admin Method : the method for all admin actions
	 **************************************/
	public static void adminMethod(Scanner input, User u) {
		int response = 0;

		while (response != 3) {
			System.out.println("Would you like to");
			System.out.println("Approve/Reject Users? (1)");
			System.out.println("Lock/Unlock Users? (2)");
			System.out.println("Quit (3)");
			try {
				response = input.nextInt();
			} catch(InputMismatchException e) {
				System.out.println("Invalid value");
				logger.warn(u.getName() + " entered a bad value " , e);
				return;
			}			// Loop until a valid response is given
			while (response > 3 | response < 1) {
				System.out.println("Please enter a valid number");
				response = input.nextInt();
			}
			// Approve or reject users
			if (response == 1) {
				System.out.println("----------------------");
				System.out.println("Approve or Reject Users");
				System.out.println("-----------------------");
				approveOrReject(input);
			}
			// Lock or unlock users
			if (response == 2) {
				System.out.println("----------------------");
				System.out.println("Lock or Unlock");
				System.out.println("----------------------");
				lockOrUnlock(input);
			}
		}
	}
	public static void depositMoney(User u) {
		System.out.println("Enter amount you wish to deposit");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		u.addBalance(n);
		System.out.println("You successfully added $" + n +"s " + "your current balance is $" + u.getBalance());
	}
	public static void withdrawMoney(User u) {
		System.out.println("Enter amount you wish to withdraw");
		Scanner input = new Scanner(System.in);
		int n = input.nextInt();
		u.subtractBalance(n);
		System.out.println("You successfully withdrew $" + n +"s " + "your current balance is $" + u.getBalance());
	}
	public static void viewBalance(User u) {
		System.out.println("Your balance is $" + u.getBalance());
	}
	//method for admin to approve or reject users
	public static void approveOrReject(Scanner input) {
		int i = 1;
		//print out name of users awaiting approval
		for(User u : userList) {
			if (!u.isApproved()) {
				System.out.println(i +") User " + u.getName() + " is awaiting approval");
				i++;
			}
		}
		//no one is awaiting approval
		if(i == 1) {
			System.out.println("No one is awaiting approval");
			return;
		}
		//admin inputs who he wants to approve/reject
		String approveOrReject = input.next();
		String who = input.next();
		
		while (!approveOrReject.equals("approve") & !approveOrReject.equals("reject")) {
			System.out.println("Please enter'approve' or 'reject' followed by user");
			approveOrReject = input.next();		
		}
		while (!inUserList(who)) {
			System.out.println(who + " is not recognized. Please enter valid name");
			who = input.next();
		}
		if(approveOrReject.equals("approve")) {
			User user = returnUserByName(who);
			user.setApproved(true);
			System.out.println(who + " has been approved!");
		}
		else {
			System.out.println(who + " has been rejected!");
		}
	}
	//method for admin to lock or unlock account
	public static void lockOrUnlock(Scanner input) {
		int i = 1;
		for (User u : userList) {
			System.out.println(u.getName() + "'s account is currently " + u.getLockedState() + 
					", enter \"" + u.changeLockedState() + " " +  u.getName() + "\" to " + u.changeLockedState() + " it.");
			i++;
		}
		String lockOrUnlock = input.next();
		String who = input.next();
		while (!lockOrUnlock.equals("lock") & !lockOrUnlock.equals("unlock")) {
			System.out.println("Please enter 'lock' or unlock' followed by user name");
			lockOrUnlock = input.next();
			who = input.next();			
		}
		while (!inUserList(who)) {
			System.out.println(who + " is not recognized. Please enter valid name");
			who = input.next();
		}
		if (lockOrUnlock.equals("lock")) {
			User user = returnUserByName(who);
			user.setLocked(true);
			System.out.println("User " + user.getName() + " has been locked");
		}
		else {
			User user = returnUserByName(who);
			user.setLocked(false);
			System.out.println("User " + user.getName() + " has been unlocked");
		}
	}
	//this method will return a user that shares 
	//the name with the parameter 'name'
	public static User returnUserByName(String name) {
		for (User u: userList) {
			if (u.getName().equals(name)) {
				return u;
			}
		}
		return null;
	}
	public static boolean inUserList(String name) {
		for (User u : userList) {
			if (u.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	public static void printUserList() {
		int i = 1;
		for (User u : userList) {
			System.out.println(i + ") " + u.getName());
			i++;
		}
	}
    public static void main ( String[] args ) {
		Scanner input = new Scanner(System.in);
    	while (true) {
    		welcome(input);
    	}
    }
}
