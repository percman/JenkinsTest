package com.revature.menus;

import java.io.File;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.exceptions.InvalidChoiceException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.serialization.Deserialize;
import com.revature.serialization.Serialize;
import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;
import com.revature.users.Creation;
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
			Deserialize.deserializeAccountData(new File("src/main/resources/data.txt"));
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
					Serialize.serializeAccountData(ad.getHashMap(), new File("src/main/resources/data.txt"));
					//
					sc.close(); // this is the only place in my application that I call sc.close
					//
					System.exit(0);
				default:
					throw new InvalidChoiceException();
				}
			}
		} catch (InvalidChoiceException ice) {
			LogThis.warn(ice.getMessage());
			startMenu();
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

	private static void login() {

		LogThis.info("Login Menu");

		String name;
		String password;

		try {
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
			
			throw new InvalidLoginException();
		} catch (InvalidLoginException ile) {
			LogThis.warn(ile.getMessage());
			
			System.out.println("1. Try to login again");
			System.out.println("0. Return to Start Menu");
			
			try {
				int choice = sc.nextInt();

				while (true) {
					switch (choice) {
					case 1:
						login();
						return;
					case 0:
						startMenu();
					default:
						throw new InvalidChoiceException();
					}
				}
			} catch (InvalidChoiceException ice) {
				LogThis.warn(ice.getMessage());
				login();
			} catch (InputMismatchException ime) {
				LogThis.warn("InpupMismatchException in Login Menu " + ime.getMessage());
				login();
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in Login Menu " + nsee.getMessage());
				login();
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in Login Menu " + ise.getMessage());
				login();
			}
			
		} catch (NoSuchElementException nsee) {
			LogThis.warn(nsee.getMessage());
			login();
		} catch (IllegalStateException ise) {
			LogThis.warn(ise.getMessage());
			login();
		}
	}

}
