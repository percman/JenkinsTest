package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.exceptions.InvalidChoiceException;
import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Principal;

public class PrincipalMenu {
	
	private static Scanner sc = new Scanner(System.in);
	private static AccountData ad = AccountData.getInstance();


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
			for (Person p : ad.values()) {
				if (p.getType() == "teacher" && !p.isApproved()) {
					System.out.println(p.getName());
				}
			}
			System.out.println("1. Approve All");
			System.out.println("2. Approve a specific teacher");
			System.out.println("0. Return to Principal Menu");
			
			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					for (Person p : ad.values()) {
						if (p.getType() == "teacher" && !p.isApproved()) {
							p.setApproved(true);
						}
					}
					LogThis.info("All teachers were approved");
					approveTeacher(principal);
					return;
				case 2:
					System.out.println("What is the name of the teacher you would like to approve?");
					String name = sc.next();
					for (Person p : ad.values()) {
						if (p.getName() == name && !p.isApproved()) {
							p.setApproved(true);
							break;
						}
					}
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
					System.out.println(p.getName());
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
						if (p.getName() == name && !p.isLocked()) {
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
					System.out.println(p.getName());
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
					System.out.println("What is the name of the teacher you would like to unlock?");
					String name = sc.next();
					for (Person p : ad.values()) {
						if (p.getName() == name && p.isLocked()) {
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
