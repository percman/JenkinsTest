package com.revature.service;


import java.sql.Timestamp;
import java.util.List;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.logstatus.LogHere;
import com.revature.users.User;

public class UserService {

	private static UserDao dao = UserDaoImpl.getInstance();
	
	private UserService() {}
	
	public static List<User> getAllUsers() {
		return dao.getAllUsers();
	}
	
	public static User getAnyUser() {
		return dao.getAnyUser();
	}
	
	public static User getUser(String username) {
		return dao.getUser(username);
	}
	
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}
	
	public static boolean updateUser(User user) {
		return dao.updateUser(user);
	}
	
	public static boolean insertAdmin(User user) {
		return dao.insertAdmin(user);
	}
	
	public static User login(User user) {
		User newuser = dao.getUser(user.getUsername());
		
		try {		
			
			if(newuser.getPassword().equals(dao.getPasswordHash(user))){
				System.out.println("You are a valid user, " + newuser.getUsername());
				return newuser;
			}
		} catch(NullPointerException npe) {
			LogHere.warn(npe.getMessage());
			System.out.println("The username or password combination is invalid.");
			return null;
		}

		System.out.println("The username or password combination is invalid.");
		return null;
	}
	
	public static Timestamp getUserTime(User user) {
		return dao.getUserTime(user);
	}
	
	public static boolean generateUserInterest(User user) {
		if(dao.generateUserInterest(user))
			return true;
		return false;
		
	}



}
