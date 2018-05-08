package com.revature.dao;

import static com.revature.logger.ReimbursementLogger.logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.connection.ConnectionObject;
import com.revature.employee.Employee;

public class EmployeeDaoImplementation implements EmployeeDao {

	Connection conn = ConnectionObject.getInstance();

	@Override
	public Employee getEmployee(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee e FULL OUTER JOIN employee_information ei "
										+ "	ON e.employee_id = ei.employee_id WHERE e.employee_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Employee(rs.getInt("employee_id"), rs.getString("firstname"), 
						rs.getString("lastname"), rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		return null;
	}
	
	@Override
	public Employee getEmployee(String username) {
		try {
			System.out.println("HERE");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee e FULL OUTER JOIN employee_information ei "
										+ "	ON e.employee_id = ei.employee_id WHERE e.username = ?");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Employee(rs.getInt("employee_id"), rs.getString("firstname"), 
						rs.getString("lastname"), rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		try {
			List<Employee> employees = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee e FULL OUTER JOIN employee_information ei "
										+ "	ON e.employee_id = ei.employee_id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				employees.add(new Employee(rs.getInt("employee_id"), rs.getString("firstname"), 
						rs.getString("lastname"), rs.getString("username"), rs.getString("password")));
			}
			return employees;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		return null;
	}

	@Override
	public boolean insertUser(Employee employee) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL insert_employee(?,?,?,?)}");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			stmt.setString(++index, employee.getFirstname());
			stmt.setString(++index, employee.getLastname());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		return false;
	}

	@Override
	public boolean modifyUser() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override // Get the hashed password from the database
	public String getPasswordHash(Employee employee) {
		int index = 0;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public String getPasswordHash() {
		// TODO Auto-generated method stub
		return null;
	}

}
