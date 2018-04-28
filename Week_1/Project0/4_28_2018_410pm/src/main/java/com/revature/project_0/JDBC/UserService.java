package com.revature.project_0.JDBC;

import java.util.List;

import com.revature.project_0.Trade;
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
	
	public static List<User> getPendingUsers(){
		return dao.getPendingUsers();
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
			System.out.println("Login successful");
			return true;
		}
		System.err.println("Invalid credentials");
		return false;
	}
	
	public static boolean checkForAdmin() {
		return dao.checkForAdmin();
	}
	
	public static boolean promoteAdmin(int id) {
		return dao.promoteAdmin(id);
	}
	
	public static boolean pendUser(int id) {
		return dao.pendUser(id);
	}
	
	public static boolean generateTimestamp(int id) {
		return dao.generateTimestamp(id);
	}
	
	public static boolean makeTradeRequest(int idRequestor, int idAcceptor, int amount) {
		return dao.makeTradeRequest(idRequestor, idAcceptor, amount);
	}
	
	public static List<Trade> getTradeRequest() {
		return dao.getTradeRequest();
	}
	
	public static boolean acceptTradeRequest(int idRequestor, int idAcceptor, int amount) {
		return dao.acceptTradeRequest(idRequestor, idAcceptor, amount);
	}
}
