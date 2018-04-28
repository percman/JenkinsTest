package com.revature.users;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.menus.PrincipalMenu;
import com.revature.menus.StartMenu;
import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;

public class Creation {

	private static AccountData ad = AccountData.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void createStudent() {
		LogThis.info("Create a New Student Profile");

		Person student = new Student();

		try {
			System.out.println("Please enter your first name");
			student.setFirstname(sc.nextLine());
			
			System.out.println("Please enter your last name");
			student.setLastname(sc.nextLine());

			System.out.println("Please choose a username");
			String username = sc.next();
			student.setUsername(username);

			System.out.println("Please enter a password");
			String password = sc.next();
			student.setPassword(password);

			student.setType("student");

			ad.put(username + ":" + password, student);

		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Create a New Student " + nsee.getMessage());
			createStudent();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Create a New Student" + ise.getMessage());
			createStudent();
		}

		LogThis.info("Student Account Created");
		StartMenu.startMenu();
	}

	public static void createTeacher() {
		LogThis.info("Create a New Teacher Profile");

		Person teacher = new Teacher();

		try {
			System.out.println("Please enter your first name");
			teacher.setFirstname(sc.nextLine());
			
			System.out.println("Please enter your last name");
			teacher.setLastname(sc.nextLine());

			System.out.println("Please choose a username");
			String username = sc.next();
			teacher.setUsername(username);

			System.out.println("Please enter a password");
			String password = sc.next();
			teacher.setPassword(password);

			teacher.setType("teacher");

			ad.put(username + ":" + password, teacher);

		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Create a New Teacher " + nsee.getMessage());
			createTeacher();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Create a New Teacher" + ise.getMessage());
			createTeacher();
		}

		LogThis.info("Teacher Account Created");
		StartMenu.startMenu();
	}

	public static void createPrincipal() {
		LogThis.info("Create Principal");

		Person principal = new Principal();

		try {
			System.out.println("Please enter your first name");
			principal.setFirstname(sc.nextLine());
			
			System.out.println("Please enter your last name");
			principal.setLastname(sc.nextLine());

			System.out.println("Please choose a username");
			String username = sc.next();
			principal.setUsername(username);

			System.out.println("Please enter a password");
			principal.setPassword(sc.next());
			String password = principal.getPassword();

			principal.setType("principal");

			ad.put(username + ":" + password, principal);

		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Create a New Principal " + nsee.getMessage());
			createPrincipal();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Create a New Principal" + ise.getMessage());
			createPrincipal();
		}

		LogThis.info("Principal Account Created");
		PrincipalMenu.principalMenu((Principal) principal);
	}

}
