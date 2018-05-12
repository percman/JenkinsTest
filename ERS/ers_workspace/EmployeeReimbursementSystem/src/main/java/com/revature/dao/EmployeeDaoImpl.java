package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.logging.LogThis;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao {

	private static EmployeeDaoImpl instance;

	private EmployeeDaoImpl() {
	}

	public static EmployeeDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}

	@Override
	public Employee getEmployee(String username) {
		Employee employee = new Employee();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee e" + 
					"JOIN employee_info ei ON e.employee_id = ei.employee_id " + 
					"WHERE e.employee_id = ?" );
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				employee.setId(rs.getInt("employee_id"));
				employee.setUsername(rs.getString("username"));
				employee.setPassword(rs.getString("password"));
				employee.setFinancialManager(false);
				employee.setFirstname(rs.getString("f_name"));
				employee.setMiddleInitial(rs.getString("m_initial"));
				employee.setLastname(rs.getString("l_name"));
				employee.setPhone(rs.getInt("phone"));
				employee.setEmail(rs.getString("email"));
				return employee;
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertEmployee(Employee employee) {
		return false;
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		
		return false;
	}

	@Override
	public String getPasswordHash(Employee employee) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) AS HASH FROM dual");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Reimbursement> viewAllReimbursements() {
		return null;
	}

	@Override
	public List<Reimbursement> viewReimbursementByStatus(int status) {
		return null;
	}

}
