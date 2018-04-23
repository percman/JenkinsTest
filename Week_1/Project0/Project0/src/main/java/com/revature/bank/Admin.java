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

	}
	public static Map<String, Admin> adminMap = new HashMap<>();
	
	public static void addAdminToMap(String username, Admin admin) {
		adminMap.put(username, admin);
	}
	
	public void approveUser(User user) {
		user.approved = true;
	}
	
	public void approveAdmin(Admin admin) {
		admin.approved = true;
	}
	
	public void lockUser(User user) {
		user.locked = true;
	}
	
	public void unlockUser(User user) {
		user.locked = false;
	}


	
}
