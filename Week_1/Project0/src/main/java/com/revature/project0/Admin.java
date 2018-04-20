package com.revature.project0;

import java.io.Serializable;

public class Admin implements Serializable,NewUser {
	private static final long serialVersionUID = 2650766702984126758L;
	String username, password; // the admins username and password

	public Admin(String name, String pass) {
		username = name;
		password = pass;
	}

	// either locks or unlocks a user
	public void setLocked(boolean lock, User user) {
		if (user.isUserLocked() == lock) {
			System.out.print("User is already ");
			if(lock) {
				System.out.println(" locked.");
			}
			else {
				System.out.println(" unlocked.");
			}
		} else {
			if(lock) {
				Record.lockedUsers.add(user.username);
				System.out.print("User has been locked.");
			}
			else {
				Record.lockedUsers.remove(user.username);
				System.out.print("User is now unlocked.");
			}
		}
	}

	// approves a user
	public void approve(User user) {
		Record.unApprovedUsers.remove(user.username);
	}

}
