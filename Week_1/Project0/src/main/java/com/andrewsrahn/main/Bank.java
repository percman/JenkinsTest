package com.andrewsrahn.main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class Bank implements Serializable{
	private static final long serialVersionUID = 2935074951441032623L;
	private Map<String, Administrator> administrators;
	private Map<String, User> users;

	public Bank(Logger logger) {
		try(BufferedReader br = new BufferedReader(
									new FileReader(
											new File("src/main/resources/initial.txt")))) {
			String initialized = br.readLine();
			
			if(initialized.equals("false"))
				initialize(br);
			else {
				administrators = Serialize.deserializeAdministrators(logger);
				users = Serialize.deserializeUsers(logger);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public User tokenizeUser(String string) {
		StringTokenizer s = new StringTokenizer(string, ":");
		s.nextToken(); // String type
		String name = s.nextToken();
		String password = s.nextToken();
		int balance = Integer.parseInt(s.nextToken());
		boolean locked = s.nextToken().equals("locked") ? true : false;
		return new User(name, password, balance, locked);
	}

	public Administrator tokenizeAdministrator(String string) {
		StringTokenizer s = new StringTokenizer(string, ":");
		s.nextToken(); // String type
		String name = s.nextToken();
		String password = s.nextToken();
		return new Administrator(name, password);
	}

	public void initialize(BufferedReader br) {
		String administrator = null, user0 = null, user1 = null, user2 = null;
		try {
			administrator = br.readLine();
			user0 = br.readLine();
			user1 = br.readLine();
			user2 = br.readLine();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Administrator andrew = tokenizeAdministrator(administrator);
		User andy = tokenizeUser(user0);
		User cameron = tokenizeUser(user1);
		User vince = tokenizeUser(user2);
		
		andy.setRejectedBy(andrew);
		vince.setApprovedBy(andrew);
		
		administrators = new HashMap<String, Administrator>();
		users = new HashMap<String, User>();
		
		administrators.put("andrew", andrew);
		users.put("andy", andy);
		users.put("cameron", cameron);
		users.put("vince", vince);
		
		try(BufferedWriter w = new BufferedWriter(
									new FileWriter(
										new File("src/main/resources/initial.txt")))){
			w.write("true\n");
			w.write("administrator:andrew:password\n");
			w.write("user:andy:password:0:locked\n");
			w.write("user:cameron:password:20:unlocked\n");
			w.write("user:vince:password:40:unlocked");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Administrator getAdministrator(String name) {
		return administrators.get(name);
	}
	
	public User getUser(String name) {
		return users.get(name);
	}

	public String authenticateUser(String name, String password) {
		for(Map.Entry<String, User> entry: users.entrySet()) {
			User user = entry.getValue();
			if(user.getName().equals(name))
				if(user.getPassword().equals(password))
					if(user.getRejectedBy() == null && user.getApprovedBy() == null)
						return "pending";
					else if(user.getRejectedBy() != null)
						return "rejected";
					else if(user.getApprovedBy() != null)
						return "approved";
				else
					return "incorrect password";
		}
		return "not found";
	}
	
	public String authenticateAdministrator(String name, String password) {
		for(Map.Entry<String, Administrator> entry: administrators.entrySet()) {
			Administrator user = entry.getValue();
			if(user.getName().equals(name))
				if(user.getPassword().equals(password))
					return "authenticate";
				else
					return "incorrect password";
		}
		return "administrator not found";
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

	public Map<String, Administrator> getAdministrators() {
		return administrators;
	}

	public void serialize(Logger logger) {
		Serialize.serializeUsers(users, logger);
		Serialize.serializeAdministrators(administrators, logger);
	}
}