package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Account;
import com.revature.utility.ConnectionUtil;

public class CredentialsDAOImpl implements CredentialsDAO {
	
	//Returns the credentials of a user based on their username
	@Override
	public Account getCredentials(String name) {
		String sql = "SELECT * FROM credentials WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, name);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				return new Account(rs.getInt("id"),rs.getString("username"),rs.getString("password"));
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertCredentials(Account user) {
		String sql = "{CALL insert_credentials(?,?)}";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(++index, user.getUserName());
			stmt.setString(++index, user.getPassword());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean deleteCredentials(String username) {
		String sql = "{CALL delete_credentials(?)}";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(++index, username);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public String getPasswordHash(Account user) {
		int index = 0;
		String sql = "SELECT get_user_hash(?,?) AS HASH FROM dual";
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, user.getUserName());
			stmt.setString(++index, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("HASH");
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	//Checks whether the username is already taken.
	@Override
	public boolean CheckAvailablility(String username) {
		String sql = "SELECT username FROM credentials WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return true;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE: "+sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

}
