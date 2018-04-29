package com.revature.jdbc;

import java.util.List;

import com.revature.factory.UserInterface;
import com.revature.trade.Trade;
import com.revature.user.User;

public interface UserDAO {
	
	User getUser(int id);
	User getUser(String username);
	List<User> getAllUsers();
	boolean insertUser(User user);
	boolean approveUser(int id);
	boolean denyUser(int id);
	boolean pendUser(int id);
	boolean lockUser(int id);
	boolean unlockUser(int id);
	boolean deposit(int id, int deposit);
	boolean withdraw(int id, int withdrawal);
	boolean deleteUser(int id);
	String getPasswordHash(UserInterface userInterface);
	boolean checkForAdmin();
	boolean promoteAdmin(int id);
	List<User> getPendingUsers();
	boolean generateTimestamp(int id);
	boolean makeTradeRequest(int idRequestor, int idAcceptor, int amount);
	List<Trade> getTradeRequest();
	boolean acceptTradeRequest(int idRequestor, int idAcceptor, int amount);
	boolean denyTradeRequest(int idRequestor, int idAcceptor, int amount);
	int getBalance(int id);
	int getTotalBalance();
}
