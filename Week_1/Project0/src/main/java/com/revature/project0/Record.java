package com.revature.project0;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Record {
	public	static Map<String, String> passwords = new HashMap<>();
	public 	static Set<User> users;
	public  static Set<Admin> admins;
	public  static Set<String> lockedUsers = new HashSet<>();
	public  static Set<String> unApprovedUsers = new HashSet<>();
}
