package com.revature.dao;

import com.revature.model.Person;
import com.revature.model.User;

public interface UserDao {

	boolean insertUser(User user);
	double getBalance(User user);
	boolean withdraw(User user, double cash);
	boolean deposit(User user, double cash);
	Person getPerson(String username);
	String getPassword(String username);
	String getPasswordHash(Person person);
	boolean isApproved(Person person);
	boolean isLocked(Person person);
}
