package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Teacher;

public class TeacherMenu {

	public static void teacherMenu(Teacher teacher) {
		LogThis.info("Teacher Menu");
		System.out.println("Your options are:");
		System.out.println("1. Approve new Student Profiles");
		System.out.println("2. Lock Student Profile");
		System.out.println("3. Unlock Student Profile");
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
					Person.logout(teacher);
				default:
					LogThis.info("Invalid Choice");
					System.out.println("Your options are:");
					System.out.println("1. Approve new Student Profiles");
					System.out.println("2. Lock Student Profile");
					System.out.println("3. Unlock Student Profile");
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

	// Approve student
	static void approveStudent() {

	}

	// Lock Student
	static void lockStudent() {

	}

	// Unlock Student
	static void unlockStudent() {

	}

}
