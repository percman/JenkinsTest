package com.revature.dao.users;

import java.util.ArrayList;

import com.revature.users.User;

public interface UserDao {

	public boolean addUser(User user);
	
	public boolean lockUser(User user);
	
	public boolean unlockUser(User user);
	
	public boolean approveUser(User user);
	
	public ArrayList<User> getUsers();

	public User getUser(String user);

	public boolean isUserLocked(User user);

	public boolean isUserUnapproved(User user);
}
