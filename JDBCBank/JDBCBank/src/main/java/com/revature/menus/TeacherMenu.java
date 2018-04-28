package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.exceptions.InvalidChoiceException;
import com.revature.singletons.AccountData;
import com.revature.singletons.LogThis;
import com.revature.users.Person;
import com.revature.users.Teacher;

public class TeacherMenu {
	
	private static AccountData ad = AccountData.getInstance();
	private static Scanner sc = new Scanner(System.in);

	public static void teacherMenu(Teacher teacher) {

		if (!teacher.isApproved()) {
			System.out.println("Your account has not been approved by your principal.");
			System.out.println("Please try to login again later.");
			StartMenu.startMenu();
			return;
		} else if (teacher.isLocked()) {
			System.out.println("Your account has been locked.");
			System.out.println("Please talk to your teacher.");
			StartMenu.startMenu();
			return;
		} else {
			LogThis.info("Teacher Menu");
			System.out.println("Your options are:");
			System.out.println("1. Approve new Student Profiles");
			System.out.println("2. Lock Student Profile");
			System.out.println("3. Unlock Student Profile");
			System.out.println("0. Logout");
		}


		try {
			while (true) {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					approveStudent(teacher);
					return;
				case 2:
					lockStudent(teacher);
					return;
				case 3:
					unlockStudent(teacher);
					return;
				case 0:
					Person.logout(teacher);
					return;
				default:
					throw new InvalidChoiceException();
				}
			}
		} catch (InvalidChoiceException ice) {
			LogThis.warn(ice.getMessage());
			teacherMenu(teacher);
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Teacher Menu " + ime.getMessage());
			teacherMenu(teacher);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Teacher Menu " + nsee.getMessage());
			teacherMenu(teacher);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Teacher Menu " + ise.getMessage());
			teacherMenu(teacher);
		}

	}

	// Approve student
	private static void approveStudent(Teacher teacher) {
		LogThis.info("Approve Student Menu");


		try {
			System.out.println("The following students need to be approved:");
			for (Person p : ad.values()) {
				if (p.getType() == "student" && !p.isApproved()) {
					System.out.println(p.getName());
				}
			}
			System.out.println("1. Approve All");
			System.out.println("2. Approve a specific student");
			System.out.println("0. Return to Teacher Menu");

			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					for (Person p : ad.values()) {
						if (p.getType() == "student" && !p.isApproved()) {
							p.setApproved(true);
						}
					}
					LogThis.info("All students were approved");
					approveStudent(teacher);
					return;
				case 2:
					System.out.println("What is the name of the student you would like to approve?");
					String name = sc.next();
					for (Person p : ad.values()) {
						if (p.getName() == name && !p.isApproved()) {
							p.setApproved(true);
							break;
						}
					}
					approveStudent(teacher);
					return;
				case 0:
					teacherMenu(teacher);
					return;
				default:
					throw new InvalidChoiceException();
				}

			}

		} catch (InvalidChoiceException ice) {
			LogThis.warn(ice.getMessage());
			approveStudent(teacher);
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Approve Student Menu " + ime.getMessage());
			approveStudent(teacher);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Approve Student Menu " + nsee.getMessage());
			approveStudent(teacher);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Approve Student Menu " + ise.getMessage());
			approveStudent(teacher);
		}

	}

	// Lock Student
	private static void lockStudent(Teacher teacher) {
		LogThis.info("Lock Student Menu");

		try {
			System.out.println("The following students are unlocked:");

			for (Person p : ad.values()) {
				if (p.getType() == "student" && !p.isLocked()) {
					System.out.println(p.getName());
				}
			}
			System.out.println("1. Lock a specific student");
			System.out.println("0. Return to Teacher Menu");

			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					System.out.println("What is the name of the student you would like to lock?");
					String name = sc.next();
					for (Person p : ad.values()) {
						if (p.getName() == name && !p.isLocked()) {
							p.setLocked(true);
							LogThis.info("The student account for " + name + " was locked.");
							break;
						}
					}
					lockStudent(teacher);
					return;
				case 0:
					teacherMenu(teacher);
					return;
				default:
					LogThis.info("Invalid Choice");
					System.out.println("The following students are unlocked:");

					for (Person p : ad.values()) {
						if (p.getType() == "student" && p.isLocked()) {
							System.out.println(p.getName());
						}
					}
					System.out.println("1. Lock a specific student");
					System.out.println("0. Return to Teacher Menu");
					choice = sc.nextInt();
					break;

				}
			}

		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in Lock Student Menu " + ime.getMessage());
			lockStudent(teacher);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Lock Student Menu " + nsee.getMessage());
			lockStudent(teacher);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Lock Student Menu " + ise.getMessage());
			lockStudent(teacher);
		}

	}

	// Unlock Student
	private static void unlockStudent(Teacher teacher) {
		LogThis.info("Unlock Student Menu");


		try {
			System.out.println("The following students need to be unlocked:");

			for (Person p : ad.values()) {
				if (p.getType() == "student" && p.isLocked()) {
					System.out.println(p.getName());
				}
			}
			System.out.println("1. Unlock All");
			System.out.println("2. Unlock a specific student");
			System.out.println("0. Return to Teacher Menu");

			int choice = sc.nextInt();

			while (true) {
				switch (choice) {
				case 1:
					for (Person p : ad.values()) {
						if (p.getType() == "student" && p.isLocked()) {
							p.setLocked(false);
						}
					}
					LogThis.info("All students were unlocked");
					unlockStudent(teacher);
					return;
				case 2:
					System.out.println("What is the name of the student you would like to unlock?");
					String name = sc.next();
					for (Person p : ad.values()) {
						if (p.getName() == name && p.isLocked()) {
							p.setLocked(false);
							break;
						}
					}
					unlockStudent(teacher);
					return;
				case 0:
					teacherMenu(teacher);
					return;
				default:
					LogThis.info("Invalid Choice");
					System.out.println("The following students need to be unlocked:");

					for (Person p : ad.values()) {
						if (p.getType() == "student" && p.isLocked()) {
							System.out.println(p.getName());
						}
					}
					System.out.println("1. Unlock All");
					System.out.println("2. Unlock a specific student");
					System.out.println("0. Return to Teacher Menu");
					choice = sc.nextInt();
					break;
				}

			}

		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in Unlock Student Menu " + ime.getMessage());
			unlockStudent(teacher);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Unlock Student Menu " + nsee.getMessage());
			unlockStudent(teacher);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Unlock Student Menu " + ise.getMessage());
			unlockStudent(teacher);
		}
	}

}
