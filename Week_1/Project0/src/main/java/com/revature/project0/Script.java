package com.revature.project0;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Script {
	private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));//reads from console for all interactions
	static StringTokenizer tokenizer;//tokenizer for all inputs
	static String input;
	//handles the basic start up for the application
	public static void start() {
		
		File userFile = new File("src/main/resources/userData.txt");
		File adminFile = new File("src/main/resources/adminData.txt");
		if(userFile.length() != 0) {
			Record.users = UserSerializer.deSerializeUser(userFile);
		}
		else {
			Record.users =new HashSet<>();
		}
		if(userFile.length() != 0) {
			Record.admins = AdminSerializer.deSerializeAdmin(adminFile);
		}
		else {
			Record.admins =new HashSet<>();
		}
		System.out.print("Type login to login or new user to create a new user:");
		try {
			input = read.readLine().toLowerCase();
			if(input.equals("login")) {
			login();
			}
			else if(input.equals("new user")){
			createUser();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			AdminSerializer.serializeAdmin(Record.admins, new File("src/main/resources/adminData.txt"));
			UserSerializer.serializeUser(Record.users, new File("src/main/resources/userData.txt"));
			Record.admins.clear();
			Record.users.clear();
			try {			
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	//handles the script that handles the creation of a new user
	public static void createUser(){
	try {
		System.out.print("Type admin to create an admin or user to create a user:");
		String type = read.readLine().replaceAll("\\s+","");
		System.out.print("Enter the username for the new user:");
		String username = read.readLine().replaceAll("\\s+","");
		System.out.print("Enter the password for the new user:");
		String password = read.readLine().replaceAll("\\s+","");
		NewUser user = NewUserFactory.getUser(type,username,password);
		if(type.equals("admin")) {
			FileIO.addAdmin((Admin)user);
			adminHub(FileIO.getAdmin(username));	
		}
		else if(type.equals("user")) {
		FileIO.addNewUser((User)user);
		userHub(FileIO.getUser(username));
		}
	} catch (IOException e) {
		e.printStackTrace();
	} catch (UserTypeNotFoundException e) {
		e.printStackTrace();
	}finally {
		AdminSerializer.serializeAdmin(Record.admins, new File("src/main/resources/adminData.txt"));
		UserSerializer.serializeUser(Record.users, new File("src/main/resources/userData.txt"));
		Record.admins.clear();
		Record.users.clear();
		try {			
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
	
	//handles running the script that handles the login method
	public static void login() {
		try {
			System.out.print("Please enter your username:");
			String username = read.readLine();
			if(Login.userExists(username)) {
				System.out.print("Please enter your password:");
				String password = read.readLine();
				if(Login.checkPassword(password, username)){
					User user = FileIO.getUser(username);
					if(!user.isUserApproved()) {
						System.out.print("Your account has not yet been approved by an admin.");
					}
					if(!user.isUserLocked()) {
						System.out.print("Your account has been locked by an admin.");
					}
					System.out.print("Welcome " + username);
					userHub(FileIO.getUser("username"));
				}
				System.out.print("That password is not correct for this account. ");
			}
			if(Login.adminExists(username)) {
				System.out.print("Please enter your password:");
				String password = read.readLine();
				if(Login.checkPassword(password, username)){
					System.out.print("you have been successfully logged in as an admin.");
					adminHub(FileIO.getAdmin("username"));
				}
				System.out.print("That password is not correct for this account.");
			}
			else {	
			System.out.print("couldn't find user.");
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally {
			AdminSerializer.serializeAdmin(Record.admins, new File("src/main/resources/adminData.txt"));
			UserSerializer.serializeUser(Record.users, new File("src/main/resources/userData.txt"));
			Record.admins.clear();
			Record.users.clear();
			try {			
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void userHub(User user) {
		String input = "";
		while(!input.equals("quit")&&!input.equals("q")) {
		try {
			System.out.print("Type add to add a movie to your collection, remove to remove a movie from your collction, view to view your collection or quit to stop:");
			input = read.readLine().toLowerCase();
			switch(input) {
			case "add":
				System.out.print("Enter the movies you want to add to your collection seperated by a common:");
				String titles = read.readLine();
				tokenizer = new StringTokenizer(titles, ",");
				while(tokenizer.hasMoreTokens()) {
				user.addMovie(tokenizer.nextToken());
				}
				break;
			case "remove":
				System.out.print("Enter the movies you want to remove from your collection seperated by a common:");
				titles = read.readLine();
				tokenizer = new StringTokenizer(titles, ",");
				while(tokenizer.hasMoreTokens()) {
				user.removeMovie(tokenizer.nextToken());
				}
				break;
			case "view":
				System.out.print("Your collection currently consists of:");
				user.viewMovies();
				break;
			case "quit":
				System.out.print("You have quit the application:");
				break;
			case "q":
				System.out.print("You have quit the application:");
				break;
			default:
				System.out.print("please enter add,remove or view.");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			AdminSerializer.serializeAdmin(Record.admins, new File("src/main/resources/adminData.txt"));
			UserSerializer.serializeUser(Record.users, new File("src/main/resources/userData.txt"));
			Record.admins.clear();
			Record.users.clear();
			try {			
				read.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		}
	}

	public static void adminHub(Admin admin) {
	String input = "";
	while(!input.equals("quit")&&!input.equals("q")) {
	try {
		System.out.print("Type lock to lock a user, unlock to unlock a user, approve to approve a user or quit to stop:");
		input = read.readLine().toLowerCase();
		String user ="";
		switch(input) {
		case "lock":
			System.out.print("The list of users that can be locked are");
			FileIO.scanLocked();
			System.out.print("Type the names of the users you want to lock seperated by a comma:");
			user = read.readLine();
			tokenizer = new StringTokenizer(user, ",");
			while(tokenizer.hasMoreTokens()) {
			admin.setLocked(true,FileIO.getUser(tokenizer.nextToken()));
			}
			break;
		case "unlock":
			System.out.print("The list of users that can be unlocked are");
			FileIO.scanUnlocked();
			System.out.print("Type the names of the users you want to unlock seperated by a comma:");
			user = read.readLine();
			tokenizer = new StringTokenizer(user, ",");
			while(tokenizer.hasMoreTokens()) {
			admin.setLocked(false,FileIO.getUser(tokenizer.nextToken()));
			}
			break;
		case "approve":
			System.out.print("The list of users that are pending approval");
			FileIO.scanApproved();
				System.out.print("Type the name of the users you want to approve separated by a comma:");
				user = read.readLine();
				tokenizer = new StringTokenizer(user, ",");
				while(tokenizer.hasMoreTokens()) {
				admin.approve(FileIO.getUser(tokenizer.nextToken()));
				}
			break;
		case "quit":
			System.out.print("You have quit the application.");
			break;
		case "q":
			System.out.print("You have quit the application.");
			break;
		default:
			System.out.print("please enter lock,unlock or approve.");
		}
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		AdminSerializer.serializeAdmin(Record.admins, new File("src/main/resources/adminData.txt"));
		UserSerializer.serializeUser(Record.users, new File("src/main/resources/userData.txt"));
		Record.admins.clear();
		Record.users.clear();
		try {			
			read.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	}
}
}	
