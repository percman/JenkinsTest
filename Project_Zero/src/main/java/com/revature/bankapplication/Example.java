package com.revature.bankapplication;

import java.util.HashMap;

public class Example {

	public static void main (String[] args) {
	String username = "david";
	String password = "password";
	HashMap<String, String> map = new HashMap<String, String>();
	map.put(username, password);
	LoginInfo info = new LoginInfo(map);
	
	}
}
