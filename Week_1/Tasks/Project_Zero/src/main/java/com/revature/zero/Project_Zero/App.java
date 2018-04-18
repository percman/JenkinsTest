package com.revature.zero.Project_Zero;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
	public static int numUsers = 0;
	public static void welcome () {
		
		Scanner input = new Scanner(System.in);
		System.out.println("Hello, new user? (Y/N) ");
		String response = input.next();
		while ((!response.equals("Y")) & (!response.equals("N"))) {
			System.out.println("Please enter Y or N");
			response = input.next();
		}
		if(response.equals("Y")) {
			System.out.println("Welcome New User");
			newUser();
		}
		else {
			System.out.println("Welcome Back");
			returnUser();
		}
	}
	public static void returnUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Username: ");
		String userName = input.next();
		System.out.println("welcome back " + userName);
	}
	public static void newUser() {
		Scanner input = new Scanner(System.in);
		System.out.println("Create Username: ");
		String userName = input.next();
		System.out.println("Username " + userName + " is available");
		if (numUsers == 0) {
			newAdmin(userName);
		}	
		else {
			System.out.println("Please wait to be approved by admin");
		}
	}
	
	public static void newAdmin(String userName) {
		
	}
    public static void main ( String[] args ) {
    	welcome();
    }
}
