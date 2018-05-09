package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.factory.ReimbursementFactory;
import com.revature.logs.LogHere;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.service.EmployeeService;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static ReimbursementDaoImpl instance;
	private ReimbursementDaoImpl() {}
	
	public static ReimbursementDaoImpl getInstance() {
		if(instance == null) {
			instance = new ReimbursementDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<Reimbursement> getAllReimbursements() {
		List<Reimbursement> reimbursementlist = new ArrayList<>();
//		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {				
				Reimbursement newreimbursement = ReimbursementFactory.getReimbursement(rs.getString("category"));
				
				newreimbursement.setId(rs.getInt("id"));
				newreimbursement.setAmount(rs.getLong("amount"));
				newreimbursement.setRequestor_id(rs.getInt("requestor_id"));
				newreimbursement.setApprover_id(rs.getInt("approver_id"));
				newreimbursement.setStatus(rs.getInt("status") > 0);
				newreimbursement.setTimemade(rs.getTimestamp("timemade"));
				newreimbursement.setTimeapproved(rs.getTimestamp("timeapproved"));
//				newreimbursement.setReason(rs.getString("reason"));
				
				reimbursementlist.add(newreimbursement);
			}
			return reimbursementlist;	
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}

	@Override
	public Reimbursement getReimbursement(String employeename) {
//		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee "
					+ "INNER JOIN personalinfo ON employee.id = personalinfo.id "
					+ "WHERE employee.id = ?");
			Employee new_employee = EmployeeService.getEmployee(employeename);
			stmt.setInt(1, new_employee.getId());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {				
				Reimbursement newreimbursement = ReimbursementFactory.getReimbursement(rs.getString("category"));
				
				newreimbursement.setId(rs.getInt("id"));
				newreimbursement.setAmount(rs.getLong("amount"));
				newreimbursement.setRequestor_id(rs.getInt("requestor_id"));
				newreimbursement.setApprover_id(rs.getInt("approver_id"));
				newreimbursement.setStatus(rs.getInt("status") > 0);
				newreimbursement.setTimemade(rs.getTimestamp("timemade"));
				newreimbursement.setTimeapproved(rs.getTimestamp("timeapproved"));
//				newreimbursement.setReason(rs.getString("reason"));
				
				return newreimbursement;	
			}
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}

	@Override
	public boolean insertReimbursement(Reimbursement reimbursement) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?,?,?)}");
			stmt.setInt(++index, reimbursement.getRequestor_id());
			stmt.setString(++index, reimbursement.getCategory());
			stmt.setDouble(++index, reimbursement.getAmount());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}

	@Override
	public boolean updateReimbursement(Reimbursement reimbursement) {
		int index = 0;
		int status = 0;
		if(reimbursement.isStatus())
			status = 1;
		try(Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL update_reimbursement(?,?,?)}");
			stmt.setInt(++index, reimbursement.getId());
			stmt.setInt(++index, reimbursement.getApprover_id());
			stmt.setInt(++index, status);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}  catch (SQLException sqle) {
			LogHere.warn(sqle.getMessage());
			LogHere.warn("SQLE State: " + sqle.getSQLState());
			LogHere.warn("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}



}
