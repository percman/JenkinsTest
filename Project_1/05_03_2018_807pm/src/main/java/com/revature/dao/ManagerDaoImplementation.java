package com.revature.dao;

import static com.revature.logger.ReimbursementLogger.logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.connection.ConnectionObject;
import com.revature.manager.Manager;

public class ManagerDaoImplementation implements ManagerDao {

	Connection conn = ConnectionObject.getInstance();
	
	@Override
	public Manager getManager(int id) {
		try {
			System.out.println("&");
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee e " + 
					"FULL OUTER JOIN employee_information ei ON e.employee_id = ei.employee_id " + 
					"FULL OUTER JOIN financial_manager f ON e.employee_id = f.employee_id " + 
					"WHERE f.f_manager_id = ?");
			stmt.setInt(1, id);
			System.out.println("*");
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			System.out.println("@");
			if (rs.next()) {
				System.out.println("!");
				return new Manager(rs.getInt("employee_id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getInt("f_manager_id"));
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		return null;
	}

	@Override
	public List<Manager> getAllManagers() {
		try {
			List<Manager> ms = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM employee e " + 
					"FULL OUTER JOIN employee_information ei ON e.employee_id = ei.employee_id " + 
					"FULL OUTER JOIN financial_manager f ON e.employee_id = f.employee_id "
					+ "WHERE f_manager_id > 0");
			ResultSet rs = stmt.executeQuery();
			System.out.println(rs);
			while (rs.next()) {
				ms.add(new Manager(rs.getInt("employee_id"), rs.getString("firstname"), rs.getString("lastname"),
						rs.getString("username"), rs.getString("password"), rs.getInt("f_manager_id")));
			}
			return ms;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		return null;
	}

	@Override
	public boolean insertManager(Manager manager) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL insert_manager(?,?)}");
			stmt.setString(++index, manager.getUsername());
			stmt.setString(++index, manager.getPassword());
			int rowsAffected = stmt.executeUpdate();
			System.out.println("%");
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("Error Code: " + sqle.getErrorCode());
			logger.warn("SQL State: " + sqle.getSQLState());
		}
		return false;
	}

	@Override
	public boolean modifyManager() {
		// TODO Auto-generated method stub
		return false;
	}

}
