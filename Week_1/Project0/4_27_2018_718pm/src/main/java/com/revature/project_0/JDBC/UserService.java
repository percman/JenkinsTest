package com.revature.project_0.JDBC;

import java.util.List;

import com.revature.project_0.User;

public class UserService {

	private static UserDAO dao = new UserDAOImplementation();
	
	private UserService() {}
	
	public static User getUser(int id) {
		return dao.getUser(id);
	}
	
	public static User getUser(String username) {
		return dao.getUser(username);
	}
	
	public static List<User> getAllUsers(){
		return dao.getAllUsers();
	}
	
	public static boolean insertUser(User user) {
		return dao.insertUser(user);
	}
	
	public static boolean approveUser(int id) {
		return dao.approveUser(id);
	}
	
	public static boolean denyUser(int id) {
		return dao.denyUser(id);
	}
	
	public static boolean lockUser(int id) {
		return dao.lockUser(id);
	}
	
	public static boolean unlockUser(int id) {
		return dao.unlockUser(id);
	}
	
	public static boolean deposit(int id, int deposit) {
		return dao.deposit(id, deposit);
	}
	
	public static boolean withdraw(int id, int withdrawal) {
		return dao.withdraw(id, withdrawal);
	}
	
	public static boolean deleteUser(int id) {
		return dao.deleteUser(id);
	}
	
	public static boolean login(User user) {
		User temp = dao.getUser(user.getUsername());
		
		if (temp.getPassword().equals(dao.getPasswordHash(user))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return true;
		}
		System.err.println("YOU ARE NOT A VALID USER, " + user.getUsername());
		return false;
	}
	
	public static boolean checkForAdmin() {
		return dao.checkForAdmin();
	}
	
	public static boolean promoteAdmin(int id) {
		return dao.promoteAdmin(id);
	}
}
