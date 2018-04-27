package com.revature.dao.users;

import java.util.ArrayList;

import com.revature.exceptions.UserNotFoundException;
import com.revature.users.User;

public class UserService {
	private UserDao dao = new UserDaoImplementation();
	private static UserService instance;
	private UserService(){};
	public static UserService getInstance() {
		if(instance == null) {
			instance = new UserService();
		}
		return instance;
	}
	public boolean addUser(User user) {
		return dao.addUser(user);
	}
	
	public boolean lockUser(User user) {
		return dao.lockUser(user);
	}
	
	public boolean unlockUser(User user) {
		return dao.unlockUser(user);
	}
	
	public boolean approveUser(User user) {
		return dao.approveUser(user);
	}
	public ArrayList<User> getUsers(){
		return dao.getUsers();
	}
	public boolean isUserUnapproved(User user) {
		return dao.isUserUnapproved(user);
	}
	public boolean isUserLocked(User user) {
		return dao.isUserLocked(user);
	}
	public User getUser(String user) throws UserNotFoundException{
		return dao.getUser(user);
	}
}
