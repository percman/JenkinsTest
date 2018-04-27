package com.revature.zero.Project_Zero;

import java.util.ArrayList;

public class UserService {

	private static UserDao dao = new UserDaolmplementation();
	
	private UserService() {}
	
	public static User getUser(String name) {
		return dao.getUser(name);
	}
	public static ArrayList<User> getAllUsers() {
		return dao.getAllUsers();
	}
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}
	public static boolean updateUser(User user) {
		return dao.updateUser(user);
	}
	public static boolean deleteUser(String name) {
		return dao.deleteUser(name);
	}
}
