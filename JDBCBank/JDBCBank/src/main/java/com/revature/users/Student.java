package com.revature.users;

import java.io.Serializable;

public class Student extends Person implements Serializable {

	private static final long serialVersionUID = -5944401087659678976L;

	private String firstname;
	private String lastname;
	private String username;
	private String password;

	private int coins;
	private int boughtSubtraction;
	private int boughtMultiplication;
	private int boughtDivision;

	private int isApproved;
	private int isLocked;

	public Student() {
	}

	public Student(String firstname, String lastname, String username, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
	}

	public Student(String firstname, String lastname, String username, int isApproved, int isLocked) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.isApproved = isApproved;
		this.isLocked = isLocked;
	}

	public Student(String firstname, String lastname, String username) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
	}

	public Student(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public Student(String firstname, String lastname, String username, String password, int coins,
			int boughtSubtraction, int boughtMultiplication, int boughtDivision) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.username = username;
		this.password = password;
		this.coins = coins;
		this.boughtSubtraction = boughtSubtraction;
		this.boughtMultiplication = boughtMultiplication;
		this.boughtDivision = boughtDivision;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getCoins() {
		return coins;
	}

	public void setCoins(int coins) {
		this.coins = coins;
	}

	public int isBoughtSubtraction() {
		return boughtSubtraction;
	}

	public void setBoughtSubtraction(int boughtSubtraction) {
		this.boughtSubtraction = boughtSubtraction;
	}

	public int isBoughtMultiplication() {
		return boughtMultiplication;
	}

	public void setBoughtMultiplication(int boughtMultiplication) {
		this.boughtMultiplication = boughtMultiplication;
	}

	public int isBoughtDivision() {
		return boughtDivision;
	}

	public void setBoughtDivision(int boughtDivision) {
		this.boughtDivision = boughtDivision;
	}

	public int getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(int isApproved) {
		this.isApproved = isApproved;
	}

	public int getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(int isLocked) {
		this.isLocked = isLocked;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + boughtDivision;
		result = prime * result + boughtMultiplication;
		result = prime * result + boughtSubtraction;
		result = prime * result + coins;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
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
		return "Student [firstname=" + firstname + ", lastname=" + lastname + ", username=" + username + ", coins="
				+ coins + "]";
	}

}
