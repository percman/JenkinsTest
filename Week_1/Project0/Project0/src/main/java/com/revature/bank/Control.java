package com.revature.bank;

import java.util.*;

public class Control {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		if(Admin.adminMap.isEmpty()) {
			addFirst();
		}
		while(true) {
		if(current!=null) {
			welcomePage();			
		}	

		directory();
		}
	}
	
	public static Person current;
	
	public static void directory() {
		boolean wrongLetter = false;
		do {
			System.out.print("Type 'L' for Login or 'N' for new User: ");
			String direct = sc.next();
			if (direct.equals("L")) login();
			else if (direct.equals("N")) {
				createUser();
			}
			else {
				System.out.println("Only enter one of the approved letters.");
				wrongLetter = true;
			}
		}while (wrongLetter);	
	}
	
	public static void login() {
		boolean wrongLetter = false;
		String type;
		do{
			System.out.println("Enter 'A' for Admin or 'U' for User: ");
			type = sc.next();
			if (!type.equals("A") && !type.equals("U")) {
				System.out.println("Only enter one of the approved letters.");
				wrongLetter = true;
				type = sc.next();
			}
		} while (wrongLetter);
		System.out.print("Enter username: ");
		String username = sc.next();
		System.out.print("Enter password: ");
		String password = sc.next();
		if(type.equals("A")) {
			if (Admin.adminMap.containsKey(username) && 
					password.equals(Admin.adminMap.get(username).password)){				
				current = Admin.adminMap.get(username);
				if(current.approved) welcomePage();
				else System.out.println("You will need to wait for admin approval to log in.");
			}
			else System.out.println("The type/username/password combination does not exist.");
		}
		else if(type.equals("U")) {
			if (User.userMap.containsKey(username) && 
					password.equals(User.userMap.get(username).password)){				
				current = User.userMap.get(username);
				if(current.approved) welcomePage();
				else System.out.println("You will need to wait for admin approval to log in.");
			}
			else System.out.println("The type/username/password combination does not exist.");
		}
		
		
		
	}
	
	public static void logOut() {
		current = null;
		System.out.println("You have logged out.");
		System.out.println();
	}
	
	public static void addFirst() {
		System.out.println("Welcome! You're the first user!");
		System.out.println("An administrative account will be created for you.");
		System.out.print("Create username (no spaces allowed): ");
		String username = sc.next();
		System.out.print("Create password (no spaces allowed: ");
		String password = sc.next();
		Admin first = new Admin (username, password);
		first.approved= true;
		Admin.addAdminToMap(username, first);
		current = first;
		System.out.println("You have been added.");
		System.out.println();
	}
	
	public static void createUser() {
		boolean wrongLetter = false;
		do{
		System.out.print("Type 'A' for Admin or 'U' for User: ");
			String userType=sc.next();
			if(userType.equals("A")) {
				System.out.println("Enter a username (no spaces): ");
				String username = sc.next();
				System.out.println("Enter a password (no spaces): ");
				String password = sc.next();
				if(Admin.adminMap.containsKey(username)) {
					System.out.println("Account already exists.");
				}
				else {
					Admin admin = new Admin(username, password);
					Admin.addAdminToMap(username, admin);
					System.out.println("You will need to wait for admin approval to log in.");
				}	
			}
			else if (userType.equals("U")) {
				System.out.println("Enter a username (no spaces): ");
				String username = sc.next();
				System.out.println("Enter a password (no spaces): ");
				String password = sc.next();
				if(User.userMap.containsKey(username)) {
					System.out.println("Accoiunt already exists.");
				}
				else {
					User user = new User(username, password);
					User.addUserToMap(username, user);
					System.out.println("You will need to wait for admin approval to log in.");
				}	
			}
			else {
				System.out.println("Only enter one of the approved letters.");
				wrongLetter=true;
			}
		}while(wrongLetter);
	}
	
	public static void welcomePage() {
		System.out.println("Welcome, "+current.username + ".");
		System.out.println("You are logged in.");
		boolean wrongLetter = false;
		do{	
			System.out.println("Type 'L' to log out.");
			String log = sc.next();
			if(log.equals("L")) {
				logOut();
			}
			else {
				System.out.println("Only enter one of the approved letters.");
				wrongLetter = true;
			}
		}while(wrongLetter);	
	}
}
