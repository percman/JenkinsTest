package com.revature.bank;

import java.util.*;

public class Admin extends Person{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6065239180076826871L;

	public Admin(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		approved = false;
		loggedIn = true;
	}
	public static Map<String, Admin> adminMap = new HashMap<>();
	
	public static void addAdminToMap(String username, Admin admin) {
		adminMap.put(username, admin);
	}
	
	void approveUser(User user) {
		user.approved = true;
	}
	
	void approveAdmin(Admin admin) {
		admin.approved = true;
	}
	
	void lockUser(User user) {
		user.locked = true;
	}
	
	void unlockUser(User user) {
		user.locked = false;
	}


	
}
