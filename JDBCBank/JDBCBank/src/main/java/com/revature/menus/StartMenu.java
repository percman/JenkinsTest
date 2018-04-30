package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.exceptions.InvalidChoiceException;
import com.revature.exceptions.InvalidUserTypeException;
import com.revature.service.MenuService;
import com.revature.singletons.LogThis;
import com.revature.users.Principal;

public class StartMenu {

	private static Scanner sc = new Scanner(System.in);

	public static void startMenu() {


		// if it is the first time this program is ever run
		// this statement will create a principal
		if (MenuService.principalExists() == 0) {
			Principal principal = Creation.createPrincipal();
			PrincipalMenu.principalMenu(principal);
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
					StartMenu.startMenu();
					return;
				case 3:
					Creation.createTeacher();
					StartMenu.startMenu();
					return;
				case 0:
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
		System.out.println();
		LogThis.info("Login Menu");

		String username;
		String password;
		String type;

		try {
			System.out.println("Please enter your username:");
			username = sc.next();

			System.out.println("Please enter your password:");
			password = sc.next();

			type = MenuService.getType(username);
			
			LoginFactory.chooseLogin(username, password, type);
			return;
			
		} catch (NoSuchElementException nsee) {
			LogThis.warn(nsee.getMessage());
			login();
		} catch (IllegalStateException ise) {
			LogThis.warn(ise.getMessage());
			login();
		} catch(InvalidUserTypeException iute) {
			LogThis.warn(iute.getMessage());
			login();

		}
	}

}
