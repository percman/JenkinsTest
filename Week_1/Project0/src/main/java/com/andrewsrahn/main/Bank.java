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
		users = new HashMap<>();
	}
	
	public void initialize() {
		Administrator andrew = new Administrator("andrew", "andrewsrahn@gmail.com", "password");
		User andy = new User("andy", "ahnman341@gmail.com", "password");
		User cameron = new User("cameron", "cameron@gmail.com", "password");
		User vince = new User("vince", "vince@gmail.com", "password");
		
		andy.setRejectedBy(andrew);
		vince.setApprovedBy(andrew);
		
		administrators.put("andrew", andrew);
		users.put("andy", andy);
		users.put("cameron", cameron);
		users.put("vince", vince);
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

	public void setUser(User u) {
		users.put(u.getName(), u);
	}
}