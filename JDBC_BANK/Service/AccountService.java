package com.revature.service;

import java.util.List;

import com.revature.DAO.AccountDAO;
import com.revature.DAO.AccountDAOImpl;
import com.revature.model.Account;
import com.revature.model.User;

public class AccountService {
	private static AccountDAO dao = new AccountDAOImpl();
	
	
	public AccountService() {}
	
	public static boolean insertAccount(Account user) {
		return dao.insertAccount(user);
	}
	
	public static double getBalance(int id, User user) {
		return dao.getFunds(id, user);
	}
	
	public static List<User> getAccounts(User user){
		return dao.getAllAccounts(user);
	}
	public static boolean setFunds(int id, Double funds) {
		return dao.setFunds(id, funds);
	}
	
}
