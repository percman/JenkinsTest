package com.revature.users;

import static com.revature.readwrite.ReadWrite.inputLine;
import static com.revature.users.Admin.*;
import static com.revature.users.SerializationOfUsers.*;

import java.util.HashMap;

public class GUI{

	// Greeting screen runs given no userFile
	public static String firstRun() {
		
		System.out.println("Hello! Welcome to the first run of Adam Lahey's Project Zero!");
		System.out.println("Note that the first time login requires creation of a new user.");
		System.out.println("This user will be deemed an administrator, meaning they can create new accounts.");
		System.out.println("To begin, please enter your username: ");
		String username = inputLine();
		System.out.println("Security is important. But time is even more precious.");
		System.out.println("So please enter your password, and I promise not to store in plain text.");
		System.out.println("Please don't try and break my program yet :( it's fragile");
		String password = inputLine();
		System.out.println("Note that this account is an administrator.");
		System.out.println("If you would like to enter a starting balance, please do so now.");
		String balancestring = inputLine();
		Double doubleObject = new Double(balancestring);
		double startingbalance = doubleObject.doubleValue();
		
		addUserAsAdmin(username, password, true, false, startingbalance);
		
		return username;
	}
	
	
	// Allows for login given a username and password 
	public static String login(HashMap<Integer, User> userHashData) {
		System.out.println("Please enter your user or admin name: ");
		String username = inputLine();
		
		System.out.println("Please enter your password: ");
		String password = inputLine();
		
		if(passwordCheck(userHashData, username, password)) {
			if(lockStatus(userHashData, username))
			{
				System.out.println("You have been locked. New users must be approved by admins.");
				return login(userHashData);
			}
			else {
				System.out.println("You have successfully logged in.");
				return username;
			}
		}
		
		System.out.println("Invalid username or password.");
		
		return login(userHashData);
	}
	
	
	
	
	// Allows selection of various activities to do 
	public static void mainMenu(HashMap<Integer, User> userHashData, String currentUser) {
		int equalsigns = 10;
		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
		System.out.print(" Main Menu ");
		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
		System.out.println();
		System.out.println("You are currently logged in as: " + currentUser);
		System.out.println("\nPlease choose from the list below.");
		System.out.println("1. Check balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Switch user");
		if(adminCheck(userHashData, currentUser)) {
			System.out.println("5. Approve or reject new user or admin");
			System.out.println("6. Lock or unlock account");
		}
		System.out.println("0. Exit");
		for(int i = 0; i < equalsigns*4; i++) System.out.print("=");
		System.out.println();

		

		String input = inputLine();
		Integer inputObject = new Integer(input); 
		int choice = inputObject.intValue();
		
		switch(choice) {
		
		case 0: choice = 0;
		System.out.println("Thank you for testing out Adam Lahey's Project Zero!");
		System.out.println("Later!");
		return;
		
		case 1: choice = 1;
		System.out.println("The current balance is: " + checkBalance(userHashData, currentUser));
		break;
		
        case 2: choice = 2;
		System.out.println("Please enter the deposit amount: ");
		String depositLine = inputLine();
		Double depositObject = new Double (depositLine);
		double deposit = depositObject.doubleValue();
		changeBalance(userHashData, currentUser, deposit);
		System.out.println("The new balance is: " + checkBalance(userHashData, currentUser));
		break;  
		
        case 3: choice = 3;
        System.out.println("Please enter the withdrawn amount: ");
		String withdrawnLine = inputLine();
		Double withdrawnObject = new Double (withdrawnLine);
		double withdrawn = withdrawnObject.doubleValue();
		changeBalance(userHashData, currentUser, -(withdrawn));
		System.out.println("The new balance is: " + checkBalance(userHashData, currentUser));
		break;
		
        case 4: choice = 4;        
		currentUser = login(userHashData);
		break;
		
        case 5: choice = 5;
		System.out.println("Which account would you like to approve or reject? Enter a username: ");
		System.out.println("If you would like a list of possible awaiting accounts, TBD");
		String userApproval = inputLine();
		System.out.println("approve or reject?");
		String approvalStatus = inputLine();
		
		boolean approve = true;
		if(approvalStatus == "reject")
			approve = true;
		else
			approve = false;
		if(userExists(userHashData, userApproval)) {
			userLock(userHashData, userApproval, approve);
			System.out.println(userApproval + " has been " + approvalStatus + "d.");
		}
		else
			System.out.println(userApproval + " has not been found.");
		
		break;
		
        case 6: choice = 6;
		System.out.println("Which account would you like to lock or unlock? Enter a username: ");
		System.out.println("If you would like a list of possible locked accounts, TBD");
		String userLocked = inputLine();
		System.out.println("lock or unlock?");
		String lockStatus = inputLine();
		
		boolean lock = true;
		if(lockStatus == "lock")
			lock = true;
		else
			lock = false;
		if(userExists(userHashData, userLocked)) {
			userLock(userHashData, userLocked, lock);
			System.out.println(userLocked + " has been " + lockStatus + "d.");
		}
		else
			System.out.println(userLocked + " has not been found.");
        break;	     
        
        default:
        System.out.println("You have made an invalid entry. Please try again.");
        mainMenu(userHashData, currentUser);
		}
		
		userHashData = hashMapUserData(userFile);
	    mainMenu(userHashData, currentUser);
		
	}

	
	
}
