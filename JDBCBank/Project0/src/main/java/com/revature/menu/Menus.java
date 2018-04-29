package com.revature.menu;

import static com.revature.menu.MenuAddons.createAdmin;
import static com.revature.menu.MenuOptions.getBankMenuOptions;
import static com.revature.menu.MenuOptions.getAdminMenuOptions;
import static com.revature.menu.MenuOptions.getUserMenuOptions;
import static com.revature.menu.MenuOptions.getWelcomeMenuOptions;
import static com.revature.readwrite.ReadWrite.inputLine;
import static com.revature.service.UserService.login;

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
		
		curruser = getWelcomeMenuOptions(choice, curruser);
		
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
		
		if(currentUser.isAdminstatus()) {
			currentUser = getAdminMenuOptions(choice,currentUser);
		}else {
			currentUser = getUserMenuOptions(choice, currentUser);
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
		
		getBankMenuOptions(choice, currentUser);
		
	}
	
}
