package com.revature.dao;

import java.util.List;


import com.revature.model.User;

public interface UserDao {

	public abstract List<User> getAllUsers();
	public abstract User getUser(String username);
	public abstract boolean insertUser(User user);
	public abstract boolean updateUser(User user);
	public abstract String getPasswordHash(User user);
	
	
}
