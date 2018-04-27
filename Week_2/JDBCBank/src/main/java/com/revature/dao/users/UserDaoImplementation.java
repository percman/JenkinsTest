package com.revature.dao.users;

import java.util.ArrayList;

import com.revature.users.User;

public class UserDaoImplementation implements UserDao {

	@Override
	public boolean addUser(User user) {
		// TODO add User to database
		return false;
	}

	@Override
	public boolean lockUser(User user) {
		// TODO locks a user
		return false;
	}

	@Override
	public boolean unlockUser(User user) {
		// TODO unlocks a user
		return false;
	}

	@Override
	public boolean approveUser(User user) {
		// TODO approves a user
		return false;
	}

	@Override
	public ArrayList<User> getUsers() {
		// TODO return an list of all the users in the table 
		return null;
	}

	@Override
	public User getUser(String user) {
		// TODO returns user with the given username;
		return null;
	}

	@Override
	public boolean isUserLocked(User user) {
		// TODO returns if the given user is locked
		return false;
	}

	@Override
	public boolean isUserUnapproved(User user) {
		// TODO returns if the given user is unapproved
		return false;
	}
	
	

}
