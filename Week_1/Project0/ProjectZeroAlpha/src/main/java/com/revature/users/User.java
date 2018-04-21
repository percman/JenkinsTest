package com.revature.users;

import java.io.Serializable;

public class User implements Serializable{


	private static final long serialVersionUID = 9099886195893877291L;
	
	private String name; 
	private String password;
	private boolean adminStatus;
	private boolean locked;
	private double balance;
	
	public User() {}
	
	
	public User(String name, String password, boolean adminStatus, boolean locked, double balance) {
		super();
		this.name = name;
		this.password = password;
		this.adminStatus = adminStatus;
		this.locked = locked;
		this.balance = balance;
	}


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

	public boolean isAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(boolean adminStatus) {
		this.adminStatus = adminStatus;
	}
	

	public boolean isLocked() {
		return locked;
	}
	

	public void setLocked(boolean locked) {
		this.locked = locked;
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
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		if (locked != other.locked)
			return false;
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
		return true;
	}


	@Override
	public String toString() {
		return "[name=" + name + ", adminStatus=" + adminStatus + ", balance=" + balance
				+ "]";
	}


}
