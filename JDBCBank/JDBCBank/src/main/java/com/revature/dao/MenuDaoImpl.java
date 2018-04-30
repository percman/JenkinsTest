package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.singletons.ConnectionUtil;
import com.revature.singletons.LogThis;

public class MenuDaoImpl implements MenuDao{

	private static MenuDaoImpl instance;
	
	private MenuDaoImpl() {}
	
	public static MenuDaoImpl getInstance() {
		if (instance == null) {
			instance = new MenuDaoImpl();
		}
		return instance;
	}

	@Override
	public boolean usernameTaken(String newUsername) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select COUNT(*) AS answer FROM username WHERE username = ? ");
			stmt.setString(++index, newUsername);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("answer") > 0;
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean insertUsername(String username, String type) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_username (?, ?) }");
			stmt.setString(++index, username);
			stmt.setString(++index, type);
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public int principalExists() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select get_principal AS num_o_principal FROM dual ");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("num_o_principal");
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return 0;
	}

	@Override
	public String getType(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("Select get_type(?) AS ye_ol_user_type FROM dual ");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("ye_ol_user_type");
			}
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
	
	
}
