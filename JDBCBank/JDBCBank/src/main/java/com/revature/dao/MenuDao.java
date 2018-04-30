package com.revature.dao;

public interface MenuDao {

	boolean usernameTaken(String newUsername);
	
	boolean insertUsername(String username, String type);
	
	int principalExists();
	
	String getType(String username);
	
	
}
