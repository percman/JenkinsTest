package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class LoginDaoImpl implements LoginDao{

	private static final Logger logger = Logger.getLogger(LoginDaoImpl.class);
	private static LoginDaoImpl instance;
	private LoginDaoImpl() {}
	public static LoginDaoImpl getInstance() {
		if(instance == null) {
			instance = new LoginDaoImpl();
		}
		return instance;
	}
	
	@Override
	public String getPassword(Employee employee) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT password FROM employee WHERE username = ?");
			stmt.setString(++index, employee.getUsername());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("password");
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return null;
	}

	@Override
	public String getPasswordHash(Employee employee) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) AS HASH FROM dual");
			stmt.setString(++index, employee.getUsername());
			stmt.setString(++index, employee.getPassword());
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return rs.getString("HASH");
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
			logger.warn(sqle.getMessage());
		}
		return null;
	}

	
}
