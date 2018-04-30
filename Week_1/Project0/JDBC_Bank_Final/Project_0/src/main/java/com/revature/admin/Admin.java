package com.revature.admin;

import java.io.Serializable;
import java.text.DecimalFormat;

import com.revature.factory.UserInterface;
import com.revature.jdbc.TiCoService;

/**
 * Admin class object. In the current implementation this class is hardly used but is here for demonstration
 * that the factory is properly implemented.
 * @author Jesse
 *
 */

public class Admin implements Serializable, UserInterface {

	private static final long serialVersionUID = 5360196251001293837L;

	// variables
	private DecimalFormat df2 = new DecimalFormat("#0.00");
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private double accountBalance;
	private int accountNumber;
	private int approved;
	private boolean admin;
	private boolean locked;

	// Default no-arg constructor
	public Admin() {
	}

	// constructor with all parameters
	public Admin(int accountNumber, String firstName, String lastName, String userName, String password,
			float accountBalance, int approved, boolean locked, boolean admin) {
		super();
		this.admin = admin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = userName;
		this.password = password;
		this.accountBalance = accountBalance;
		this.accountNumber = accountNumber;
		this.locked = locked;
		this.approved = approved;
	}

	// Methods for administrator settings
	public void promoteAdmin() {
		this.admin = true;
	}

	public void demoteAdmin() {
		this.admin = false;
	}

	public boolean getAdmin() {
		return admin;
	}

	// Methods for locking/unlocking account
	public void lock() {
		this.locked = true;
	}

	public void unlock() {
		this.locked = false;
	}

	public boolean isLocked() {
		return locked;
	}

	// Methods for approving/denying account
	public void approve() {
		this.approved = 2;
	}

	public void setPending() {
		this.approved = 1;
	}

	public void deny() {
		this.approved = 0;
	}

	public int isApproved() {
		return approved;
	}

	// Getter and setters for first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	// Getter and Setters for userName
	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getUsername() {
		return username;
	}

	// Getter and Setters for password
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	// Getter and Setters for Account balance
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountBalance() {
		return df2.format(accountBalance);
	}

	// Getter and Setters for Account number
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	// Overridden toString for specific format
	@Override
	public String toString() {
		return firstName + " " + lastName + "\n\tusername: " + username + "\n\tAccount Number: #" + accountNumber
				+ "\n\tAccount Balance: " + TiCoService.getBalance(accountNumber) + "\n\tAdministrator: " + admin
				+ "\n\tLocked: " + locked + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(accountBalance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + accountNumber;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + approved;
		result = prime * result + ((df2 == null) ? 0 : df2.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Admin other = (Admin) obj;
		if (Double.doubleToLongBits(accountBalance) != Double.doubleToLongBits(other.accountBalance))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (admin != other.admin)
			return false;
		if (approved != other.approved)
			return false;
		if (df2 == null) {
			if (other.df2 != null)
				return false;
		} else if (!df2.equals(other.df2))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (locked != other.locked)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
}
