package com.revature.dao;

import static com.revature.logger.ReimbursementLogger.logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.connection.ConnectionObject;
import com.revature.factory.Reimbursement;
import com.revature.factory.ReimbursementFactory;
import com.revature.image.Image;

public class ReimbursementDaoImplementation implements ReimbursementDao {

	Connection conn = ConnectionObject.getInstance();
	
	@Override
	public boolean submitRequest(Reimbursement rb, String file) {
		int index = 0;
		try {
			FileInputStream fin = new FileInputStream(file);
			System.out.println("File Read");
			CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?,?,?,?,?,?)}");
			stmt.setInt(++index, rb.getRequestor_id());
			stmt.setInt(++index, rb.getCategory());
			stmt.setFloat(++index, rb.getAmount());
			stmt.setString(++index, rb.getMessage());
			stmt.setInt(++index, rb.getStatus());
			stmt.setBinaryStream(++index, fin, fin.available());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		} catch (IOException ioe) {
			logger.warn(ioe.getMessage());
			ioe.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbursement> getRequests(int id) {
		try {
			List<Reimbursement> rbl = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement WHERE requestor_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Reimbursement rb = ReimbursementFactory.getReimbursement(rs.getInt("category"));
				rb.setRequestor_id(rs.getInt("requestor_id"));
				rb.setApprover_id(rs.getInt("f_manager_id"));
				rb.setAmount(rs.getInt("amount"));
				rb.setStatus(rs.getInt("status"));
				rb.setRequestDate(rs.getString("request_date"));
				rb.setApproveDate(rs.getString("approve_date"));
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
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reimbursement ORDER BY requestor_id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {			
				Reimbursement rb = ReimbursementFactory.getReimbursement(rs.getInt("category"));
				rb.setReimbursement_id(rs.getInt("reimbursement_id"));
				rb.setRequestor_id(rs.getInt("requestor_id"));
				rb.setApprover_id(rs.getInt("f_manager_id"));
				rb.setAmount(rs.getFloat("amount"));
				rb.setStatus(rs.getInt("status"));
				rb.setRequestDate(rs.getString("request_date"));
				rb.setApproveDate(rs.getString("approve_date"));
				rb.setMessage(rs.getString("message"));
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

	@Override
	public boolean insertPhoto(int id, String file) {
		try {
			FileInputStream fin = new FileInputStream(file);
			int index = 0;
			CallableStatement stmt = conn.prepareCall("INSERT INTO receipt VALUES(?,?)");
			stmt.setInt(++index, id);
			stmt.setBinaryStream(++index, fin, fin.available());
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tImage inserted");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		} catch (IOException ioe) {
			System.out.println("Error reading image file");
		}
		return false;
	}

	@Override
	public Blob retrievePhoto(int id) {
		try {
			Blob blob;
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM receipt WHERE receipt_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {			
				blob = rs.getBlob("receipt");
				return blob;
			}		
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		System.out.println("No requests found");
		return null;
	}

	@Override
	public List<Image> getAllImages() {
		try {
			List<Image> images = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM receipt");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {			
				images.add(new Image(rs.getInt("receipt_id"), rs.getBlob("receipt")));
			}		
			return images;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		System.out.println("No requests found");
		return null;
	}
	
	
}
