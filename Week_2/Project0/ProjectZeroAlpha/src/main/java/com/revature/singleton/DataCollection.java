package com.revature.singleton;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

import com.revature.users.User;

public class DataCollection implements Serializable{

	private static final long serialVersionUID = -3855901752095132148L;

	private static DataCollection instance;

	private HashMap<String, User> userHashMap;

	private DataCollection() {
		userHashMap = new HashMap<>();
	}

	public static DataCollection getInstance() {
		if (instance == null) {
			instance = new DataCollection();
		}
		return instance;
	}

	public HashMap<String, User> getHashMap() {
		return userHashMap;
	}

	public User put(String username, User user) {
		return userHashMap.put(username, user);
	}

	public boolean isEmpty() {
		return userHashMap.isEmpty();
	}

	public void setHashMap(HashMap<String, User> hashMapData) {
		userHashMap = hashMapData;
	}
	
	public Collection<User> values() {
		return userHashMap.values();
	}
	
	public Set<String> keySet() {
		return userHashMap.keySet();
	}

	public boolean containsKey(String key) {
		return userHashMap.containsKey(key);
	}

	public User get(String key) {
		return userHashMap.get(key);
	}

	public int size() {
		return userHashMap.size();
	}
}
