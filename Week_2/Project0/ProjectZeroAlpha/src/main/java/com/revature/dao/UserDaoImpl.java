package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.users.User;
import com.revature.util.ConnectionUtil;


public class UserDaoImpl implements UserDao{

	
	private static UserDaoImpl instance;
	private UserDaoImpl() {}
	
	public static UserDaoImpl getInstance() {
		if(instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String username) {		
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM usertable WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), 
						(rs.getInt("lockstatus") < 0), rs.getDouble("balance"));
			}
				
		}  catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQLE State: " + sqle.getSQLState());
			System.err.println("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_user(?, ?, ?)}");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			stmt.setDouble(++index, user.getBalance());
			return stmt.executeUpdate() > 0;
		}  catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQLE State: " + sqle.getSQLState());
			System.err.println("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}

	@Override
	public boolean updateUser(User user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			
			int locked;
			if(user.isLocked())
				locked = 1;
			else
				locked = 0;
			
			CallableStatement stmt = conn.prepareCall("{CALL update_user(?, ?, ?, ?)}");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			stmt.setInt(++index, locked);
			stmt.setDouble(++index, user.getBalance());
			return stmt.executeUpdate() > 0;
		}  catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQLE State: " + sqle.getSQLState());
			System.err.println("Error code: " + sqle.getErrorCode());
		} 
		return false;
	}

	@Override
	public boolean deleteUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String getPasswordHash(User user) {
		int index = 0; 
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
			stmt.setString(++index, user.getUsername());
			stmt.setString(++index, user.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("HASH");
			}
		}  catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQLE State: " + sqle.getSQLState());
			System.err.println("Error code: " + sqle.getErrorCode());
		}
		return null;
	}
	
}
