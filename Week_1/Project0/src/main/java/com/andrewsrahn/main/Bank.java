package com.andrewsrahn.main;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;

public class Bank implements Serializable{
	private static final long serialVersionUID = 2396854967873985409L;
	private Map<String, Administrator> administrators;
	private Map<String, User> users;

	public Bank() {
		administrators = new HashMap<>();
		administrators.put("andrew", new Administrator("andrew", "andrewsrahn@gmail.com", "password"));
		users = new HashMap<>();
		users.put("andy", new User("andy", "ahnman341@gmail.com", "password"));
	}
	
	public Administrator getAdministrator(String name) {
		return administrators.get(name);
	}
	
	public User getUser(String name) {
		return users.get(name);
	}

	public void createAdmin(Administrator administrator) {
		administrators.put(administrator.getName(), administrator);	
	}

	public void createUser(User user) {
		users.put(user.getName(), user);		
	}

	public Map<String, User> getUsers() {
		return users;
	}
}