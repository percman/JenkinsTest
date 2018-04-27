package com.revature.data;

import com.revature.dao.users.AdminService;
import com.revature.dao.users.UserService;
import com.revature.exceptions.UserNotFoundException;
import com.revature.users.Admin;
import com.revature.users.User;

//adds to and scans the list of all users
public class FileIO {
//looks through the list of users for those who are pending approval
public static void scanApproved() {
	for(User user : UserService.getUsers()) {
		if(UserService.isUserUnapproved(user)) {
			System.out.println(user.getUsername());
		}
	}
}
//looks through the list of users for those who are currently locked
public static void scanLocked() {
	for(User user : UserService.getUsers()) {
		if(UserService.isUserLocked(user)) {
			System.out.println(user.getUsername());
		}
	}
}
public static void scanUnlocked(){
	for(User user : UserService.getUsers()) {
		if(!UserService.isUserLocked(user)) {
			System.out.println(user.getUsername());
		}
	}
}
}
