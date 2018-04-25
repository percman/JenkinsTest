package com.revature.project0;

//adds to and scans the list of all users
public class FileIO {
//adds a new user to the list containing all users	
public static void addNewUser(User user){
	if(!Record.users.contains(user)&&!Record.admins.contains(new Admin(user.username,user.password))){
		Record.users.add(user);
		Record.passwords.put(user.username, user.password);
		Record.unApprovedUsers.add(user.username);
	}

}
//adds a new admin to the list containing all admins
public static void addAdmin(Admin admin){
	if(!Record.admins.contains(admin)&&!Record.admins.contains(new User(admin.username,admin.password))) {
		Record.admins.add(admin);
		Record.passwords.put(admin.username, admin.password);
	}

}
//looks through the list of users for those who are pending approval
public static void scanApproved() {
	for(User user : Record.users) {
		if(user.isUserUnapproved()) {
			System.out.println(user.username);
		}
	}
}
//looks through the list of users for those who are currently locked
public static void scanLocked() {
	for(User user : Record.users) {
		if(user.isUserLocked()) {
			System.out.println(user.username);
		}
	}
}
public static void scanUnlocked(){
	for(User user : Record.users) {
		if(!user.isUserLocked()) {
			System.out.println(user.username);
		}
	}
}
public static User getUser(String name) throws UserNotFoundException {
	for(User use : Record.users) {
		if(use.username.equals(name)) {
			return use;
		}	
	}
	throw new UserNotFoundException();
}
public static Admin getAdmin(String name) throws UserNotFoundException {
	for(Admin admin : Record.admins) {
		if(admin.username.equals(name)) {
			return admin;
		}	
	}
	throw new UserNotFoundException();
}
}
