package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import com.revature.model.Info;
import com.revature.util.ConnectionUtilSingleton;

import org.apache.log4j.Logger;

public class InfoDaoImpl implements InfoDao{

	private static final Logger logger = Logger.getLogger(InfoDaoImpl.class);
	private static InfoDaoImpl instance;
	
	private InfoDaoImpl() {}
	
	public static InfoDaoImpl getInstance() {
		if (instance == null) {
			instance = new InfoDaoImpl();
		}
		return instance;
	}
	
	@Override
	public List<Info> getAllInfo() {
		List<Info> infoList = new ArrayList<>();
		try (Connection conn = ConnectionUtilSingleton.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM info");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Info info = new Info(rs.getInt("employeeId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("DOB"), rs.getInt("salary"));
				infoList.add(info);
			}
			return infoList;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}	
		return null;
	}

	@Override
	public Info getInfo(int employeeId) {
		int index = 0;
		try (Connection conn = ConnectionUtilSingleton.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM info WHERE employeeId = ?");
			stmt.setInt(++index, employeeId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Info(rs.getInt("employeeId"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("DOB"), rs.getInt("salary"));
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertInfo(Info info) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateInfo(Info info) {
		int index = 0, result;
		
		try (Connection conn = ConnectionUtilSingleton.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL update_info(?, ?, ?, ?, ?)}");
			stmt.setInt(++index, info.getEmployeeId());
			stmt.setString(++index, info.getFirstName());
			stmt.setString(++index, info.getLastName());
			stmt.setString(++index, info.getDOB());
			stmt.setInt(++index, info.getSalary());
			result = stmt.executeUpdate();
			stmt.close();
			return result > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

}
