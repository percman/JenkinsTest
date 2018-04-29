package com.revature.util;

import com.revature.model.Admin;
import com.revature.model.Person;
import com.revature.model.User;

public class Factory {

	public static Person getPerson(String role, String username, String password, double balance,
			String approved, String locked) {
		boolean approval;
		if(approved.equals("T")) {
			approval = true;
		}
		else approval = false;
		if(role == "Admin") return new Admin(username, password, approval);
		else {
			boolean lock;
			if (locked.equals("T")) lock = true;
			else lock = false;
			return new User(username, password, approval, balance, lock);
		}
	}
	
}
