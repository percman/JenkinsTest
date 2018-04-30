package com.revature.menus;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.service.MenuService;
import com.revature.service.PrincipalService;
import com.revature.service.StudentService;
import com.revature.service.TeacherService;
import com.revature.singletons.LogThis;
import com.revature.users.Principal;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class Creation {

	private static Scanner sc = new Scanner(System.in);

	public static void createStudent() {
		System.out.println();
		LogThis.info("Create a New Student Profile");

		Student student = new Student();

		try {
			System.out.println("Please enter your first name");
			student.setFirstname(sc.nextLine());
			
			System.out.println("Please enter your last name");
			student.setLastname(sc.nextLine());

			System.out.println("Please choose a username ");
			System.out.println("You will not be able to change this later and must be unique to you");
			String username = sc.nextLine();
			
			while(MenuService.usernameTaken(username)) {
				System.out.println("That username has already been taken, please choose a different one");
				username = sc.nextLine();
			}
			
			student.setUsername(username);

			System.out.println("Please enter a password");
			String password = sc.nextLine();
			student.setPassword(password);

			MenuService.insertUsername(username, "student");

			StudentService.insertStudent(student);

		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Create a New Student " + nsee.getMessage());
			createStudent();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Create a New Student" + ise.getMessage());
			createStudent();
		}

		LogThis.info("Student Account Created");
	}

	public static void createTeacher() {
		System.out.println();
		LogThis.info("Create a New Teacher Profile");

		Teacher teacher = new Teacher();

		try {
			System.out.println("Please enter your first name");
			teacher.setFirstname(sc.nextLine());
			
			System.out.println("Please enter your last name");
			teacher.setLastname(sc.nextLine());

			System.out.println("Please choose a username");
			String username = sc.nextLine();
			
			while(MenuService.usernameTaken(username)) {
				System.out.println("That username has already been taken, please choose a different one");
				username = sc.nextLine();
			}

			teacher.setUsername(username);

			System.out.println("Please enter a password");
			String password = sc.nextLine();
			teacher.setPassword(password);

			MenuService.insertUsername(username, "teacher");

			TeacherService.insertTeacher(teacher);

		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Create a New Teacher " + nsee.getMessage());
			createTeacher();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Create a New Teacher" + ise.getMessage());
			createTeacher();
		}

		LogThis.info("Teacher Account Created");
	}

	public static Principal createPrincipal() {
		System.out.println();
		LogThis.info("Create Principal");

		Principal principal = new Principal();

		try {
			System.out.println("Please enter your first name");
			principal.setFirstname(sc.nextLine());
			
			System.out.println("Please enter your last name");
			principal.setLastname(sc.nextLine());

			System.out.println("Please choose a username");
			String username = sc.nextLine();
			
			while(MenuService.usernameTaken(username)) {
				System.out.println("That username has already been taken, please choose a different one");
				username = sc.next();
			}

			principal.setUsername(username);

			System.out.println("Please enter a password");
			principal.setPassword(sc.nextLine());

			MenuService.insertUsername(username, "principal");

			PrincipalService.insertPrincipal(principal);

		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Create a New Principal " + nsee.getMessage());
			createPrincipal();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Create a New Principal" + ise.getMessage());
			createPrincipal();
		}

		LogThis.info("Principal Account Created");
		return principal;
	}

}
