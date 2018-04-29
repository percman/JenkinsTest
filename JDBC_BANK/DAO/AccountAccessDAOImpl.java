package com.revature.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.utility.ConnectionUtil;

public class AccountAccessDAOImpl implements AccountAccessDAO {

	@Override
	public List<User> getLockedUsers() {
		String sql = "SELECT username FROM account_access WHERE locked = ?";
		int index = 0;
		List<User> users = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, "true");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getString("username")));
			}
			return users;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}
	
	@Override
	public List<User> getUnlockedUsers(){
		String sql = "SELECT username FROM account_access WHERE locked = ? AND permissions =? AND pending = ?";
		int index = 0;
		List<User> users = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, "false");
			stmt.setString(++index, "false");
			stmt.setString(++index, "false");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getString("username")));
			}
			return users;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public Boolean getPermission(Account username) {
		String sql = "SELECT permissions FROM account_access WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, username.getUserName());
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return Boolean.parseBoolean(rs.getString("permissions"));
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public List<User> getPending() {
		List<User> users = new ArrayList<>();
		String sql = "SELECT username FROM account_access WHERE pending = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, "true");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getString("username")));
			}return users;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertAccountAccess(Account account) {
		String sql = "{CALL insert_account_access(?)}";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall(sql);
			stmt.setString(++index, account.getUserName());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public Boolean isLocked(Account account) {
		String sql = "SELECT locked FROM account_access WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, account.getUserName());
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return Boolean.parseBoolean(rs.getString("locked"));
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public Boolean isPending(Account account) {
		String sql = "SELECT pending FROM account_access WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, account.getUserName());
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return Boolean.parseBoolean(rs.getString("pending"));
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean ApproveUser(User user) {
		String sql = "UPDATE account_access SET pending = 'false' WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, user.getUserName());
			int success = stmt.executeUpdate();
			return success > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean LockUser(User user) {
		String sql = "UPDATE account_access SET locked = 'true' WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, user.getUserName());
			int success = stmt.executeUpdate();
			return success > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean UnLockUser(User user) {
		String sql = "UPDATE account_access SET locked = 'false' WHERE username = ?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, user.getUserName());
			int success = stmt.executeUpdate();
			return success > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

}
