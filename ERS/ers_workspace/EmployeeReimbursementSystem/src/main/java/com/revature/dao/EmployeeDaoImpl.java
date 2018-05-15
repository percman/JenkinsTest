package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
	public boolean isFinMan (String username) {

		try (Connection conn = ConnectionUtil.getConnection()) {
			int index = 0;
			PreparedStatement stmt = conn.prepareStatement("SELECT is_f_manager FROM employee WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();

			if(rs.next()) {
				if(rs.getInt("is_f_manager")==1) {
					return true;
				} else {
					return false;
				}
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}

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
	public Employee getEmployee(String username) {
//		LogThis.info("do i get here 3");
		Employee employee = new Employee();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee e "
					+ "JOIN employee_info ei ON e.employee_id = ei.employee_id " + "WHERE e.username = ?");
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
				employee.setPhone(rs.getString("phone"));
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
	public boolean updateEmployee(Employee employee) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL update_employee (?, ?, ?, ?, ?, ?, ?)}");
			stmt.setInt(++index, employee.getId());
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getFirstname());
			stmt.setString(++index, employee.getMiddleInitial());
			stmt.setString(++index, employee.getLastname());
			stmt.setString(++index, employee.getPhone());
			stmt.setString(++index, employee.getEmail());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public List<Reimbursement> viewAllReimbursements(Employee employee) {
		List<Reimbursement> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT r.reimbursement_id, (ei.f_name || ' ' || ei.l_name) AS requestor_name, s.r_status, "
							+ "c.r_category, r.amount, r.submitted, r.resolved, ai.approver_name, ei.employee_id, r.image FROM reimbursement r "
							+ "JOIN employee_info ei ON r.requestor_id = ei.employee_id "
							+ "JOIN r_status s ON s.status_id = r.status_id "
							+ "JOIN r_category c ON c.category_id = r.category_id "
							+ "JOIN (SELECT (ei2.f_name || ' ' || ei2.l_name) AS approver_name, fm.f_manager_id AS fmid FROM employee_info ei2 "
							+ "JOIN f_manager fm ON ei2.employee_id = fm.employee_id) ai ON ai.fmid = r.approver_id "
							+ "WHERE r.requestor_id = ?");
			stmt.setInt(1, employee.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("employee_id"), rs.getString("requestor_name"),
						rs.getString("approver_name"), rs.getString("r_category"), rs.getString("r_status"),
						rs.getInt("amount"), rs.getString("submitted"), rs.getString("resolved"), rs.getBlob("image")));
			}
			return list;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<Reimbursement> viewReimbursementByStatus(Employee employee, int status) {
		List<Reimbursement> list = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(
					"SELECT r.reimbursement_id, (ei.f_name || ' ' || ei.l_name) AS requestor_name, s.r_status, "
							+ "c.r_category, r.amount, r.submitted, r.resolved, ai.approver_name, ei.employee_id, r.image FROM reimbursement r "
							+ "JOIN employee_info ei ON r.requestor_id = ei.employee_id "
							+ "JOIN r_status s ON s.status_id = r.status_id "
							+ "JOIN r_category c ON c.category_id = r.category_id "
							+ "JOIN (SELECT (ei2.f_name || ' ' || ei2.l_name) AS approver_name, fm.f_manager_id AS fmid FROM employee_info ei2 "
							+ "JOIN f_manager fm ON ei2.employee_id = fm.employee_id) ai ON ai.fmid = r.approver_id "
							+ "WHERE r.requestor_id = ? AND r.status_id = ?");
			stmt.setInt(1, employee.getId());
			stmt.setInt(2, status);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("employee_id"), rs.getString("requestor_name"),
						rs.getString("approver_name"), rs.getString("r_category"), rs.getString("r_status"),
						rs.getInt("amount"), rs.getString("submitted"), rs.getString("resolved"), rs.getBlob("image")));
			}
			return list;
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

}
