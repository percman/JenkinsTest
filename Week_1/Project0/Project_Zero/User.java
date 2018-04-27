package com.revature.zero.Project_Zero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.zero.Project_Zero.ConnectionUtil;

public class User implements Serializable {
	private final long serialVersionUID = -3703230509509286756L;
	private int balance;
	private boolean admin;
	private boolean locked;
	private String name;
	private boolean approved;

	//Method which serializes the user
	public static void serializeUser(ArrayList<User> userList, File file) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(userList);
			out.close();
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	//The method which takes a file and turns it into an array list
	public static ArrayList<User> getUserList(File file) {
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
			ArrayList<User> userList = (ArrayList<User>) in.readObject();
			System.out.println("Current users:");
			for(User user: userList) {
				System.out.println(user.getName());
			}
			return userList;
		} catch (Exception e) {
		}
		ArrayList<User> userList = new ArrayList<> ();
		return userList;
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
