package com.revature.dao;

import static com.revature.logger.ReimbursementLogger.logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.connection.ConnectionObject;
import com.revature.employee.Employee;

public class EmployeeDaoImplementation implements EmployeeDao {

	Connection conn = ConnectionObject.getInstance();

	@Override
	public Employee getEmployee() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getPasswordHash() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertUser(Employee employee) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL insert_employee(?,?)}");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
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

}
