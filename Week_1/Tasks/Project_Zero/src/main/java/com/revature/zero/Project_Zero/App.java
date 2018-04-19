package com.revature.zero.Project_Zero;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Cameron Skaggs Project Zero
 *
 */
public class App {

	public static int numUsers = 0;
	public static ArrayList<User> userList = new ArrayList<User>();
	//Initial page for welcoming new and returning users
	public static void welcome () {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Hello, new user? (Y/N) ");
		String response = input.next();
		while ((!response.equals("Y")) & (!response.equals("N"))) {
			System.out.println("Please enter Y or N");
			response = input.next();
		}
		//create new user
		if(response.equals("Y")) {
			System.out.println("Welcome New User");
			newUser();
		}
		//returning user
		else {
			System.out.println("Welcome Back");
			returnUser();
		}
	}
	//user has already been created
	public static void returnUser() {
		//variable that will be true if the user name is a return
		boolean recognized = false;
		Scanner input = new Scanner(System.in);
		//ask user for user name
		System.out.println("Enter Username: ");
		String userName = input.next();
		for(User u : userList) {
			if(u.getName().equals(userName)) {
				System.out.println("welcome back " + userName);
				recognized = true;
				if (u.isAdmin()) {
					adminMethod();
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
						userMethod();
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
	public static void newUser() {
		boolean taken = true;
		while (taken == true) {	
			taken = false;
			Scanner input = new Scanner(System.in);
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
					newAdmin(userName);
				}
				else {
					printUserList();
					User user = new User(userName, 0, false, false, false);
					userList.add(user);
					System.out.println("Please wait to be approved by admin");
				}
			}
		}
	}
	
	public static void newAdmin(String userName) {
		User user = new User(userName, 0, true, false, true);
		userList.add(user);
		adminMethod();
	}
	public static void userMethod() {
		
	}
	public static void adminMethod() {
		Scanner input = new Scanner(System.in);
		System.out.println("Would you like to");
		System.out.println("Approve/Reject Users? (1)");
		System.out.println("Lock/Unlock Users? (2)");
		System.out.println("Quit (3)");
		int response = input.nextInt();
		
		//Loop until a valid response is given
		while(response > 3 | response < 1) {
			System.out.println("Please enter 1 or 2");
			response = input.nextInt();
		}
		//Approve or reject users
		if(response == 1) {
			System.out.println("Approve or Reject Users");
		}
		//Lock or unlock users
		if(response == 2) {
			System.out.println("Lock or Unlock");
		}
	}
	public static void approveOrReject() {
		for(User u : userList) {
			if (!u.isApproved()) {
				System.out.println("User " + u.getName() + " is awaiting approval");
			}
		}
	}
	public static void printUserList() {
		for(User u : userList) {
			System.out.println(u.getName());
		}
	}
    public static void main ( String[] args ) {
    	while (true) {
    		printUserList();
    		welcome();
    	}
    }
}
