package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Admin;
import com.revature.util.ConnectionUtil;

public class AdminDaoImpl implements AdminDao{

	private static AdminDaoImpl instance;
	private AdminDaoImpl() {}
	public static AdminDaoImpl getInstance() {
		if(instance == null) {
			instance = new AdminDaoImpl();
		}
		return instance;
	}
	
	@Override
	public boolean insertAdmin(Admin admin) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL insert_person(?, ?, ?)}");
			stmt.setString(++index, admin.getUsername());
			stmt.setString(++index, admin.getPassword());
			stmt.setString(++index, ("Admin"));
			return stmt.executeUpdate()>0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean approvePerson(String username) {
		int index= 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("UPDATE person SET approved = 'T' "
					+ "WHERE username = ?");
			stmt.setString(++index, username);
			return stmt.executeUpdate()>0;			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean lockUser(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("UPDATE person SET locked = 'T' "
					+ "WHERE username = ?");
			stmt.setString(++index, username);
			return stmt.executeUpdate()>0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean unlockUser(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("UPDATE person SET locked = 'F' "
					+ "WHERE username = ?");
			stmt.setString(++index, username);
			return stmt.executeUpdate()>0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return false;
	}
	@Override
	public List<String> getUnapproved() {
		try(Connection conn = ConnectionUtil.getConnection()){
			List<String> unapproved = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT username FROM person WHERE "
					+ "approved = 'F' ORDER BY username");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				unapproved.add(rs.getString("username"));
			}
			return unapproved;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return null;
	}
	@Override
	public List<String> getLocked() {
		try(Connection conn = ConnectionUtil.getConnection()){
			List<String> locked = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT username, last_transaction(user_id) "
					+ "FROM person WHERE locked = 'T' AND role = 'User' ORDER BY username");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				locked.add(rs.getString("username")+ " " + rs.getTimestamp("last_transaction(user_id)"));
			}
			return locked;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return null;
	}

	
	@Override
	public String getPassword(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT password FROM person WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("password");
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return null;
	}
	
	@Override
	public boolean emptySet() {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM person");
			ResultSet rs = stmt.executeQuery();
			if(!rs.next())
				return true;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return false;
	}
	
	@Override
	public List<String> getUnlocked() {
		try(Connection conn = ConnectionUtil.getConnection()){
			List<String> unlocked = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT username, last_transaction(user_id) "
					+ "FROM person WHERE locked = 'F' AND role = 'User' ORDER BY username");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				unlocked.add(rs.getString("username") + " " + rs.getTimestamp("last_transaction(user_id)"));
			}
			return unlocked;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return null;
	}
}
