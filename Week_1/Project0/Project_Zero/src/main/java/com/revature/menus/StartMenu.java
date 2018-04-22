package com.revature.menus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Principal;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class StartMenu {

	private static boolean wasDeserialized = false;
	
	


	public static void startMenu() {

		AccountData ad = AccountData.getInstance();

		// if the data has not been deserialized yet, this statement will run
		// it will not run if someone logs out and returns to the start menu
		 if (!wasDeserialized) {
		 deserializeAccountData(new File("src/main/resources/data.txt"));
		 wasDeserialized = true;
		 }

		// if it is the first time this program is ever run
		// this statement will create a principal
		if (ad.size() == 0) {
			createPrincipal();
		}

		LogThis.info("Start Menu");
		System.out.println("Your options are:");
		System.out.println("1. Login");
		System.out.println("2. Create new student profile");
		System.out.println("3. Create new teacher profile");
		System.out.println("0. Exit");

		Scanner sc = new Scanner(System.in);
		

		try {
			while (true) {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					login();
					break;
				case 2:
					createStudent();
					break;
				case 3:
					createTeacher();
					break;
				case 0:
					serializeAccountData(ad.getHashMap(), new File("src/main/resources/data.txt"));
					sc.close();
					System.exit(0);
				default:
					LogThis.info("Invalid Choice");
					System.out.println("Your options are:");
					System.out.println("1. Login");
					System.out.println("2. Create new student profile");
					System.out.println("3. Create new teacher profile");
					System.out.println("0. Exit");
					choice = sc.nextInt();
					break;
				}
			}
		} catch(InputMismatchException ime) {
			LogThis.warn(ime.getMessage());
		} catch(NoSuchElementException nsee) {
			LogThis.warn(nsee.getMessage());
		} catch(IllegalStateException ise) {
			LogThis.warn(ise.getMessage());
		} finally {
			sc.close();
		}

	}

	public static void login() {
		
		AccountData ad = AccountData.getInstance();
		
		LogThis.info("Login Menu");

		Scanner sc = new Scanner(System.in);
		String name = "";
		String password = "";

		try {
			System.out.println("Please enter your username:");
			name = sc.nextLine();
			System.out.println("Please enter your password:");
			password = sc.nextLine();

		} catch (NoSuchElementException nsee) {
			LogThis.warn(nsee.getMessage());
		} catch (IllegalStateException ise) {
			LogThis.warn(ise.getMessage());
		} finally {
			sc.close();
		}

		String keyCheck = name + ":" + password;

		while (true) {
			if (ad.containsKey(keyCheck)) {
				if (ad.get(keyCheck).getType().equals("student")) {
					StudentMenu.studentMenu((Student) ad.get(keyCheck));
				} else if (ad.get(keyCheck).getType().equals("teacher")) {
					TeacherMenu.teacherMenu((Teacher) ad.get(keyCheck));
				} else {
					PrincipalMenu.principalMenu((Principal) ad.get(keyCheck));
				}
			}

			LogThis.info("Your username and/or password are incorrect");
			try {
				System.out.println("Please enter your username:");
				name = sc.nextLine();
				System.out.println("Please enter your password:");
				password = sc.nextLine();

			} catch (NoSuchElementException nsee) {
				LogThis.warn(nsee.getMessage());
			} catch (IllegalStateException ise) {
				LogThis.warn(ise.getMessage());
			} finally {
				sc.close();
			}

		}

	}

	public static void createStudent() {
		LogThis.info("Create a New Student Profile");

	}

	public static void createTeacher() {
		LogThis.info("Create a New Teacher Profile");

	}

	public static void createPrincipal() {
		LogThis.info("Create Principal");
		Person principal = new Principal();

		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter your first and last name");
		principal.setName(sc.nextLine());

		System.out.println("Please choose a username");
		principal.setUserName(sc.nextLine());

		System.out.println("Please enter a password");
		principal.setPassword(sc.nextLine());

		sc.close();
		
		LogThis.info("Principal Account Created");
		PrincipalMenu.principalMenu((Principal) principal);
	}

	public static void serializeAccountData(Map<String,Person> data, File file) {
		ObjectOutputStream save = null;
		try {
			save = new ObjectOutputStream(new FileOutputStream(new File(file.getPath())));
			save.writeObject(data);
		} catch (IOException ioe) {
			LogThis.warn(ioe.getMessage());
		} finally {
			// gotta close those resources
			try {
				save.close();
			} catch (IOException ioe) {
				LogThis.warn(ioe.getMessage());
			}
		}

	}

	@SuppressWarnings("unchecked")
	public static void deserializeAccountData(File file) {
		
		AccountData ad = AccountData.getInstance();

		if (file.exists()) {
			ObjectInputStream savedData = null;
			try { 
				savedData = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
				
				ad.setHashMap((Map<String, Person>) savedData.readObject());

			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (ClassNotFoundException cnfe) {
				cnfe.printStackTrace();
			} finally {
				// I will beat this dead horse
				// close that resource
				try {
					savedData.close();
				} catch (IOException ioe) {
					LogThis.warn(ioe.getMessage());
				}
			}
		}
//		AccountData.getInstance();
	}
}
