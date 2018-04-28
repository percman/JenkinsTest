package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImpl;
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
	
	public static String login(User user) {
		User temp = dao.getUser(user.getUsername());
		
		if (temp.getPassword().equals(dao.getPasswordHash(user))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return user.getUsername();
		}
		System.err.println("YOU ARE NOT A VALID USER, " + user.getUsername());
		return "fail";
	}

}
