package com.revature.dao;

import com.revature.model.Customer;

public interface CustomerDao {

	Customer getCustomer(String username);
	boolean insertCustomer(Customer customer);
	String getPasswordHash(Customer customer);
	boolean changeLock(int input_id);
	void depositCustomer(Customer customer, int amount);
	void withdrawCustomer(Customer customer, int amount);
}
