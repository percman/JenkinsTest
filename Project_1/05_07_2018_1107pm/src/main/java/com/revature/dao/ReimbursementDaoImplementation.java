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
import com.revature.factory.Reimbursement;
import com.revature.factory.ReimbursementFactory;

public class ReimbursementDaoImplementation implements ReimbursementDao {

	Connection conn = ConnectionObject.getInstance();
	
	@Override
	public boolean submitRequest(Reimbursement rb) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?,?,?,?)}");
			stmt.setInt(++index, rb.getRequestor_id());
			stmt.setInt(++index, rb.getCategory());
			stmt.setFloat(++index, rb.getAmount());
			stmt.setInt(++index, rb.getStatus());
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
	public List<Reimbursement> getRequests(Employee employee) {
		try {
			List<Reimbursement> rbl = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement WHERE requestor_id = ?");
			stmt.setInt(1, employee.getId());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Reimbursement rb = ReimbursementFactory.getReimbursement(rs.getInt("category"));
				rb.setRequestor_id(rs.getInt("requestor_id"));
				rb.setApprover_id(rs.getInt("f_manager_id"));
				rb.setAmount(rs.getInt("amount"));
				rb.setStatus(rs.getInt("status"));
				rb.setRequestDate(rs.getDate("request_date"));
				rb.setApproveDate(rs.getDate("approve_date"));
				rbl.add(rb);
			}
			return rbl;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		System.out.println("No requests found");
		return null;
	}

	@Override
	public List<Reimbursement> getAllRequests() {
		try {
			List<Reimbursement> rbl = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {			
				Reimbursement rb = ReimbursementFactory.getReimbursement(rs.getInt("category"));
				rb.setReimbursement_id(rs.getInt("reimbursement_id"));
				rb.setRequestor_id(rs.getInt("requestor_id"));
				rb.setApprover_id(rs.getInt("f_manager_id"));
				rb.setAmount(rs.getInt("amount"));
				rb.setStatus(rs.getInt("status"));
				rb.setRequestDate(rs.getDate("request_date"));
				rb.setApproveDate(rs.getDate("approve_date"));
				rbl.add(rb);
			}
			return rbl;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		System.out.println("No requests found");
		return null;
	}

	@Override
	public boolean approveReimbursement(int requestorId, int managerId, int reimbursementId) {
		try {
			int index = 0;
			CallableStatement stmt = conn.prepareCall("{CALL approve_reimbursement(?,?,?)}");
			stmt.setInt(++index, requestorId);
			stmt.setInt(++index, managerId);
			stmt.setInt(++index, reimbursementId);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tReimbursement approved");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean denyReimbursement(int requestorId, int managerId, int reimbursementId) {
		try {
			int index = 0;
			CallableStatement stmt = conn.prepareCall("{CALL deny_reimbursement(?,?,?)}");
			stmt.setInt(++index, requestorId);
			stmt.setInt(++index, managerId);
			stmt.setInt(++index, reimbursementId);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tReimbursement denied");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
	
}
