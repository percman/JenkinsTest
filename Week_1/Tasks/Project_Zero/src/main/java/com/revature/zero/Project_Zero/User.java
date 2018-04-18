package com.revature.zero.Project_Zero;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -3703230509509286756L;
	private static int balance;
	private static boolean admin;
	private static boolean locked;
	private static String name;
	private static boolean approved;
	public User() {}
	public User(String name, int balance, boolean admin, boolean locked, boolean approved) {
		super();
		this.name = name;
		this.balance = balance;
		this.admin = admin;
		this.locked = locked;
		this.approved = approved;
	}
	public static int getBalance() {
		return balance;
	}
	
	public static String getName() {
		return name;
	}

	public static void setBalance(int balance) {
		User.balance = balance;
	}

	public static boolean isLocked() {
		return locked;
	}

	public static void setLocked(boolean locked) {
		User.locked = locked;
	}
	public static boolean isApproved() {
		return approved;
	}

	public static boolean isAdmin() {
		return admin;
	}
	
}
