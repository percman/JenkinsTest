package com.revature.dao;

import java.util.List;

import com.revature.model.User;

public interface TransactionDao {

	List<String> transactions(User user);
}
