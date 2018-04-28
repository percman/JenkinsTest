package com.revature.dao.users;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.UserNotFoundException;
import com.revature.users.User;

public interface UserDao {

	public boolean addUser(User user);
	
	public boolean lockUser(User user) throws UserNotFoundException;
	
	public boolean unlockUser(User user) throws UserNotFoundException;
	
	public boolean approveUser(User user) throws UserNotFoundException;
	
	public List<User> getUsers();

	public User getUser(String user) throws UserNotFoundException;

	public boolean isUserLocked(User user) throws UserNotFoundException;

	public boolean isUserUnapproved(User user) throws UserNotFoundException;
}
