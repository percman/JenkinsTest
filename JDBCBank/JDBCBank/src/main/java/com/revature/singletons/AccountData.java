package com.revature.singletons;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.revature.users.Person;

public class AccountData implements Serializable {

	private static final long serialVersionUID = 5022202839677821505L;

	/*
	 * 3 things we need for a Singleton: - private static field, matching the type
	 * of out class - private constructor - public static getInstance() method,
	 * which will either instantiate or return the existing object in memory
	 */

	private static AccountData instance;

	private Map<String, Person> accountData;

	private AccountData() {
		accountData = new HashMap<>();
	}

	public static AccountData getInstance() {
		if (instance == null) {
			instance = new AccountData();
		}
		return instance;
	}

	public Map<String, Person> getHashMap() {
		return accountData;
	}

	public Person put(String loginInfo, Person person) {
		return accountData.put(loginInfo, person);
	}

	public boolean isEmpty() {
		return accountData.isEmpty();
	}

	public void setHashMap(Map<String, Person> hashMap) {
		accountData = hashMap;

	}

	public Set<String> keySet() {
		return accountData.keySet();
	}

	public Collection<Person> values() {
		return accountData.values();
	}

	public boolean containsKey(String key) {
		return accountData.containsKey(key);
	}

	public Person get(String key) {
		return accountData.get(key);
	}

	public int size() {
		return accountData.size();
	}

}
