package com.revature.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.dao.R_InformationDAO;
import com.revature.model.Reimbursement;
import com.revature.utility.ConnectionUtility;

public class R_InformationDaoImpl implements R_InformationDAO {

	@Override
	public boolean setInformation(Reimbursement reimbursement) {
		String sql = "{CALL insert_r_info(?,?)}";
		int index = 0;
		try(Connection conn = ConnectionUtility.getConnection()){
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(++index, reimbursement.getId());
			cs.setString(++index, reimbursement.getCategory());
			int success = cs.executeUpdate();
			return success > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public Reimbursement getInformation(Reimbursement reimbursement) {
		String sql = "SELECT category,status FROM reimbursement_info WHERE r_id =?";
		int index = 0;
		try(Connection conn = ConnectionUtility.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(++index, reimbursement.getId());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				reimbursement.setCategory(rs.getString("category"));
				reimbursement.setStatus(rs.getString("status"));
			}
			return reimbursement;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}
	
	public boolean UpdateInformation(Reimbursement rbmt) {
		String sql = "UPDATE reimbursement_info SET status = ? WHERE r_id = ?";
		int index = 0;
		try(Connection conn = ConnectionUtility.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, rbmt.getStatus());
			stmt.setInt(++index, rbmt.getId() );
			int success = stmt.executeUpdate();
			return success > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}return false;
	}

}
