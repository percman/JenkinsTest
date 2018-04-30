package com.revature.menus;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.exceptions.InvalidChoiceException;
import com.revature.service.PrincipalService;
import com.revature.service.TeacherService;
import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Principal;
import com.revature.users.Teacher;

public class PrincipalMenu {
	
	private static Scanner sc = new Scanner(System.in);


	public static void principalMenu(Principal principal) {
		LogThis.info("Principal Menu");
		
		System.out.println("Your options are:");
		System.out.println("1. Approve new Teacher Profiles");
		System.out.println("2. Lock Teacher Profile");
		System.out.println("3. Unlock Teacher Profile");
		System.out.println("0. Logout");
		
		try {
			int choice = sc.nextInt();
			
			while (true) {
				switch (choice) {
				case 1:
					approveTeacher(principal);
					return;
				case 2:
					lockTeacher(principal);
					return;
				case 3:
					unlockTeacher(principal);
					return;
				case 0:
					Person.logout(principal);
					return;
				default:
					throw new InvalidChoiceException();
				}
				
			}
		} catch (InvalidChoiceException ice) {
			LogThis.warn(ice.getMessage());
			principalMenu(principal);
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Principal Menu " + ime.getMessage());
			principalMenu(principal);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Principal Menu " + nsee.getMessage());
			principalMenu(principal);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Principal Menu " + ise.getMessage());
			principalMenu(principal);
		} 

	}

	// Approve teacher
	private static void approveTeacher(Principal principal) {
		LogThis.info("Approve Teacher Menu");

		try {
			System.out.println("The following Teachers need to be approved:");
			List<Teacher> approve = PrincipalService.getUnapprovedTeachers();
			for (Teacher t : approve) {
					System.out.println(t.getFirstname() + " " + t.getLastname() + ", username: " + t.getUsername());
			}
			System.out.println("1. Approve All");
			System.out.println("2. Approve a specific teacher");
			System.out.println("0. Return to Principal Menu");
			
			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					PrincipalService.approveAllTeachers();
					LogThis.info("All teachers were approved");
					return;
				case 2:
					System.out.println("What is the username of the teacher you would like to approve?");
					String username = sc.next();
					PrincipalService.approveTeacher(username);
					System.out.println(TeacherService.getTeacher(username).getFirstname() + " " 
							+ TeacherService.getTeacher(username).getLastname() + " was approved");
					approveTeacher(principal);
					return;
				case 0:
					principalMenu(principal);
					return;
				default:
					throw new InvalidChoiceException();
				}

			}

		} catch (InvalidChoiceException ice) {
			LogThis.warn(ice.getMessage());
			approveTeacher(principal);
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Approve Teacher Menu " + ime.getMessage());
			approveTeacher(principal);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Approve Teacher Menu " + nsee.getMessage());
			approveTeacher(principal);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Approve Teacher Menu " + ise.getMessage());
			approveTeacher(principal);
		} 
	}

	// Lock teacher
	private static void lockTeacher(Principal principal) {
		LogThis.info("Lock Teacher Menu");

		try {
			System.out.println("The following Teachers are unlocked:");

			for (Person p : ad.values()) {
				if (p.getType() == "teacher" && p.isLocked()) {
					System.out.println(p.getFirstname());
				}
			}
			System.out.println("1. Lock a specific teacher");
			System.out.println("0. Return to Principal Menu");

			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					System.out.println("What is the name of the teacher you would like to lock?");
					String name = sc.next();
					for (Person p : ad.values()) {
						if (p.getFirstname() == name && !p.isLocked()) {
							p.setLocked(true);
							LogThis.info("The teacher account for " + name + " was locked.");
							break;
						}
					}
					lockTeacher(principal);
					return;
				case 0:
					principalMenu(principal);
					return;
				default:
					throw new InvalidChoiceException();

				}
			}

		} catch (InvalidChoiceException ice) {
			LogThis.warn(ice.getMessage());
			lockTeacher(principal);
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Lock Teacher Menu " + ime.getMessage());
			lockTeacher(principal);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Lock Teacher Menu " + nsee.getMessage());
			lockTeacher(principal);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Lock Teacher Menu " + ise.getMessage());
			lockTeacher(principal);
		} 

	}

	// Unlock teacher
	private static void unlockTeacher(Principal principal) {
		LogThis.info("Unlock Teacher Menu");

		try {
			System.out.println("The following Teachers need to be unlocked:");

			for (Person p : ad.values()) {
				if (p.getType() == "teacher" && p.isLocked()) {
					System.out.println(p.getFirstname() + " " + p.getLastname());
				}
			}
			System.out.println("1. Unlock All");
			System.out.println("2. Unlock a specific teacher");
			System.out.println("0. Return to Principal Menu");

			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					for (Person p : ad.values()) {
						if (p.getType() == "teacher" && p.isLocked()) {
							p.setLocked(false);
						}
					}
					LogThis.info("All teachers were unlocked");
					unlockTeacher(principal);
					return;
				case 2:
					System.out.println("What is the first name of the teacher you would like to unlock?");
					String firstname = sc.next();
					for (Person p : ad.values()) {
						if (p.getFirstname() == firstname && p.isLocked()) {
							p.setLocked(false);
							break;
						}
					}
					unlockTeacher(principal);
					return;
				case 0:
					principalMenu(principal);
					return;
				default:
					throw new InvalidChoiceException();
				}

			}

		} catch (InvalidChoiceException ice) {
			LogThis.warn(ice.getMessage());
			unlockTeacher(principal);
		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in Unlock Teacher Menu " + ime.getMessage());
			unlockTeacher(principal);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Unlock Teacher Menu " + nsee.getMessage());
			unlockTeacher(principal);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Unlock Teacher Menu " + ise.getMessage());
			unlockTeacher(principal);
		} 
	}

}
