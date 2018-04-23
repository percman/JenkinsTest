package com.revature.bank;

import java.util.HashMap;
import java.util.Map;

public class User extends Person{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2431287459106300670L;
	private double balance;
	public boolean locked;
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		locked = false;
		approved = false;
		balance = 0;
	}

	public static Map<String, User> userMap = new HashMap<>();
		
	public static void addUserToMap(String username, User user) {
		userMap.put(username, user);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void withdraw(double cash) {
		if(cash<=0) System.out.println("You must enter a positive number.");
		else if(balance >= cash) {
			balance =  balance-cash;
			System.out.println("You have withdrawn $" + cash + " and have $" + balance + " remaining");
		}
		else System.out.println("You do not have enough in your account");
	}
	public void deposit(double cash) {
		if(cash<=0) System.out.println("You must enter a positive number.");
		else {
			balance+=cash;
			System.out.println("You now have $" + balance + " in your account.");
		}
	}
	
}
