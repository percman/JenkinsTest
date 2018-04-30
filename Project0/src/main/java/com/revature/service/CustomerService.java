package com.revature.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.dao.CustomerDao;
import com.revature.dao.CustomerDaoImpl;
import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerService {

	private static CustomerDao dao = CustomerDaoImpl.getInstance();
	
	private CustomerService() {}
	
	public static Customer getCustomer(String username) {
		return dao.getCustomer(username);
	}

	public static boolean insertCustomer(Customer customer) {
		return dao.insertCustomer(customer);
	}
	
	public static boolean login(Customer customer) {
		Customer temp = dao.getCustomer(customer.getUsername());
		
		if(temp.getPassword().equals(dao.getPasswordHash(customer))) {
			return true;
		}
			return false;
	}
	
	public static boolean lockCheck(Customer customer) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE username = ?");
			stmt.setString(++index, customer.getUsername());
			ResultSet rs = stmt.executeQuery();
			if (rs.getInt("isLocked") == 0) {
				return true;
			}
				return false;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
}
