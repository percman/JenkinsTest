package com.revature.service;

import java.util.List;

import com.revature.DAO.AccountAccessDAO;
import com.revature.DAO.AccountAccessDAOImpl;
import com.revature.model.Account;
import com.revature.model.User;

public class AccountAccessService {
	private static AccountAccessDAO dao = new AccountAccessDAOImpl();
	
	
	public AccountAccessService() {}
	
	public static boolean isPending(Account user) {
		return dao.isPending(user);
	}
	
	public static boolean insertAccountAccess(Account user) {
		return dao.insertAccountAccess(user);
	}
	
	public static List<User> getPendingUsers(){
		return dao.getPending();
	}
	public static List<User> getLockedUsers(){
		return dao.getLockedUsers();
	}
	
	public static List<User> getUnlockedUsers(){
		return dao.getUnlockedUsers();
	}
	
	public static boolean isAdmin(Account user) {
		return dao.getPermission(user);
	}
	public static boolean isLocked(Account user) {
		return dao.isLocked(user);
	}
	public static boolean Approve(User user) {
		return dao.ApproveUser(user);
	}
	public static boolean Lock(User user) {
		return dao.LockUser(user);
	}
	public static boolean Unlock(User user) {
		return dao.UnLockUser(user);
	}
}
