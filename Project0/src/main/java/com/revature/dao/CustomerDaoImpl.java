package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Customer;
import com.revature.util.ConnectionUtil;

public class CustomerDaoImpl implements CustomerDao{

	private static CustomerDaoImpl instance;
	
	private CustomerDaoImpl() {}
	
	public static CustomerDaoImpl getInstance() {
		if (instance == null) {
			instance = new CustomerDaoImpl();
		}
		return instance;
	}
	
	
	@Override
	public Customer getCustomer(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customers WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Customer(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
								rs.getString("fname"), rs.getString("lname"));
			}
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertCustomer(Customer customer) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_customer(?,?,?,?)}");
			stmt.setString(++index, customer.getUsername());
			stmt.setString(++index, customer.getPassword());
			stmt.setString(++index, customer.getFname());
			stmt.setString(++index, customer.getLname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		} 			return false;
	}

	@Override
	public String getPasswordHash(Customer customer) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, customer.getUsername());
			stmt.setString(++index, customer.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}
		return null;
	}

	@Override
	public boolean changeLock(int input_id) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL change_lock(?)}");
			stmt.setInt(++index, input_id);
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}
		return false;
	}

	@Override
	public void depositCustomer(Customer customer, int amount) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL deposit(?,?)}");
			stmt.setString(++index, customer.getUsername());
			stmt.setInt(++index, amount);
			stmt.executeQuery();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}		
	}

	@Override
	public void withdrawCustomer(Customer customer, int amount) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL withdraw(?,?)}");
			stmt.setString(++index, customer.getUsername());
			stmt.setInt(++index, amount);
			stmt.executeQuery();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}		
	}

	
	
	
}
