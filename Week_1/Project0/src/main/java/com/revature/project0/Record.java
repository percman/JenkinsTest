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
	public  static Set<String> lockedUsers = new HashSet<>();
	public  static Set<String> unApprovedUsers = new HashSet<>();
	
	public static void backup() {
		if(Record.users.size() > 0) {
			UserSerializer.serializeUser(Record.users, new File("src/main/resources/userData.txt"));
			Record.users.clear();
		}
		if(Record.admins.size() > 0) {
		AdminSerializer.serializeAdmin(Record.admins, new File("src/main/resources/adminData.txt"));
		Record.admins.clear();
		}
		if(Record.passwords.size() > 0) {
			PasswordSerializer.serializePasswords(Record.passwords, new File("src/main/resources/passData.txt"));
			Record.passwords.clear();
		}
	}
}
