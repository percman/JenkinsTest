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
import com.revature.model.Manager;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ManagerDaoImpl implements ManagerDao {

	private static final Logger logger = Logger.getLogger(ManagerDaoImpl.class);

	private static ManagerDaoImpl instance;
	private ManagerDaoImpl() {}
	public static ManagerDaoImpl getInstance() {
		if(instance == null) {
			instance = new ManagerDaoImpl();
		}
		return instance;
	}
	@Override
	public boolean insertManager(Manager manager) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_manager(?, ?, ?, ?, ?)}");
			stmt.setString(++index, manager.getUsername());
			stmt.setString(++index, manager.getPassword());
			stmt.setString(++index, manager.getFirstname());
			stmt.setString(++index, String.valueOf(manager.getMiddleInit()));
			stmt.setString(++index, manager.getLastName());
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
	public boolean approveDeny(String response, int reimburse_id, int manager_id) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement pStmt = conn.prepareStatement("SELECT * "
					+ "FROM reimbursement INNER JOIN manager ON reimbursement.requestor_id=manager.employee_id "
					+ "WHERE reimbursement_id=? AND manager_id = ?");
			pStmt.setInt(++index, reimburse_id);
			pStmt.setInt(++index, manager_id);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) return false;
			else {
				index=0;
				CallableStatement stmt = conn.prepareCall("{CALL approve_deny(?, ?, ?)}");
				stmt.setString(++index, response);
				stmt.setInt(++index, reimburse_id);
				stmt.setInt(++index, manager_id);
				return stmt.executeUpdate()>0;
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return false;
	}
	@Override
	public List<Employee> viewEmployees() {
		List<Employee> employees=new ArrayList<>();
 		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee NATURAL JOIN info");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Employee employee=new Employee(rs.getInt("employee_id"), rs.getString("username"), rs.getString("password"), 
						rs.getString("first_name"), rs.getString("middle_initial").charAt(0), rs.getString("last_name"));
				employees.add(employee);
			}
			return employees;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return null;
	}
	@Override
	public List<Reimbursement> viewReimbursements() {
		List<Reimbursement> reimbursements =new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * "
					+ "FROM reimbursement");
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Reimbursement reimbursement = new Reimbursement(rs.getInt("reimbursement_id"), 
						rs.getInt("requestor_id"), rs.getInt("approver_id"), rs.getDouble("amount"),
						rs.getString("category"), rs.getString("status"),
						rs.getTimestamp("request_time").toString(), rs.getTimestamp("approval_time")==null ? "" : rs.getTimestamp("approval_time").toString(),
						rs.getString("image"));
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
	public List<Reimbursement> viewReimbursementByEmployee(int id) {
		List<Reimbursement> reimbursements =new ArrayList<>();
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * "
					+ "FROM reimbursement WHERE requestor_id = ?");
			stmt.setInt(++index, id);
			ResultSet rs=stmt.executeQuery();
			while(rs.next()) {
				Reimbursement reimbursement = new Reimbursement(rs.getInt("reimbursement_id"),
						rs.getInt("requestor_id"), rs.getInt("approver_id"), rs.getDouble("amount"),
						rs.getString("category"), rs.getString("status"), rs.getTimestamp("request_time").toString(),
						rs.getTimestamp("approval_time")==null ? "" : rs.getTimestamp("approval_time").toString(),
								rs.getString("image"));
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
	public String approver(int id) {
		int index=0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT first_name, middle_initial, last_name "
					+ "FROM info NATURAL JOIN manager WHERE manager_id=?");
			stmt.setInt(++index, id);
			ResultSet rs=stmt.executeQuery();
			if(rs.next()) {
				String name=rs.getString("first_name")+ " " + 
						rs.getString("middle_initial")+" " +rs.getString("last_name");
				System.out.println(name);
				return name;
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
	public List<Reimbursement> listPending() {
		List<Reimbursement> reimbursements= new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement WHERE status = 'Pending'");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				Reimbursement reimbursement=new Reimbursement(rs.getInt("reimbursement_id"), rs.getInt("requestor_id"),
						rs.getString("category"), rs.getDouble("amount"), rs.getString("status"),
						rs.getTimestamp("request_time").toString(), rs.getString("image"));
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

}
