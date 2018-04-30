package com.revature.service;


import com.revature.model.Customer;

import java.util.List;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;


public class CustomerService {

	private static CustomerDao dao = CustomerDaoImpl.getInstance();

	private CustomerService() {}

	public static List<Customer> getAllCustomers(){
		return dao.getAllCustomers();
	}
	
	public static Customer getCustomer(String username) {
		return dao.getCustomer(username);
	}
	
	public static boolean insertCustomer(Customer customer) {
		return dao.insertCustomer(customer);
	}
	
	public static boolean updateCustomer(Customer customer) {
		return dao.updateCustomer(customer);
	}
	
	public static Customer login(Customer customer) {
		Customer temp = dao.getCustomer(customer.getUsername());
		
		if (temp.getPassword().equals(dao.getPasswordHash(customer))) {
			System.out.println("You are a valid user, " + temp.getUsername());
			return temp;
		}
		System.err.println("YOU ARE NOT A VALID USER, " + customer.getUsername());
		return null;
	}
}
