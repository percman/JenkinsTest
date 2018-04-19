package com.revature.zero.Project_Zero;

import java.io.Serializable;

public class User implements Serializable {
	private final long serialVersionUID = -3703230509509286756L;
	private int balance;
	private boolean admin;
	private boolean locked;
	private String name;
	private boolean approved;
	
	public User() {}
	public User(String name, int balance, boolean admin, boolean locked, boolean approved) {
		super();
		this.name = name;
		this.balance = balance;
		this.admin = admin;
		this.locked = locked;
		this.approved = approved;
	}
	public int getBalance() {
		return balance;
	}
	
	public String getName() {
		return name;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isApproved() {
		return approved;
	}

	public boolean isAdmin() {
		return admin;
	}
	
}
