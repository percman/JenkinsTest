package com.revature.data;

import org.apache.log4j.Logger;

import com.revature.dao.users.UserService;
import com.revature.exceptions.UserNotFoundException;
import com.revature.project0.Script;
import com.revature.users.User;

//adds to and scans the list of all users
public class FileIO {
private static final Logger logger = Logger.getLogger(Script.class);
//looks through the list of users for those who are pending approval
public static void scanApproved() {
	
	for(User user : UserService.getUsers()) {
		try {
			if(UserService.isUserUnapproved(user)) {
				System.out.println(user.getUsername());
			}
		} catch (UserNotFoundException unfe) {
			logger.error(unfe.getMessage(), unfe);
		}
	}
}
//looks through the list of users for those who are currently locked
public static void scanLocked() {
	for(User user : UserService.getUsers()) {
		try {
			if(!UserService.isUserLocked(user)) {
				System.out.println(user.getUsername());
			}
		} catch (UserNotFoundException unfe) {
			logger.error(unfe.getMessage(), unfe);
		}
	}
}
public static void scanUnlocked(){
	for(User user : UserService.getUsers()) {
		try {
			if(UserService.isUserLocked(user)) {
				System.out.println(user.getUsername());
			}
		} catch (UserNotFoundException unfe) {
			logger.error(unfe.getMessage(), unfe);
		}
	}
}
}
