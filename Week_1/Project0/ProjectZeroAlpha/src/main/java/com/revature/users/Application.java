package com.revature.users;

import java.util.HashMap;

@SuppressWarnings("unused")
public class Application extends Admin{
	
	// Not sure why this needs a serialID, given the files it inherits already has them
	// but here it is anyway :D 
	private static final long serialVersionUID = -8881654296306890719L;



	// Greeting screen runs given no userFile
	public static void firstRun() {
		userFile.delete();
		
		System.out.println("Hello! Welcome to the first run of Adam Lahey's Project Zero!");
		System.out.println("Note that the first time login requires creation of a new user.");
		System.out.println("This user will be deemed an administrator, meaning they can create new accounts.");
		System.out.println("To begin, please enter your username: ");
		String username = aquireLine();
		System.out.println("Security is important. But time is even more precious.");
		System.out.println("So please enter your password, and I promise not to store in plain text.");
		System.out.println("Please don't try and break my program yet :( it's fragile");
		String password = aquireLine();
		System.out.println("Note that this account is an administrator.");
		System.out.println("If you would like to enter a starting balance, please do so now.");
		String balancestring = aquireLine();
		Double doubleObject = new Double(balancestring);
		double startingbalance = doubleObject.doubleValue();
		
		addUserAsAdmin(username, password, true, false, startingbalance);
		
		
	}
	
	// Allows selection of various activities to do 
	public static void mainMenu(HashMap<Integer, User> userHashData, String currentUser) {
		int equalsigns = 20;
		for(int i = 0; i < equalsigns; i++) System.out.print("=");
		System.out.print(" Main Menu ");
		for(int i = 0; i < equalsigns; i++) System.out.print("=");
		System.out.println();
		System.out.println("You are currently logged in as: " + currentUser);
		System.out.println("\nPlease choose from the list below.");
		System.out.println("1. Check balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Change username or password");
		if(adminCheck(userHashData, currentUser)) {
			System.out.println("5. Approve or reject new user or admin");
			System.out.println("6. Lock or unlock account");
		}
		
		
		String input = aquireLine();
		if(input.length() > 1)
			System.out.println("You have made an invalid entry.");
		
		
	}
	
	// Allows for login given a username and password 
	public static String login(HashMap<Integer, User> userHashData) {
		System.out.println("Please enter your user or admin name: ");
		String username = aquireLine();
		
		System.out.println("Please enter your password: ");
		String password = aquireLine();
		
		if(passwordCheck(userHashData, username, password)) {
			System.out.println("You have successfully logged in.");
			return username;
		}
		
		System.out.println("Invalid username or password.");
		return login(userHashData);
	}
	
	
	
	public static void main(String[] args) {	
		
//		userFile.delete();
//		
//		
//		if(!userFile.exists())
//			firstRun();

		// Create a HashMap of all users
		// this is created here so it does not have to be recreated multiple times 
		// can query this until an account is changed 
		HashMap<Integer, User> userHashData = new HashMap<>();
		userHashData = hashMapUserData(userFile);


		System.out.println("Hello! Welcome to Adam Lahey's Project Zero!\n\n");
		String currentUser = login(userHashData);
		mainMenu(userHashData, currentUser);
		
		// ================= EASY TESTING BELOW =================
		// should be automated using junit, but this is quick and dirty 
		
//		userFile.delete();
//		
//		User p = addUserAsAdmin("adamL", "easypass", true, 53.6);
//		User v = addUserAsAdmin("admjl", "ExCeLl3nT_passW0rd", false, 573.6);
//		User f = addUserAsAdmin("sfd2", "lkaslkjdf", true, 531.6);
//		
//						
//		HashMap<Integer, User> userHashData = new HashMap<>();
//				
//		userHashData = hashMapUserData(userFile);
//		
//		System.out.println(userHashData);
//		System.out.println(userHashData.size());
//		
//		System.out.println("===========");
//		
//		System.out.println(passwordCheck(userHashData, p.getName(), p.getPassword()));
		
		
		
		
		
		
		codeCleanUp();
	}

}