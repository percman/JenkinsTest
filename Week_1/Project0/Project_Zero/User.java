package com.revature.zero.Project_Zero;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
	private final long serialVersionUID = -3703230509509286756L;
	private int balance;
	private boolean admin;
	private boolean locked;
	private String name;
	private boolean approved;
	
	public static void serializeUser(ArrayList<User> userList, File file) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(file));
			for (User u: userList) {
				out.write(u.getName() + " " + " was added \n");
			}
			out.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
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
	public void addBalance(int n) {
		this.balance += n;
	}
	public void subtractBalance(int n) {
		this.balance -= n;
	}

	public boolean isLocked() {
		return locked;
	}
	public String getLockedState() {
		if (locked) {
			return "locked";
		}
		else {
			return "not locked";
		}
	}
	public String changeLockedState() {
		if (locked) {
			return "unlock";
		} else {
			return "lock";
		}
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
	public boolean isApproved() {
		return approved;
	}
	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public boolean isAdmin() {
		return admin;
	}
	
}
