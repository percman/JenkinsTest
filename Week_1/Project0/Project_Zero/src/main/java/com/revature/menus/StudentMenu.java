package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Student;

public class StudentMenu {

	private static Scanner sc = new Scanner(System.in);

	public static void studentMenu(Student student) {

		if (!student.isApproved()) {
			System.out.println("Your account has not been approved by your teacher.");
			System.out.println("Please try to login again later.");
			StartMenu.startMenu();
			return;
		} else if (student.isLocked()) {
			System.out.println("Your account has been locked.");
			System.out.println("Please talk to your teacher.");
			StartMenu.startMenu();
			return;
		} else {
			LogThis.info("Student Menu");
			System.out.println("You have " + student.getCoins() + " coins!");
			System.out.println("Your options are:");
			System.out.println("1. Earn more coins by completing arithmetic problems");
			System.out.println("2. Buy more arithmetic problems with your coins");
			System.out.println("0. Logout");
		}
		

		try {
			while (true) {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					EarnCoinsMenu.earnCoinsMenu(student);
					return;
				case 2:
					SpendCoinsMenu.spendCoinsMenu(student);
					return;
				case 0:
					Person.logout(student);
					return;
				default:
					LogThis.info("Invalid Choice");
					System.out.println("Your options are:");
					System.out.println("You have " + student.getCoins() + " coins!");
					System.out.println("1. Earn more coins by completing arithmetic problems");
					System.out.println("2. Buy more arithmetic problems with your coins");
					System.out.println("0. Logout");
					break;
				}
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Student Menu " + ime.getMessage());
			studentMenu(student);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Student Menu " + nsee.getMessage());
			studentMenu(student);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateExcdeption in Student Menu " + ise.getMessage());
			studentMenu(student);
		} 

	}

}
