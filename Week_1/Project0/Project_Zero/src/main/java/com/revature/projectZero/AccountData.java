package com.revature.projectZero;

import java.util.HashMap;
import java.util.Map;

public class AccountData {
	
	/*
	 * 3 things we need for a Singleton:
	 * - private static field, matching the type of out class
	 * - private constructor
	 * - public static getInstance() method, which will either instantiate or return the existing object in memory
	 */
	
	private static AccountData instance;
	public static Map<String, Person> AccountData;
	
	private AccountData() {
		AccountData = new HashMap<>();
	}

	public static AccountData getInstance() {
		if (instance == null) {
			instance = new AccountData();
		}
		return instance;
	}
	
}
