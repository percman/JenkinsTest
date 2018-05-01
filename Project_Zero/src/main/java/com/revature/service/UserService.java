package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.model.User;
import com.revature.application.*;

public class UserService {

	private static UserDao dao = UserDaoImpl.getInstance();
	
	private UserService() {}
	
	public static User getUser(String username) {
		return dao.getUser(username);
	}
	
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}
	
	public static User login(User user) {
		User temp = dao.getUser(user.getUsername());
		
		if (temp.getPassword().equals(dao.getPasswordHash(user))) {
			System.out.println("Valid user");
			return temp;
		}
		System.err.println("Incorrect login information. " + user.getUsername());
		return null;
	}
	
	
}
