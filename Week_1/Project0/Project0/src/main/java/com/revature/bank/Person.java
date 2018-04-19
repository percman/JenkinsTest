package com.revature.bank;

import java.util.*;

public class Person {
	String username;
	String password;
	boolean loggedIn;
	boolean approved;

	public static Map<String, Person> userMap = new HashMap<>();
	
	public static void addUserToMap(String username, Person person) {
		Person.userMap.put(username, person);
	}
}
