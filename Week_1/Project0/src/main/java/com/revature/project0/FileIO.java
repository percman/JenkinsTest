package com.revature.project0;

import java.util.*;
//adds to and scans the list of all users
public class FileIO {
//adds a new user to the list containing all users	
public static void addUser(User user){
	MovieBarn.users.add(user);
	MovieBarn.passwords.put(user.username, user.password);
}
//looks through the list of users for those who are pending approval
public static Set<User> scanApproved() {
	Set<User> unApproved = new HashSet<>();
	for(User user : MovieBarn.users) {
		if(!user.isUserApproved()) {
			unApproved.add(user);
		}
	}
	return unApproved;
}
//looks through the list of users for those who are currently locked
public static Set<User> scanLocked() {
	Set<User> Locked = new HashSet<>();
	for(User user : MovieBarn.users) {
		if(user.isUserLocked()) {
			Locked.add(user);
		}
	}
	return Locked;
}
public static Set<User> scanUnlocked(){
	Set<User> unLocked = new HashSet<>();
	for(User user : MovieBarn.users) {
		if(!user.isUserLocked()) {
			unLocked.add(user);
		}
	}
	return unLocked;
}
}
