package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.apache.log4j.Logger;

import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDao{

	private static final Logger logger = Logger.getLogger(CustomerDaoImpl.class);
	private static CustomerDaoImpl instance;
	
	private CustomerDaoImpl() {}
	
	public static CustomerDaoImpl getInstance() {
		if (instance == null) {
			instance = new CustomerDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public List<Customer> getAllCustomers() {
		
		List<Customer> customers = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Customer customer = new Customer(rs.getString("username"), rs.getString("password"), rs.getInt("approvalcode"), rs.getInt("lockcode"),
						rs.getInt("rejected"), rs.getBigDecimal("accountbalance"));
				customers.add(customer);
				
			}
			rs.close();
			return customers;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public Customer getCustomer(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Customer(rs.getString("username"), rs.getString("password"), rs.getInt("approvalcode"), rs.getInt("lockcode"), 
						rs.getInt("rejected"), rs.getBigDecimal("accountbalance"));
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_customer(?, ?, ?, ?, ?, ?)}");
			stmt.setString(++index, customer.getUsername());
			stmt.setString(++index, customer.getPassword());
			stmt.setInt(++index, customer.getApprovalCode());
			stmt.setInt(++index, customer.getLockCode());
			stmt.setInt(++index, customer.getRejected());
			stmt.setBigDecimal(++index, customer.getAccountBalance());
			//stmt.close();
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updateCustomer(Customer customer) {
		int index = 0, result;
		
		try (Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL update_customer(?, ?, ?, ?, ?)}");
			stmt.setString(++index, customer.getUsername());
			stmt.setInt(++index, customer.getApprovalCode());
			stmt.setInt(++index, customer.getLockCode());
			stmt.setInt(++index, customer.getRejected());
			stmt.setBigDecimal(++index, customer.getAccountBalance());
			result = stmt.executeUpdate();
			stmt.close();
			return result > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	} 

	@Override
	public String getPasswordHash(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
