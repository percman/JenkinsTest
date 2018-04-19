package com.revature.bank;

public class Admin extends Person{
	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		approved = false;
		loggedIn = true;
	}
	
	void approveUser(User user) {
		user.approved = true;
	}
	
	void lockUser(User user) {
		user.locked = true;
	}
	
	void unlockUser(User user) {
		user.locked = false;
	}
	
}
