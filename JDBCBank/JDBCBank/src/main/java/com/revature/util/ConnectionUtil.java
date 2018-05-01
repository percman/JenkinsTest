package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	private ConnectionUtil() {}
	
	public static Connection getConnection() {		
		Properties props = new Properties();
		
		try (InputStream in = new FileInputStream("src/main/resources/db.properties")) {
			props.load(in);
			return DriverManager.getConnection(props.getProperty("jdbc.url"), 
					props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
			
		} catch (FileNotFoundException fnfe) {
			System.out.println(fnfe.getMessage());
		} catch (IOException ioe) {
			System.out.println(ioe.getMessage());
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQLE State: " + sqle.getSQLState());
			System.err.println("Error code: " + sqle.getErrorCode());
		} 
		return null;
	}
	
	public static void main(String[] args) {
		Connection conn = ConnectionUtil.getConnection();
		System.out.println(conn);
		try {
			conn.close();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQLE State: " + sqle.getSQLState());
			System.err.println("Error code: " + sqle.getErrorCode());
		} 
	}
	
}
