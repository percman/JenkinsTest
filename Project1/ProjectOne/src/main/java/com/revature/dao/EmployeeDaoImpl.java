package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public Employee getEmployee(int id) {
		try (Connection c = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM employeeTable WHERE employeeId = " + id);
			ResultSet rs = stmt.executeQuery();
			Employee e = null;
			while(rs.next()) {
				e = new Employee(rs.getString("userName"), rs.getString("userPassword"));
			}
			return e;
			
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public ArrayList<Employee> getAllEmployees() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<Employee> employeeList = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employeeTable");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {	
				employeeList.add(new Employee(rs.getString("userName"), rs.getString("userPassword")));
			}
			return employeeList;
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertEmployee(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateEmployee(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
