package com.revature.bank;

public class User extends Person{

	private float balance;
	boolean locked;
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		locked = true;
		approved = false;
		balance = 0;
	}
	float getBalance() {
		return balance;
	}
	
	void withdraw(float cash) {
		if(balance >= cash) {
			balance =  balance-cash;
			System.out.println("You have withdrawn $" + cash + " and have $" + balance + " remaining");
		}
		else System.out.println("You do not have enough in your account");
	}
	void deposit(float cash) {
		balance+=cash;
		System.out.println("You now have $" + balance + " in your account.");
	}
}
