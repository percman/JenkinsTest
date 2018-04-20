package com.revature.users;


import java.io.Serializable;

public class User extends SerializationOfUsers implements Serializable{

	
	private static final long serialVersionUID = -4269544628204763811L;

	private String name; 
	private static transient String password;
	private boolean adminStatus;
	private double balance;
	
	public User() {}


	public User(String name, String password, boolean adminstatus, double balance) {
		super();
		this.name = name;
		User.password = password;
		this.adminStatus = adminstatus;
		this.balance = balance;
	}

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public static String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		User.password = password;
	}


	public boolean isAdminStatus() {
		return adminStatus;
	}


	public void setAdminStatus(boolean adminStatus) {
		this.adminStatus = adminStatus;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (adminStatus ? 1231 : 1237);
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (adminStatus != other.adminStatus)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[name=" + name + ", adminStatus=" + adminStatus + ", balance=" + balance + "]";
	}


}
