package com.revature.daoImpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.dao.ReimbursementDAO;
import com.revature.model.Employee;
import com.revature.model.Reimbursement;
import com.revature.utility.ConnectionUtility;

public class ReimbursementDaoImpl implements ReimbursementDAO {

	@Override
	public boolean setReimbursement(Employee emp) {
		String sql = "{CALL insert_reimbursement(?)}";
		int index = 0;
		try(Connection conn = ConnectionUtility.getConnection()){
			CallableStatement cs = conn.prepareCall(sql);
			cs.setInt(++index, emp.getId());
			int success = cs.executeUpdate();
			return success >0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}return false;

	}

	@Override
	public Reimbursement getReimbursement(int id) {
		String sql = "SELECT * FROM reimbursement WHERE r_id =?";
		int index =0;
		Reimbursement rbmt = new Reimbursement();
		try(Connection conn = ConnectionUtility.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(++index, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				rbmt.setId(rs.getInt("r_id"));
				rbmt.setE_id(rs.getInt("request_id"));
				rbmt.setFm_id(rs.getInt("manager_id"));
			}return rbmt;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}
	
	public boolean UpdateReimbursement(Reimbursement rbmt, Employee emp) {
		String sql = "UPDATE reimbursement SET manager_id = ? WHERE r_id = ?";
		int index = 0;
		try(Connection conn = ConnectionUtility.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(++index, emp.getId());
			stmt.setInt(++index, rbmt.getId());
			int success = stmt.executeUpdate();
			return success > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}return false;
	}

}
