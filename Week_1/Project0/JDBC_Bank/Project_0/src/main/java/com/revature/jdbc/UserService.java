package com.revature.jdbc;

import java.util.List;

import com.revature.factory.UserInterface;
import com.revature.trade.Trade;
import com.revature.user.User;

import Exceptions.UsernameDoesNotExistException;

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
	
	public static boolean login(UserInterface UserInterface) {
		User temp = dao.getUser(UserInterface.getUsername());
		try {
			if (temp.getUsername() == null) {
				throw new UsernameDoesNotExistException();
			}	
			else if (temp.getPassword().equals(dao.getPasswordHash(UserInterface))) {
				System.out.println("Login successful");
				return true;
			}
			System.out.println("\n\n\t\t***INVALID PASSWORD***\n");
			return false;
		} catch (UsernameDoesNotExistException udnee) {
			return false;
		}
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
	
	public static boolean denyTradeRequest(int idRequestor, int idAcceptor, int amount) {
		return dao.denyTradeRequest(idRequestor, idAcceptor, amount);
	}
	
	public static int getBalance(int id) {
		return dao.getBalance(id);
	}
	
	public static int getTotalBalance() {
		return dao.getTotalBalance();
	}
}
