package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Applying;
import com.revature.util.ConnectionUtil;

public class ApplyingDaoImpl implements ApplyingDao{

	private static ApplyingDaoImpl instance;
	
	private ApplyingDaoImpl() {}
	
	public static ApplyingDaoImpl getInstance() {
		if (instance == null) {
			instance = new ApplyingDaoImpl();
		}
		return instance;
	}

	@Override
	public Applying getApplying(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM applying_customers WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				return new Applying(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
								rs.getString("fname"), rs.getString("lname"));
			}
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
				return null;
	}

	@Override
	public boolean insertApplying(Applying applying) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_applying(?,?,?,?)}");
			stmt.setString(++index, applying.getUsername());
			stmt.setString(++index, applying.getPassword());
			stmt.setString(++index, applying.getFname());
			stmt.setString(++index, applying.getLname());
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		} 	
		return false;
	}

	@Override
	public boolean deleteApplying(String username) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPasswordHash(Applying applying) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, applying.getUsername());
			stmt.setString(++index, applying.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("HASH");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());	
			System.err.println("SQL State: " + sqle.getSQLState());				
			System.err.println("Error Code: " + sqle.getErrorCode());		
		}
		return null;
	}

}
