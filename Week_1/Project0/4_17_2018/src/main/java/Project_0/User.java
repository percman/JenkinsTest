package Project_0;

import java.io.Serializable;

public class User implements Serializable
{

	private static final long serialVersionUID = -8221461477618358704L;
	
	private boolean admin;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private float accountBalance;
	private int accountNumber;
	
	// Default no-arg constructor
	public User() {}
	
	// constructor with all parameters
	public User(boolean admin, String firstName, String lastName, String userName, String password,
			float accountBalance, int accountNumber) {
		super();
		this.admin = admin;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.accountBalance = accountBalance;
		this.accountNumber = accountNumber;
	}
	
	// Getter and setters related to administrator priviledges
	public void promoteAdmin() {
		admin = true;
	}
	public boolean getAdmin() {
		return admin;
	}
	public void demoteAdmin() {
		admin = false;
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
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	
	// Getter and Setters for password
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	// Getter and Setters for Account balance
	public void setAccountBalance(float accountBalance) {
		this.accountBalance = accountBalance;
	}
	public float getAccountBalance() {
		return accountBalance;
	}
	
	// Getter and Setters for Account number
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getAccountNumber() {
		return accountNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(accountBalance);
		result = prime * result + accountNumber;
		result = prime * result + (admin ? 1231 : 1237);
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
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
		User other = (User) obj;
		if (Float.floatToIntBits(accountBalance) != Float.floatToIntBits(other.accountBalance))
			return false;
		if (accountNumber != other.accountNumber)
			return false;
		if (admin != other.admin)
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
	
	
}
