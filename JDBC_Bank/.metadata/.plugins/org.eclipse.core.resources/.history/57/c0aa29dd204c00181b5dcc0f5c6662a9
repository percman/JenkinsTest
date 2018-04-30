package com.bank.service;

import java.util.List;

import com.bank.dao.UserDao;
import com.bank.dao.UserDaoImpl;
import com.bank.model.User;

public class UserService {

private static UserDao dao = UserDaoImpl.getInstance();
	
	private UserService() {}
	
	public static User getUser(String firstname, String lastname) {
		return dao.getUser(firstname,lastname);
	}
	
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}
	
	public static List<User> getUsers() {
		return dao.getAllUsers();
	}
	
	public static User login(User user) {
		User temp = dao.getUser(user.getFirst_name(), user.getLast_name());
		
		if (temp.getPassword().equals(dao.getPasswordHash(user))) {
			System.out.println("You are a valid user, " + temp.getFirst_name());
			return temp;
		}
		System.err.println("YOU ARE NOT A VALID USER, " + user.getFirst_name());
		return null;
	}
	
	
}