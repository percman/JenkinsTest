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
			PreparedStatement stmtEmployee = conn.prepareStatement("Select * FROM employee WHERE username = ? ");
			stmtEmployee.setString(1, username);
			ResultSet rsEmployee = stmtEmployee.executeQuery();
			if (rsEmployee.next()) {
				employee.setId(rsEmployee.getInt("employee_id"));
				employee.setUsername(rsEmployee.getString("username"));
				employee.setPassword(rsEmployee.getString("password"));
				employee.setFinancialManager(false);
			}
			PreparedStatement stmtInfo = conn.prepareStatement("SELECT * FROM employee_info WHERE employee_id = ?");
			stmtInfo.setInt(1, employee.getId());
			ResultSet rsInfo = stmtInfo.executeQuery();
			if (rsInfo.next()) {
				employee.setFirstname(rsInfo.getString("f_name"));
				employee.setMiddleInitial(rsInfo.getString("m_initial"));
				employee.setLastname(rsInfo.getString("l_name"));
				employee.setPhone(rsInfo.getInt("phone"));
				employee.setEmail(rsInfo.getString("email"));
			}
			return employee;
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
