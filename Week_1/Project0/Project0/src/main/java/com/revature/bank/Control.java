package com.revature.bank;

import java.util.*;

public class Control {
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		if(Person.userMap.isEmpty()) {
			addFirstUser();
		}
		while(true) {
		if(current!=null) {
			welcomePage();			
		}	
		login();
		}
	}
	
	public static Person current;
	
	public static void login() {
		System.out.println("Enter username.");
		String username = sc.next();
		System.out.println("Enter password.");
		String password = sc.next();
		if (Person.userMap.containsKey(username) && 
				password.equals(Person.userMap.get(username).password)){
			current = Person.userMap.get(username);
		}
		else System.out.println("The username/password combination does not exist.");
	}
	
	public static void logOut() {
		current = null;
	}
	
	public static void addFirstUser() {
		System.out.println("Welcome! You're the first user!");
		System.out.println("An administrative account will be created for you.");
		System.out.print("Create username (no spaces allowed): ");
		String username = sc.next();
		System.out.print("Create password (no spaces allowed: ");
		String password = sc.next();
		Admin first = new Admin (username, password);
		Person.addUserToMap(username, first);
		current = first;
		System.out.println("You have been added.");
		System.out.println();
	}
	
	public static void welcomePage() {
		System.out.println("Welcome, "+current.username + ".");
		System.out.println("You are logged in.");
		System.out.println("Type 'L' to log out.");
		String log = sc.next();
		if(log.equals("L")) {
			logOut();
			System.out.println("You have logged out.");
			System.out.println();
		}
	}
}
