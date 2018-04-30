package com.revature.factory;

public interface UserInterface {

	void promoteAdmin();
	void demoteAdmin();
	boolean getAdmin();
	void lock();
	void unlock();
	boolean isLocked();
	void approve();
	void setPending();
	void deny();
	int isApproved();
	void setFirstName(String firstName);
	void setLastName(String lastName);
	String getFirstName();
	String getLastName();
	void setUsername(String userName);
	String getUsername();
	void setPassword(String password);
	String getPassword();
	void setAccountBalance(double accountBalance);
	String getAccountBalance();
	void setAccountNumber(int accountNumber);
	int getAccountNumber();
	
}
