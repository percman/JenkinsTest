package com.revature.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Application {

	public static void main(String[] args) {
		
	}
	
	public static void selectAllPokemon() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM pokemon");
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt("id") + ": " + rs.getString("name") 
					+ " (" + rs.getString("type") + ")");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
	}
	
	public static boolean insertPokemon(int id, String name, String type) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO pokemon VALUES (?, ?, ?)");
			stmt.setInt(++index, id);
			stmt.setString(++index, name);
			stmt.setString(++index, type);
			int rowsAffected = stmt.executeUpdate();
			System.out.println("Was the pokemon successfully inserted? " + (rowsAffected > 0));
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
	public static void selectFromCustomer() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = ConnectionUtil.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT firstname, customerId, lastname FROM customer");
			while(rs.next()) {
				System.out.println(rs.getInt(2) + ": " + rs.getString("lastname") + ", " + rs.getString(1));
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		} finally {
			try {
				rs.close();
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			try {
				stmt.close();
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}
			try {
				conn.close();
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			}

		}
	}
}
