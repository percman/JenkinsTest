package com.revature.dao;

import java.util.List;

import com.revature.model.Customer;

public interface CustomerDao {

	public abstract List<Customer> getAllCustomers();
	public abstract Customer getCustomer(String username);
	public abstract boolean insertCustomer(Customer customer);
	public abstract boolean updateCustomer(Customer customer);
	public abstract String getPasswordHash(Customer customer);
}
