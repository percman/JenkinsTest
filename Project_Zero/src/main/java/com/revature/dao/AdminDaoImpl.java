package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

import com.revature.model.Admin;
import com.revature.util.ConnectionUtil;

public class AdminDaoImpl implements AdminDao{

	private static final Logger logger = Logger.getLogger(AdminDaoImpl.class);
	private static AdminDaoImpl instance;
	
	private AdminDaoImpl() {}
	
	public static AdminDaoImpl getInstance() {
		if (instance == null) {
			instance = new AdminDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<Admin> getAllAdmins() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getAdmin(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM admin WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Admin(rs.getString("username"), rs.getString("password"));
			}
			rs.close();
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertAdmin(Admin admin) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_admin(?, ?)}");
			stmt.setString(++index, admin.getUsername());
			stmt.setString(++index, admin.getPassword());
			stmt.close();
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getPasswordHash(Admin admin) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
