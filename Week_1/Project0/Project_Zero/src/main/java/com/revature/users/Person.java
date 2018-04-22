package com.revature.users;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.menus.PrincipalMenu;
import com.revature.menus.StartMenu;
import com.revature.menus.StudentMenu;
import com.revature.menus.TeacherMenu;
import com.revature.singletons.LogThis;

public abstract class Person implements Serializable {

	private static final long serialVersionUID = -7837235030867746015L;

	// These are variables that all Person classes have
	private String name;
	private String userName;
	private String password;
	private String type;
	
	private boolean isApproved;
	private boolean isLocked;

	public Person() {
		super();
	}

	public Person(String name, String password) {
		super();
		this.name = name;
		this.password = password;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", userName=" + userName + "]";
	}

	//
	//
	// Method(s) that all Person classes have
	//
	//

	public static void logout(Person user) {
		System.out.println("Are you sure you want to log out?");
		System.out.println("1. Yes");
		System.out.println("2. No");

		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();

		try {
			while (true) {
				if (choice == 1) {
					LogThis.info("You have successfully logged out.");
					sc.close();
					StartMenu.startMenu();
					} else if (choice == 2) {
					sc.close();
					if (user.getType().equals("student")) {
						StudentMenu.studentMenu((Student) user );
					} else if (user.getType().equals("teacher")) {
						TeacherMenu.teacherMenu((Teacher) user);
					} else {
						PrincipalMenu.principalMenu((Principal) user);
					}
					
				} else {
					LogThis.info("Invalid Choice");
					System.out.println("Please enter a 1 to log out or a 2 to return to main menu.");
					choice = sc.nextInt();
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

	// public void changePassword() {
	//
	// }

}
