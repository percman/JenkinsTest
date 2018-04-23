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
import com.revature.users.Creation;
import com.revature.users.Person;
import com.revature.users.Principal;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class StartMenu {

	private static boolean wasDeserialized = false;

	private static AccountData ad = AccountData.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void startMenu() {

		// if the data has not been deserialized yet, this statement will run
		// it will not run if someone logs out and returns to the start menu
		if (!wasDeserialized) {
			deserializeAccountData(new File("src/main/resources/data.txt"));
			wasDeserialized = true;
		}

		// if it is the first time this program is ever run
		// this statement will create a principal
		if (ad.size() == 0) {
			Creation.createPrincipal();
			return;
		}

		LogThis.info("Start Menu");
		System.out.println("Your options are:");
		System.out.println("1. Login");
		System.out.println("2. Create new student profile");
		System.out.println("3. Create new teacher profile");
		System.out.println("0. Exit");

		try {
			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					login();
					return;
				case 2:
					Creation.createStudent();
					return;
				case 3:
					Creation.createTeacher();
					return;
				case 0:
					serializeAccountData(ad.getHashMap(), new File("src/main/resources/data.txt"));
					//
					sc.close(); // this is the only place in my application that I call sc.close
					//
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
		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in Start Menu " + ime.getMessage());
			startMenu();
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Start Menu " + nsee.getMessage());
			startMenu();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Start Menu " + ise.getMessage());
			startMenu();
		}

	}

	public static void login() {

		LogThis.info("Login Menu");

		String name;
		String password;

		try {

			while (true) {
				System.out.println("Please enter your username:");
				name = sc.next();
				
				System.out.println("Please enter your password:");
				password = sc.next();

				String keyCheck = name + ":" + password;

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
					name = sc.next();
					System.out.println("Please enter your password:");
					password = sc.next();

				} catch (NoSuchElementException nsee) {
					LogThis.warn(nsee.getMessage());
					login();
				} catch (IllegalStateException ise) {
					LogThis.warn(ise.getMessage());
					login();
				}
			}
		} catch (NoSuchElementException nsee) {
			LogThis.warn(nsee.getMessage());
			login();
		} catch (IllegalStateException ise) {
			LogThis.warn(ise.getMessage());
			login();
		}
	}

//	private static void createNewStudent() {
//		Person newStudent = Creation.createStudent();
//
//		ad.put(newStudent.getUserName() + ":" + newStudent.getPassword(), newStudent);
//
//		startMenu();
//
//	}
//
//	private static void createNewTeacher() {
//		Person newTeacher = Creation.createTeacher();
//
//
//		startMenu();
//	}

	public static void serializeAccountData(Map<String, Person> data, File file) {
		LogThis.info("Data was Serialized.");

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
	// The suppressed warning comes from casting the object being read to a map
	public static void deserializeAccountData(File file) {

		if (file.exists()) {
			ObjectInputStream savedData = null;
			try {
				savedData = new ObjectInputStream(new FileInputStream(new File(file.getPath())));

				ad.setHashMap((Map<String, Person>) savedData.readObject());

				LogThis.info("Data was deserialized.");

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
	}
}
