package com.revature.project0;

import java.util.*;
//adds to and scans the list of all users
public class FileIO {
//adds a new user to the list containing all users	
public static void addNewUser(User user){
	if(!Record.users.contains(user)) {
		Record.users.add(user);
		Record.passwords.put(user.username, user.password);
	}

}
//adds a new admin to the list containing all admins
public static void addAdmin(Admin admin){
	if(!Record.admins.contains(admin)) {
		Record.admins.add(admin);
		Record.passwords.put(admin.username, admin.password);
	}

}
//looks through the list of users for those who are pending approval
public static Set<User> scanApproved() {
	Set<User> unApproved = new HashSet<>();
	for(User user : Record.users) {
		if(!user.isUserApproved()) {
			unApproved.add(user);
		}
	}
	return unApproved;
}
//looks through the list of users for those who are currently locked
public static Set<User> scanLocked() {
	Set<User> Locked = new HashSet<>();
	for(User user : Record.users) {
		if(user.isUserLocked()) {
			Locked.add(user);
		}
	}
	return Locked;
}
public static Set<User> scanUnlocked(){
	Set<User> unLocked = new HashSet<>();
	for(User user : Record.users) {
		if(!user.isUserLocked()) {
			unLocked.add(user);
		}
	}
	return unLocked;
}
public static User getUser(String name) {
	for(User use : Record.users) {
		if(use.username.equals(name)) {
			return use;
		}	
	}
	return null;
}
}
