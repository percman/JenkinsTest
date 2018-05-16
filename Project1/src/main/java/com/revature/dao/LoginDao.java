package com.revature.dao;

import com.revature.model.User;

public interface LoginDao {

	String getPassword(String username);
	String getPasswordHash(User user);
}
