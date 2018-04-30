package com.revature.jdbc;

import static com.revature.logger.BankLogger.logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * All the implementations for the DAO methods
 * @author Jesse
 *
 */

public class TiCoDAOImplementation implements TiCoDAO {

	Connection conn = ConnectionObject.getInstance();
	
	@Override // Generates a new timestamp (TiCo)
	public boolean generateTimestamp(int id) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL insert_TiCo(?)}");
			stmt.setInt(++index, id);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\n\n\t\tTICO GENERATED!\n");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override // Gets the number of TiCos for a user
	public int getBalance(int id) {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(user_timestamp)AS balance FROM user_TiCo WHERE accountnumber "
					+ "= ? GROUP BY accountnumber");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("balance");
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return 0;
	}

	@Override // Get the total number of TiCos in the bank
	public int getTotalBalance() {
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(user_timestamp)AS balance FROM user_TiCo");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("balance");
			}
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return 0;
	}
}
