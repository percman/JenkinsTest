package com.revature.projectZero;

import java.io.Serializable;
import java.util.Scanner;

public class Application implements Serializable {

	private static final long serialVersionUID = -5080182619109412880L;

	private static boolean wasDeserialized;

	//
	// This class will run the structure of the program
	//

	public static void main(String[] args) {
		Application.startMenu();

	}

	// this method provide the start screen for the program
	public static void startMenu() {
		
		// if the data has not been deserialized yet, this statement will run
		// it will not run if someone logs out and returns to the start menu
		if(!wasDeserialized) {
			deserialize();
			wasDeserialized = true;
		}
		
		System.out.println("Your options are:");
		System.out.println("1. Login");
		System.out.println("2. Create new student profile");
		System.out.println("3. Create new teacher profile");
		System.out.println("0. Exit");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		//
		//
		// Change this to a switch ? maybe
		// Add try catch finally
		// 
		
		try {
			while(true) {
				switch (choice) {
					case 1:
						Application.login();
						break;
					case 2:
						Application.createStudent();
						break;
					case 3:
						Application.createTeacher();
						break;
					case 0:
						Application.serialize();
						break;
					default:
						LogThis.info("Invalid Choice");
						System.out.println("Please enter a 1 to login, a 2 to create a new student profile,");
						System.out.println("a 3 to create a new teacher profile, or a 0 to exit the program.");
						choice = sc.nextInt();
						break;
				}
			}
		} finally {
			sc.close();
		}
		
		
		
//		while (true) {
//			if (choice == 1) {
//				Application.login();
//			} else if (choice == 2) {
//				Application.createStudent();
//			} else if (choice == 3) {
//				Application.createTeacher();
//			} else if(choice == 0) {
//				sc.close();
//				Application.serialize();
//			} else {
//				System.out.println("Please enter a 1 to login, a 2 to create a new student profile,");
//				System.out.println("a 3 to create a new teacher profile, or a 0 to exit the program.");
//				choice = sc.nextInt();
//			}
//		}
	}

	// This method will allow the user to login
	public static void login() {
		System.out.println("Login page");
		System.out.println("Please enter your username:");
	}

	public static void createStudent() {
		System.out.println("Create a New Student Profile");

	}

	public static void createTeacher() {
		System.out.println("Create a New Teacher Profile");

	}

	public static void serialize() {

	}

	public static void deserialize() {

	}

}
