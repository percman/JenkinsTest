package com.revature.dao;

import static com.revature.logger.ReimbursementLogger.logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.connection.ConnectionObject;
import com.revature.employee.Employee;
import com.revature.factory.Reimbursement;

public class ReimbursementDaoImplementation implements ReimbursementDao {

	Connection conn = ConnectionObject.getInstance();
	
	@Override
	public boolean submitRequest(Reimbursement rb) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL insert_request(?,?,?)}");
			stmt.setInt(++index, rb.getRequestor_id());
			stmt.setInt(++index, rb.getCategory());
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
	public Reimbursement getRequests(Employee employee) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getAllRequests() {
		// TODO Auto-generated method stub
		return null;
	}

}
