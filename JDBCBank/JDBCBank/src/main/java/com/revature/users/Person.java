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
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private String type;

	private boolean isApproved = false;
	private boolean isLocked = false;

	public Person() {
		super();
	}

	public Person(String firstname, String password) {
		super();
		this.firstname = firstname;
		this.password = password;
	}

	// getters and setters
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String name) {
		this.firstname = name;
	}
	
	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + (isApproved ? 1231 : 1237);
		result = prime * result + (isLocked ? 1231 : 1237);
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (isApproved != other.isApproved)
			return false;
		if (isLocked != other.isLocked)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", type=" + type
				+ ", isApproved=" + isApproved + ", isLocked=" + isLocked + "]";
	}

	
	
	
	
	
	
	
	//
	//
	// Method(s) that all Person classes have
	//
	//


	private static Scanner sc = new Scanner(System.in);
	
	public static void logout(Person user) {
		System.out.println("Are you sure you want to log out?");
		System.out.println("1. Yes");
		System.out.println("2. No");

		

		try {
			int choice = sc.nextInt();

			while (true) {
				if (choice == 1) {
					LogThis.info("You have successfully logged out.");
					StartMenu.startMenu();
				} else if (choice == 2) {
					if (user.getType().equals("student")) {
						StudentMenu.studentMenu((Student) user);
					} else if (user.getType().equals("teacher")) {
						TeacherMenu.teacherMenu((Teacher) user);
					} else {
						PrincipalMenu.principalMenu((Principal) user);
					}

				} else {
					LogThis.info("Invalid Choice");
					System.out.println("Please enter a 1 to log out or a 2 to return to main menu.");
				}
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Logout " + ime.getMessage());
			logout(user);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Logout " + nsee.getMessage());
			logout(user);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in Logout " + ise.getMessage());
			logout(user);
		}

	}

	// public void changePassword() {
	//
	// }

}
