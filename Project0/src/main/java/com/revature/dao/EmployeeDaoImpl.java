package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private static EmployeeDaoImpl instance;
	
	private EmployeeDaoImpl() {}
	
	public static EmployeeDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}
	
	@Override
	public Employee getEmployee(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employees WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
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
	public boolean insertEmployee(Employee employee) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_employee(?,?,?,?)}");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			stmt.setString(++index, employee.getFname());
			stmt.setString(++index, employee.getLname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		} 	
		return false;
	}

	@Override
	public boolean deleteEmployee(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPasswordHash(Employee employee) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
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

}
