package com.revature.project_0;

import java.io.Serializable;
import java.text.DecimalFormat;

public class User implements Serializable
{

	private static final long serialVersionUID = -8221461477618358704L;
	
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
	public User() {}
	
	// constructor with all parameters
	public User(boolean admin, String firstName, String lastName, String userName, String password,
			float accountBalance, int accountNumber, boolean locked, int approved) {
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
				+ "\n\tAccount Balance: $" + df2.format(accountBalance) + "\n\tAdministrator: " + admin + "\n\tLocked: " + locked + "\n"  ;
	}
}
