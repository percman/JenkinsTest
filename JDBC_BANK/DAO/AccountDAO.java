package com.revature.DAO;

import java.util.List;

import com.revature.model.Account;
import com.revature.model.User;

public interface AccountDAO {

	Double getFunds(int accountID, User user);
	boolean setFunds(int accountID, Double funds);
	List<User> getAllAccounts(User user);
	boolean insertAccount(Account user);
	}
