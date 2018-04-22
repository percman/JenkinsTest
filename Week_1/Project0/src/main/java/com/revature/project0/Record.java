package com.revature.project0;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Record {
	public	static Map<String, String> passwords;
	public 	static Set<User> users;
	public  static Set<Admin> admins;
	public  static Set<String> lockedUsers;
	public  static Set<String> unApprovedUsers;
	
	public static void backup() {
		if(users.size() > 0) {
			UserSerializer.serializeUser(users, new File("src/main/resources/userData.txt"));
			users.clear();
		}
		if(admins.size() > 0) {
		AdminSerializer.serializeAdmin(admins, new File("src/main/resources/adminData.txt"));
		admins.clear();
		}
		if(passwords.size() > 0) {
			PasswordSerializer.serializePasswords(passwords, new File("src/main/resources/passData.txt"));
			passwords.clear();
		}
			lockedSerializer.serializeUser(lockedUsers, new File("src/main/resources/lockedData.txt"));
			lockedUsers.clear();
			lockedSerializer.serializeUser(unApprovedUsers, new File("src/main/resources/approvedData.txt"));
			unApprovedUsers.clear();
		
	}
	public static void loadData() {
		File userFile = new File("src/main/resources/userData.txt");
		File adminFile = new File("src/main/resources/adminData.txt");
		File passFile = new File("src/main/resources/passData.txt");
		File lockedFile = new File("src/main/resources/lockedData.txt");
		File approvedFile = new File("src/main/resources/approvedData.txt");
		if (userFile.length() != 0) {
			Record.users = UserSerializer.deSerializeUser(userFile);
		}else {
			Record.users = new HashSet<>();
		}
		if (adminFile.length() != 0) {
			Record.admins = AdminSerializer.deSerializeAdmin(adminFile);
			
		}else {
			Record.admins = new HashSet<>();
		}
		if (passFile.length() != 0) {
			Record.passwords = PasswordSerializer.deSerializePasswords(passFile);
		} else {
			Record.passwords = new HashMap<>();
		}
		if (lockedFile.length() != 0) {
			Record.lockedUsers = lockedSerializer.deSerializelockedUser(lockedFile);
		}else {
			Record.lockedUsers = new HashSet<>();
		}
		if (approvedFile.length() != 0) {
			Record.unApprovedUsers = lockedSerializer.deSerializelockedUser(approvedFile);
		}else {
			Record.unApprovedUsers = new HashSet<>();
		}
	}
}
