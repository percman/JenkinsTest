package com.revature.dao;

import java.io.FileInputStream;
import java.io.IOException;
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
	public boolean newReimbursement(Reimbursement reimbursement, String filePath) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			LogThis.info("dehljdeaewj");
			FileInputStream fis = new FileInputStream(filePath);

			CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?, ?, ?, ?)}");
			stmt.setInt(++index, reimbursement.getRequestorId());
			stmt.setString(++index, reimbursement.getCategory());
			stmt.setString(++index, reimbursement.getAmountString());
			stmt.setBinaryStream(++index, fis, fis.available());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		} catch (IOException e) {
			LogThis.warn(e.getMessage());
		}
		return false;
	}

}
