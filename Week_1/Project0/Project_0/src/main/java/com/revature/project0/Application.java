package com.revature.project0;

import java.util.Scanner;

public class Application {

	//
	// This class will run the structure of the program
	//

	public static void main(String[] args) {
		Application.start();

	}

	// this method provide the start screen for the program
	public static void start() {
		System.out.println("Your options are:");
		System.out.println("1. Login");
		System.out.println("2. Create new student profile");
		System.out.println("3. Create new teacher profile");
		System.out.println("0. Exit");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		while (true) {
			if (choice == 1) {
				Application.login();
			} else if (choice == 2) {
				Application.createStudent();
			} else if (choice == 3) {
				Application.createTeacher();
			} else if(choice == 0) {
				sc.close();
				System.exit(0);
			} else {
				System.out.println("Please enter a 1 to login, a 2 to create a new student profile,");
				System.out.println("a 3 to create a new teacher profile, or a 0 to exit the program.");
				choice = sc.nextInt();
			}
		}
	}

	// This method will allow the user to login
	public static void login() {
		System.out.println("Login page");
		
	}

	public static void createStudent() {
		System.out.println("Create a New Student Profile");
		
	}

	public static void createTeacher() {
		System.out.println("Creste a New Teacher Profile");
		
	}
	

}
