package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.logs.LogHere;
import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	
	private static EmployeeDaoImpl instance;
	private EmployeeDaoImpl() {}
	
	public static EmployeeDaoImpl getInstance() {
		if(instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> employeelist = new ArrayList<>();
//		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee WHERE manager = 0 ORDER BY id");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {				
				employeelist.add(new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("manager")>0), rs.getString("firstname"), rs.getString("lastname"),
						rs.getTimestamp("datehired"), rs.getString("email"), rs.getInt("phonenumber")));
			}
			return employeelist;	
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}

	@Override
	public List<Employee> getAllManagers() {
		List<Employee> employeelist = new ArrayList<>();
//		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee WHERE manager = 1 ORDER BY id");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {				
				employeelist.add(new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("manager")>0), rs.getString("firstname"), rs.getString("lastname"),
						rs.getTimestamp("datehired"), rs.getString("email"), rs.getInt("phonenumber")));
			}
			return employeelist;	
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}

	@Override
	public Employee getEmployee(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {				
				return new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("manager")>0), rs.getString("firstname"), rs.getString("lastname"),
						rs.getTimestamp("datehired"), rs.getString("email"), rs.getInt("phonenumber"));
			}
				
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}

	@Override
	public boolean insertEmployee(Employee employee) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_employee(?, ?, ?, ?, ?, ?, ?)}");
			
			int manager = 0;
			if(employee.isManagerstatus()) {
				manager = 1;
			}
			
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			stmt.setInt(++index, manager);
			stmt.setString(++index, employee.getFirstname());
			stmt.setString(++index, employee.getLastname());
			stmt.setString(++index, employee.getEmail());
			stmt.setLong(++index, employee.getPhonenumber());
			return stmt.executeUpdate() > 0;
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			Employee gotemployee = getEmployee(employee.getUsername());
			
			CallableStatement stmt = conn.prepareCall("{CALL update_personalinfo(?, ?, ?, ?, ?)}");
			stmt.setInt(++index, gotemployee.getId());
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			stmt.setString(++index, employee.getEmail());
			stmt.setLong(++index, employee.getPhonenumber());

			return stmt.executeUpdate() > 0;
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}

	@Override
	public String getPasswordHash(Employee employee) {
		int index = 0; 
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT GET_EMPLOYEE_HASH(?,?) AS HASH FROM dual");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("HASH");
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public Employee getAnyEmployee() {
		try(Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Testing the connection. Please wait.");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usertable");
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {			
				System.out.println("Success!");
				return new Employee(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("manager")>0), rs.getString("firstname"), rs.getString("lastname"),
						rs.getTimestamp("datehired"), rs.getString("email"), rs.getInt("phonenumber"));
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}
	
	
	
	
}
