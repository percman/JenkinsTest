package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImpl implements EmployeeDao{

	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	private static EmployeeDaoImpl instance;
	private EmployeeDaoImpl() {}
	public static EmployeeDaoImpl getInstance() {
		if(instance == null) {
			instance = new EmployeeDaoImpl();
		}
		return instance;
	}
	@Override
	public boolean insertEmployee(Employee employee) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_employee(?, ?, ?, ?, ?)}");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			stmt.setString(++index, employee.getFirstname());
			stmt.setString(++index, String.valueOf(employee.getMiddleInit()));
			stmt.setString(++index, employee.getLastName());
			return stmt.executeUpdate()>0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return false;
	}
	@Override
	public Employee viewEmployee(int id) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM info "
					+ "NATURAL JOIN employee WHERE employee_id=?");
			stmt.setInt(++index, id);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Employee employee=new Employee(rs.getInt("employee_id"), rs.getString("username"), rs.getString("password"), 
						rs.getString("first_name"), rs.getString("middle_initial").charAt(0), rs.getString("last_name"));
				return employee;
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return null;
	}
	@Override
	public boolean updateEmployee(Employee employee) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL update_employee(?, ?, ?, ?, ?, ?)}");
			stmt.setInt(++index,employee.getId());
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			stmt.setString(++index, employee.getFirstname());
			stmt.setString(++index, String.valueOf(employee.getMiddleInit()));
			stmt.setString(++index, employee.getLastName());
			return stmt.executeUpdate()>0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return false;
	}
	@Override
	public List<Reimbursement> listPending(Employee employee) {
		int index = 0;
		List<Reimbursement> reimbursements= new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement WHERE requestor_id=? AND status = 'Pending'");
			stmt.setInt(++index, employee.getId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Reimbursement reimbursement=new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("requestor_id"),
						rs.getString("category"), rs.getDouble("amount"), rs.getString("status"),
						rs.getTimestamp("request_time").toString());
				reimbursements.add(reimbursement);
			}
			return reimbursements;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return null;
	}

	@Override
	public List<Reimbursement> listResolved(Employee employee) {
		int index = 0;
		List<Reimbursement> reimbursements= new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * "
					+ "FROM reimbursement WHERE requestor_id=? AND status <> 'Pending'");
			stmt.setInt(++index, employee.getId());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Reimbursement reimbursement=new Reimbursement(rs.getInt("reimbursement_id"), 
						rs.getInt("requestor_id"), rs.getInt("approver_id"),
						rs.getDouble("amount"), rs.getString("category"), rs.getString("status"),
						rs.getTimestamp("request_time").toString(), rs.getTimestamp("approval_time").toString());
				reimbursements.add(reimbursement);
			}
			return reimbursements;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return null;
	}
	@Override
	public Employee getEmployee(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM info "
					+ "NATURAL JOIN employee WHERE username=?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				Employee employee=new Employee(rs.getInt("employee_id"), rs.getString("username"), rs.getString("password"), 
						rs.getString("first_name"), rs.getString("middle_initial").charAt(0), rs.getString("last_name"));
				return employee;
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}		return null;
	}
}
