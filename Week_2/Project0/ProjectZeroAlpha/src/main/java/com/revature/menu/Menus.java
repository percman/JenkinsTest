package com.revature.menu;

import static com.revature.readwrite.ReadWrite.inputLine;
import static com.revature.menu.SpecialUserMethods.createAdmin;
import static com.revature.menu.SpecialUserMethods.createUser;
import static com.revature.service.UserService.login;

import com.revature.exceptions.ChoiceInputException;
import com.revature.logstatus.LogHere;
import com.revature.users.User;

public class Menus {

	
	/* A first run method
	 * The first welcome screen! 
	 */
	public static void firstRun() {
		
		System.out.println("Hello! Welcome to the first run of Adam Lahey's Project Zero!");
		System.out.println("Note that the first time login requires creation of a new admin.");
		
		createAdmin();
	}
	

	/* Allows a User to login 
	 * 
	 */
	public static User loginAttempt() {
		User newuser = new User(); 
		System.out.println("Please enter your user or admin name: ");
		String username = inputLine();
		
		System.out.println("Please enter your password: ");
		String password = inputLine();
		
		newuser.setName(username);
		newuser.setPassword(password);
		
		String logintest = login(newuser);
		
		if(logintest.equals("invalid")) {
			System.out.println("Continue? T or F");
			String cont = inputLine();
			if(cont.equals("T") || cont.equals("t") || cont.equals("true"))
				loginAttempt();
			System.out.println("Program is now exiting. You may create a new user upon login.");
			System.exit(0);
		}
		
		if(logintest.equals("locked")) {
			System.out.println("However, you appear to be locked. Please speak to an admin.");
			System.out.println("Note that new users must be approved by admins.\n");
			newuser = welcomeScreen();
		}
		
		return newuser;
	}
	

	/* Standard welcome screen
	 * Takes HashMap 'userHashData' for login and user creation purposes (to check against)
	 */
	public static User welcomeScreen() {
		int choice = 100;
		User curruser = null;
		
		System.out.println("Hello! Welcome to Adam Lahey's Project Zero!\n");
		System.out.println("1. Login");
		System.out.println("2. Create new account");
		System.out.println("0. Exit");
		System.out.println();
		
		try {
			String input = inputLine();
			Integer inputObject = new Integer(input); 
			choice = inputObject.intValue();			
		} catch (NumberFormatException nfe) {
			System.out.println("That's not even a number!");
			LogHere.warn(nfe.getMessage());
		}
		
		switch(choice) {
		
		case 0: choice = 0;
			System.out.println("Thank you for testing out Adam Lahey's Project Zero!");
			System.out.println("Later!");
			System.exit(0);
		break;
		
		case 1: choice = 1;
		return curruser = loginAttempt();
		
		case 2: choice = 2;
			createUser();
		return curruser = welcomeScreen();
		
		default:
	        try {
				throw new ChoiceInputException();
			} catch (ChoiceInputException cie) {
				LogHere.warn(cie.getMessage());
			}
	        return curruser = welcomeScreen();
		}
		
		return curruser;
	}
	
	
	/* The Main Menu
	 * easy to adjust :)
	 * Takes HashMap 'userHashData' for given to other methods 
	 */
//	public static void mainMenu(User currentUser) {
//		
//		int choice = 100;
//		
//
//		//Easy adjustment of header and footer
//		int equalsigns = 10;
//		
//		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
//		System.out.print(" Main Menu ");
//		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
//		System.out.println();
//		
//		System.out.println("You are currently logged in as: " + currentUser);
//		System.out.println("\nPlease choose from the list below.");
//		System.out.println("1. Check balance");
//		System.out.println("2. Deposit");
//		System.out.println("3. Withdraw");
//		System.out.println("4. Switch user");
//		System.out.println("5. Create new account");
//		if(currentUser.isAdminstatus()) {
//			System.out.println("6. Approve or reject new user or admin");
//			System.out.println("7. Lock or unlock account");
//			System.out.println("8. Upgrade account to admin");
//		}
//		System.out.println("0. Exit");
//		for(int i = 0; i < equalsigns*4; i++) System.out.print("=");
//		System.out.println();
//
//		// Converts the string given to an integer value 
//		
//		try {
//			String input = inputLine();
//			Integer inputObject = new Integer(input); 
//			choice = inputObject.intValue();			
//		} catch (NumberFormatException nfe) {
//			System.out.println("That's not even a number!");
//			LogHere.warn(nfe.getMessage());
//		}
//		
//		switch(choice) {
//	
//		case 0: choice = 0;
//			System.out.println("Thank you for testing out Adam Lahey's Project Zero!");
//			System.out.println("Later!");
//			System.exit(0);
//		
//		case 1: choice = 1;
//			System.out.println("The current balance is: " + checkBalance(userHashData, currentUser));
//		break;
//		
//        case 2: choice = 2;
//			System.out.println("Please enter the deposit amount: ");
//			String depositLine = inputLine();
//			Double depositObject = new Double (depositLine);
//			double deposit = depositObject.doubleValue();
//			changeBalance(userHashData, currentUser, deposit);
//			System.out.println("The new balance is: " + checkBalance(userHashData, currentUser));
//		break;  
//		
//        case 3: choice = 3;
//	        System.out.println("Please enter the withdrawn amount: ");
//			String withdrawnLine = inputLine();
//			Double withdrawnObject = new Double (withdrawnLine);
//			double withdrawn = withdrawnObject.doubleValue();
//			changeBalance(userHashData, currentUser, -(withdrawn));
//			System.out.println("The new balance is: " + checkBalance(userHashData, currentUser));
//		break;
//		
//        case 4: choice = 4;        
//			currentUser = login(userHashData);
//		break;
//        
//        case 5: choice = 5;
//			createUser();
//        break;
//		
//        case 6: choice = 6;
//			System.out.println("Which account would you like to approve or reject? Enter a username: ");
//			System.out.println("If you would like a list of possible awaiting accounts, TBD");
//			String userApproval = inputLine();
//			System.out.println("approve or reject?");
//			String approvalStatus = inputLine();
//			
//			boolean approve = true;
//			if(approvalStatus == "approve")
//				approve = true;
//			else
//				approve = false;
//			if(userExists(userHashData, userApproval)) {
//				userLock(userHashData, userApproval, approve);
//				System.out.println(userApproval + " has been " + approvalStatus + "d.");
//			}
//			else
//				System.out.println(userApproval + " has not been found.");
//			
//		break;
//		
//        case 7: choice = 7;
//			System.out.println("Which account would you like to lock or unlock? Enter a username: ");
//			System.out.println("If you would like a list of possible locked accounts, TBD");
//			String userLocked = inputLine();
//			System.out.println("lock or unlock?");
//			String lockStatus = inputLine();
//			
//			boolean lock = true;
//			if(lockStatus == "lock")
//				lock = true;
//			else
//				lock = false;
//			if(userExists(userHashData, userLocked)) {
//				userLock(userHashData, userLocked, lock);
//				System.out.println(userLocked + " has been " + lockStatus + "d.");
//			}
//			else
//				System.out.println(userLocked + " has not been found.");
//        break;	     
//        
//        default:
//	        try {
//				throw new ChoiceInputException();
//			} catch (ChoiceInputException cie) {
//				LogHere.warn(cie.getMessage());
//			}
//	        mainMenu(userHashData, currentUser);
//		}
//		
//		userHashData = hashMapUserData(userFile);
//	    mainMenu(userHashData, currentUser);
//	}
	

	
	
}
