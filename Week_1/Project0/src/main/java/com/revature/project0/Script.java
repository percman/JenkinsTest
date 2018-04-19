package com.revature.project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Script {
	private static BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	public static void start() {
		System.out.println("Do you wish to login or create a new user");
		try {
			if(read.readLine().toLowerCase().equals("login")) {
			login();
			}
			else if(read.readLine().toLowerCase().equals(" create a new user")) {
			createUser();	
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void createUser(){
	try {
		System.out.println("Is this new user an admin or just a user");
		String type = read.readLine();
		System.out.println("Enter the username for the new user");
		String username = read.readLine();
		System.out.println("Enter the password for the new user");
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
	
	//handles running the main login interaction 
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
						System.out.println("Your account has not yet been approved by an admin");
					}
					if(!user.isUserLocked()) {
						System.out.println("Your account has been locked by an admin");
					}
					System.out.println("Welcome " + username);
					//userHub
				}
				System.out.println("That password is not correct for this account");
			}
			if(Login.adminExists(username)) {
				System.out.println("Please enter your password:");
				String password = read.readLine();
				if(Login.checkPassword(password, username)){
					System.out.println("you have successfully logged in admin");
					//adminHub()
				}
				System.out.println("That password is not correct for this account");
			}
				
			System.out.println("couldn't find user");
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
