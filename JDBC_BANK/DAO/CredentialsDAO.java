package com.revature.DAO;

import com.revature.model.Account;

public interface CredentialsDAO {
	boolean CheckAvailablility(String username);
	Account getCredentials(String username);
	boolean insertCredentials(Account user);
	boolean deleteCredentials(String username);
	String getPasswordHash(Account user);
}
