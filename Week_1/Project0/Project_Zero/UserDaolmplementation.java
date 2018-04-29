package com.revature.zero.Project_Zero;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaolmplementation implements UserDao{

	@Override
	public User getUser(String name) {
		int index = 0;
		try (Connection c = ConnectionUtil.getConnection()) {
			System.out.println("Name as seen " + name);
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM USERTABLE WHERE USERNAME = '" + name +"'");
			//stmt.setString(++index, name);
			ResultSet rs = stmt.executeQuery();
			User u = null;
			while(rs.next()) {
				u = new User(rs.getString("USERNAME"), rs.getInt("BALANCE"), 
						rs.getInt("ISADMIN") == 1, rs.getInt("ISLOCKED") == 1, rs.getInt("ISAPPROVED")==1);
			}
			return u;
			
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public ArrayList<User> getAllUsers() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			ArrayList<User> userList = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM USERTABLE");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("USERNAME"));
				boolean isAdmin = rs.getInt("ISADMIN") == 1;
				boolean isLocked = rs.getInt("ISLOCKED") == 1;
				boolean isApproved = rs.getInt("ISAPPROVED") == 1;
				userList.add(new User(rs.getString("USERNAME"), rs.getInt("BALANCE"), isAdmin, isLocked, isApproved));
			}
			return userList;
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL STATE " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("{CALL insert_user(?, ?, ?, ?, ?)  }");
			stmt.setString(++index, user.getName());
			stmt.setInt(++index, user.getBalance());
			int admin = user.isAdmin() ? 1 : 0;
			stmt.setInt(++index, admin);
			int locked = user.isLocked() ? 1: 0;
			stmt.setInt(++index, locked);
			int approved = user.isApproved() ? 1: 0;
			stmt.setInt(++index, approved);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			String userName = user.getName();
			int balance = user.getBalance();
			int admin = user.isAdmin() ? 1 : 0;
			int locked = user.isLocked() ? 1 : 0;
			int approved = user.isApproved() ? 1 : 0;
			PreparedStatement stmt = conn.prepareStatement("{CALL update_user(?, ?, ?, ?, ?)  }");
			stmt.setString(++index, userName);
			stmt.setInt(++index, balance);
			stmt.setInt(++index, admin);
			stmt.setInt(++index, locked);
			stmt.setInt(++index, approved);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean deleteUser(String name) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt =  conn.prepareCall("{CALL delete_user(?)}");
			stmt.setString(1, name);
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public int getTotalUsers() {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_total_users FROM USERTABLE");
			ResultSet rs = stmt.executeQuery();
			int output = 0;
			if(rs.next()) {
				output = rs.getInt("get_total_users");
			}
			return output;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return 0;
	}

	@Override
	public int getTotalBalance() {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_total_balance FROM USERTABLE");
			ResultSet rs = stmt.executeQuery();
			int output = 0;
			if(rs.next()) {
				output = rs.getInt("get_total_balance");
			}
			return output;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return 0;
	}

	@Override
	public boolean updateTransaction(int total) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO TRANSACTIONS VALUES(null, " + total + ")");
			ResultSet rs = stmt.executeQuery();
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println(sqle.getSQLState());
			System.err.println(sqle.getErrorCode());
		}
		return false;
	}
}
