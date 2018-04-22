package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Principal;

public class PrincipalMenu {

	public static void principalMenu(Principal principal) {
		LogThis.info("Principal Menu");
		System.out.println("Your options are:");
		System.out.println("1. Approve new Teacher Profiles");
		System.out.println("2. Lock Teacher Profile");
		System.out.println("3. Unlock Teacher Profile");
		System.out.println("0. Logout");

		Scanner sc = new Scanner(System.in);

		try {
			while (true) {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					break;
				case 2:
					break;
				case 3:
					break;
				case 0:
					Person.logout(principal);
				default:
					LogThis.info("Invalid Choice");
					System.out.println("Your options are:");
					System.out.println("1. Approve new Teacher Profiles");
					System.out.println("2. Lock Teacher Profile");
					System.out.println("3. Unlock Teacher Profile");
					System.out.println("0. Logout");
					choice = sc.nextInt();
					break;
				}
			}
		} catch (InputMismatchException ime) {
			LogThis.warn(ime.getMessage());
		} catch (NoSuchElementException nsee) {
			LogThis.warn(nsee.getMessage());
		} catch (IllegalStateException ise) {
			LogThis.warn(ise.getMessage());
		} finally {
			sc.close();
		}

	}

	// Approve teacher
	static void approveTeacher() {
		LogThis.info("Approve Teacher Menu");

	}

	// Lock teacher
	static void lockTeacher() {
		LogThis.info("Lock Teacher Menu");

	}

	// Unlock teacher
	static void unlockTeacher() {
		LogThis.info("Unlock Teacher Menu");

	}

}
