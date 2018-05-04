package com.bank.service;

import java.util.List;

import com.bank.dao.UserDao;
import com.bank.dao.UserDaoImpl;
import com.bank.model.Product;
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
	
	public static int getUserId(User user) {
		return dao.getUserId(user);
	}
	
	public static int getApproved(User user) {
		return dao.getApproved(user);
	}
	public static int getLocked(User user) {
		return dao.getLocked(user);
	}
	public static boolean approveAccount(User user) {
		return dao.approveAccount(user);
	}
	public static boolean lockAccount(User user) {
		return dao.lockAccount(user);
	}
	public static boolean unlockAccount(User user) {
		return dao.unlockAccount(user);
	}
	
	
}