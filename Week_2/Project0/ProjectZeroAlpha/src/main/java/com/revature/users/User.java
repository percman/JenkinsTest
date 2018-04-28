package com.revature.users;

import java.io.Serializable;

public class User implements Serializable, Person{


	private static final long serialVersionUID = 9099886195893877291L;
	
	private int id;
	private String username; 
	private String password;
	private boolean locked;
	private double balance;
	
	
	
	
	public User(String username, String password, double balance) {
		super();
		this.username = username;
		this.password = password;
		this.balance = balance;
	}


	public User(int id, String username, String password, boolean locked, double balance) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.locked = locked;
		this.balance = balance;
	}

	
	public User() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		result = prime * result + (locked ? 1231 : 1237);
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (id != other.id)
			return false;
		if (locked != other.locked)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
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
		return "User [id=" + id + ", name=" + username + ", password=" + password + ", locked=" + locked + ", balance="
				+ balance + "]";
	}

	@Override
	public double generateInterest() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	

}
