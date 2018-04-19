package com.revature.bank;

import java.util.*;

public class Control {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		if(userList.isEmpty()) {
			addFirstUser();
		}
		while(current!=null) {
			System.out.println("You are logged in.");
			System.out.println("Type 'L' to log out.");
			String log = sc.next();
			if(log.equals("L")) {
				logOut();
				System.out.println("You have logged out.");
				System.out.println();
			}
			sc.close();
		}	
		System.out.println("Enter username.");
	}
	
	private static List<Person> userList = new ArrayList<>();
	
	public static void addUserToList(Person user) {
		userList.add(user);
	}
	
	public static Person current;
	
	public static void login(String username, String password) {
		
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
		addUserToList(first);
		current = first;
		System.out.println("You have been added.");
		System.out.println();
	}
}
