package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.revature.logging.LogThis;
import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {

	private static ReimbursementDaoImpl instance;

	private ReimbursementDaoImpl() {
	}

	public static ReimbursementDaoImpl getInstance() {
		if (instance == null) {
			instance = new ReimbursementDaoImpl();
		}
		return instance;
	}

	@Override
	public boolean newReimbursement(Reimbursement reimbursement) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?, ?, ?)}");
			stmt.setInt(++index, reimbursement.getRequestorId());
			stmt.setInt(++index, reimbursement.getCategoryId());
			stmt.setInt(++index, reimbursement.getAmount());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

}
