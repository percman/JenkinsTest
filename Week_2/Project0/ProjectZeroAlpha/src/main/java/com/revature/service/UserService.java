package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.logstatus.LogHere;
import com.revature.users.User;

public class UserService {

	private static UserDao dao = UserDaoImpl.getInstance();
	
	private UserService() {}
	
	public static User getUser(String username) {
		return dao.getUser(username);
	}
	
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}
	
	public static boolean insertAdmin(User user) {
		return dao.insertAdmin(user);
	}
	
	public static String login(User user) {
		User newuser = dao.getUser(user.getUsername());
		
		try {
			System.out.println("lock status");
			System.out.println(newuser.isLocked());
			
			if(newuser.getPassword().equals(dao.getPasswordHash(user))){
				if(newuser.isLocked()) {
					System.out.println("You are a valid user, " + newuser.getUsername());
					return "locked";
				} else {
					System.out.println("You are a valid user, " + newuser.getUsername());
					return "valid";
				}
			}
		} catch(NullPointerException npe) {
			LogHere.warn(npe.getMessage());
			System.out.println("The username or password combination is invalid.");
			return "invalid";
		}

		System.out.println("The username or password combination is invalid.");
		return "invalid";
	}



}
