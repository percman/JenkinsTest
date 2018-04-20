package com.revature.project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Script {
	private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));//reads from console for all interactions
	//handles the basic start up for the application
	public static void start() {
		System.out.print("Type login to login or new user to create a new user:");
		try {
			if(read.readLine().toLowerCase().equals("login")) {
			login();
			}
			else if(read.readLine().toLowerCase().equals("new user")) {
			createUser();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//handles the script that handles the creation of a new user
	public static void createUser(){
	try {
		System.out.println("Type admin to create an admin or user to create a user:");
		String type = read.readLine();
		System.out.println("Enter the username for the new user:");
		String username = read.readLine();
		System.out.println("Enter the password for the new user:");
		String password = read.readLine();
		NewUser user = NewUserFactory.getUser(type,username,password);
		//if(user is an admin){add admin,run adminHub}
		//else if(user is a user){add user,userHub}
	} catch (IOException e) {
		e.printStackTrace();
	} catch (UserTypeNotFoundException e) {
		e.printStackTrace();
	}
	}
	
	//handles running the script that handles the login method
	public static void login() {
		try {
			System.out.println("Please enter your username:");
			String username = read.readLine();
			if(Login.userExists(username)) {
				System.out.println("Please enter your password:");
				String password = read.readLine();
				if(Login.checkPassword(password, username)){
					User user = FileIO.getUser(username);
					if(!user.isUserApproved()) {
						System.out.println("Your account has not yet been approved by an admin.");
					}
					if(!user.isUserLocked()) {
						System.out.println("Your account has been locked by an admin.");
					}
					System.out.println("Welcome " + username);
					userHub(FileIO.getUser("username"));
				}
				System.out.println("That password is not correct for this account.");
			}
			if(Login.adminExists(username)) {
				System.out.println("Please enter your password:");
				String password = read.readLine();
				if(Login.checkPassword(password, username)){
					System.out.println("you have been successfully logged in as an admin.");
					adminHub(FileIO.getAdmin("username"));
				}
				System.out.println("That password is not correct for this account.");
			}
				
			System.out.println("couldn't find user.");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public static void userHub(User user) {
		String input = "";
		while(input.equals("quit")||input.equals("q")) {
		try {
			System.out.println("Type add to add a movie to your collection, remove to remove a movie from your collction, view to view your collection or quit to stop:");
			input = read.readLine().toLowerCase();
			switch(input) {
			case "add":
				System.out.println("What movie do you want to add to your collection:");
				user.addMovie(read.readLine());
				break;
			case "remove":
				System.out.println("What movie do you want to remove from your collection:");
				user.removeMovie(read.readLine());
				break;
			case "view":
				System.out.println("Your collection currently consists of:");
				user.viewMovies();
				break;
			case "quit":
				System.out.println("You have quit the application:");
				break;
			case "q":
				System.out.println("You have quit the application:");
				break;
			default:
				System.out.println("please enter add,remove or view.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		}
	}

	public static void adminHub(Admin admin) {
	String input = "";
	while(input.equals("quit")||input.equals("q")) {
	try {
		System.out.println("Type lock to lock a user, unlock to unlock a user, approve to approve a user or quit to stop:");
		input = read.readLine().toLowerCase();
		String user ="";
		switch(input) {
		case "lock":
			System.out.println("The list of users that can be locked are");
			FileIO.scanLocked();
			while(user.equals("back")) {
				System.out.println("Type the name of the user you want to lock or type back to return to the main screen:");
				user = read.readLine().toLowerCase();
				if(!user.equals("back"))
					admin.setLocked(true, FileIO.getUser(user));
				}
			break;
		case "unlock":
			System.out.println("The list of users that can be unlocked are");
			FileIO.scanUnlocked();
			while(user.equals("back")) {
			System.out.println("Type the name of the user you want to unlock or type back to return to the main screen:");
			user = read.readLine().toLowerCase();
			if(!user.equals("back"))
				admin.setLocked(false, FileIO.getUser(user));
			}
			break;
		case "approve":
			System.out.println("The list of users that are pending approval");
			FileIO.scanApproved();
			while(user.equals("back")) {
				System.out.println("Type the name of the user you want to approve or type back to return to the main screen:");
				user = read.readLine().toLowerCase();
				if(!user.equals("back"))
					admin.approve(FileIO.getUser(user));
				}
			break;
		case "quit":
			System.out.println("You have quit the application.");
			break;
		case "q":
			System.out.println("You have quit the application.");
			break;
		default:
			System.out.println("please enter lock,unlock or approve.");
		}
	} catch (IOException e) {
		e.printStackTrace();
	}
	}
}
}	
