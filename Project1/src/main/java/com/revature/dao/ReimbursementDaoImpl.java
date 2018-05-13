package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Reimbursement;
import com.revature.util.ConnectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao{

	private static final Logger logger = Logger.getLogger(ReimbursementDaoImpl.class);

	private static ReimbursementDaoImpl instance;
	private ReimbursementDaoImpl() {}
	public static ReimbursementDaoImpl getInstance() {
		if(instance == null) {
			instance = new ReimbursementDaoImpl();
		}
		return instance;
	}
	@Override
	public boolean insertReimbursement(Reimbursement reimbursement) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_reimbursement(?, ?, ?)}");
			stmt.setInt(++index, reimbursement.getRequestorId());
			stmt.setString(++index, reimbursement.getCategory());
			stmt.setDouble(++index, reimbursement.getAmount());
			return stmt.executeUpdate()>0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return false;
	}

}
