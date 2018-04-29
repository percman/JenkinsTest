package com.revature.menu;

import static com.revature.menu.MenuAddons.createAdmin;
import static com.revature.menu.MenuAddons.createUser;
import static com.revature.readwrite.ReadWrite.inputLine;
import static com.revature.service.UserService.getUser;
import static com.revature.service.UserService.getAllUsers;
import static com.revature.service.UserService.getUserTime;
import static com.revature.service.UserService.login;
import static com.revature.service.UserService.updateUser;

import java.util.List;

import com.revature.exceptions.ChoiceInputException;
import com.revature.logstatus.LogHere;
import com.revature.users.User;

public class Menus {

	
	/* A first run method
	 * The first welcome screen! 
	 */
	public static void firstRun() {
		
		System.out.println("\nHello! Welcome to the first run of Adam Lahey's Project Zero!");
		System.out.println("\nNote that the first time login requires creation of a new admin.");
		
		createAdmin();
	}
	

	/* Allows a User to login 
	 * 
	 */
	public static User loginAttempt() {
		User newuser = new User(); 
		System.out.println("Please enter your username: ");
		String username = inputLine();
		
		System.out.println("Please enter your password: ");
		String password = inputLine();
		
		newuser.setName(username);
		newuser.setPassword(password);
		
		newuser = login(newuser);
		
		if(newuser == null) {
			System.out.println("Continue? T or F");
			String cont = inputLine();
			if(cont.equals("T") || cont.equals("t") || cont.equals("true"))
				return loginAttempt();
			System.out.println("Program is now exiting. You may create a new user upon login.");
			System.exit(0);
		}
		
		if(newuser.isLocked()) {
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
		
		System.out.println("\nHello! Welcome to Adam Lahey's Project Zero!\n");
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
	 * 
	 *  
	 */
	public static void mainMenu(User currentUser) {
		
		int choice = 100;
		//Easy adjustment of header and footer
		int equalsigns = 10;
		
		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
		System.out.print(" Main Menu ");
		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
		System.out.println();
		
		System.out.println("You are currently logged in as: " + currentUser.getUsername());
		System.out.println("\nPlease choose from the list below.");
		System.out.println("1. Banking App");
		System.out.println("2. Switch user");
		System.out.println("3. Create new account");
		if(currentUser.isAdminstatus()) {
			System.out.println("4. Approve or reject new user or admin");
			System.out.println("5. Lock or unlock account");
			System.out.println("6. Upgrade account to admin");
			System.out.println("7. Check time of new user creation");
			System.out.println("8. List all users");
		}
		System.out.println("0. Exit");
		for(int i = 0; i < equalsigns*5; i++) System.out.print("=");
		System.out.println();

		// Converts the string given to an integer value 
		
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
		
		case 1: choice = 1;
			bankMenu(currentUser);
		break;
		
        case 2: choice = 2;        
			currentUser = loginAttempt();
		break;
        
        case 3: choice = 3;
			createUser();
        break;
		
        case 4: choice = 4;
			boolean approved;
			System.out.println("Which account would you like to approve or reject? Enter a username: ");
			String userApproval = inputLine();
			User approvedUser = getUser(userApproval);
			
			System.out.println("approve or reject?");
			String approvalStatus = inputLine();
			if(approvalStatus == "approve")
				approved = true;
			else
				approved = false;
			
			approvedUser.setLocked(approved);
			updateUser(approvedUser);
		break;
		
        case 5: choice = 5;
        	boolean locked;
			System.out.println("Which account would you like to lock or unlock? Enter a username: ");
			String userLocked = inputLine();
			User lockedUser = getUser(userLocked);
			System.out.println("lock or unlock?");
			String lockStatus = inputLine();			
			if(lockStatus == "unlock")
				locked = false;
			else
				locked = true;
			lockedUser.setLocked(locked);
			updateUser(lockedUser);
        break;	     
        
        case 6: choice = 6;
			System.out.println("Which account would you like to upgrade? Enter a username: ");
			String upgraded = inputLine();
			User upgradeUser = getUser(upgraded);
			upgradeUser.setAdminstatus(true);
			updateUser(upgradeUser);
		break;
		
        case 7: choice = 7;
        	System.out.println("Which user would you like to see the time of creation? Enter a username");
        	String usertimed = inputLine();
        	User userTime = getUser(usertimed);
        	System.out.println(getUserTime(userTime));
        break;
        
        case 8: choice = 8;
        	List<User> userList = getAllUsers();
        	for(User u : userList)
        		System.out.println(u);
        	
    	break;
        
        default:
	        try {
				throw new ChoiceInputException();
			} catch (ChoiceInputException cie) {
				LogHere.warn(cie.getMessage());
			}
	        mainMenu(currentUser);
		}
		
	    mainMenu(currentUser);
	}
	

	/* Banking Menu 
	 * handles all bank transactions
	 */
	public static void bankMenu(User currentUser) {
		int equalsigns = 10;
		int choice = 100;

		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
		System.out.print(" The Bank ");
		for(int i = 0; i < equalsigns*2; i++) System.out.print("=");
		System.out.println();
		
		System.out.println("You are currently logged in as: " + currentUser.getUsername());
		System.out.println("1. Check balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("0. Back to Main Menu");
		
		for(int i = 0; i < equalsigns*5; i++) System.out.print("=");
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
			System.out.println("Back to Main Menu");
			mainMenu(currentUser);
		break;
		
		case 1: choice = 1;
			System.out.println("The current balance is: " + currentUser.getBalance());
		break;
		
		case 2: choice = 2;
			System.out.println("Please enter the deposit amount: ");
			double deposit = 0;
			try {
				String depositLine = inputLine();
				Double depositObject = new Double (depositLine);
				deposit = depositObject.doubleValue();		
			} catch (NumberFormatException nfe) {
				System.out.println("That's not even a number!");
				LogHere.warn(nfe.getMessage());
			}
			currentUser.setBalance(currentUser.getBalance() + deposit);			
			System.out.println("The new balance is: " + currentUser.getBalance());
			updateUser(currentUser);
		break;  
		
      case 3: choice = 3;
	        System.out.println("Please enter the withdrawn amount: ");
			double withdrawn = 0;
			try {
				String withdrawnLine = inputLine();
				Double withdrawnObject = new Double (withdrawnLine);
				withdrawn = withdrawnObject.doubleValue();	
			} catch (NumberFormatException nfe) {
				System.out.println("That's not even a number!");
				LogHere.warn(nfe.getMessage());
			}
			currentUser.setBalance(currentUser.getBalance() - withdrawn);			
			System.out.println("The new balance is: " + currentUser.getBalance());
			updateUser(currentUser);
		break;
		
        default:
	        try {
				throw new ChoiceInputException();
			} catch (ChoiceInputException cie) {
				LogHere.warn(cie.getMessage());
			}
	        mainMenu(currentUser);
		}
		
	}
	
}
