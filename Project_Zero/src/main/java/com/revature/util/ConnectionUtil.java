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

	private static ConnectionUtil instance;
	
	private ConnectionUtil() {}
	
	public static Connection getConnection() {
		InputStream in = null;
		Properties props = new Properties();
		try {
			in = new FileInputStream("src/main/resources/db.properties");
			props.load(in);
			// Things we need to connect
			//1. URL
			//2. Username
			//3. Password
			//return DriverManager.getConnection("jdbc:oracle:thin:@jta-1804.c5cc3rtdkhbm.us-east-1.rds.amazonaws.com:1521:ORCL", "davidkim", "saiyandave11");
			return DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		}catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
	
	public static ConnectionUtil getInstance() {
		if (instance == null) {
			instance = new ConnectionUtil();
		}
		return instance;
	}

	/*public static void main(String[] args) {
		Connection conn = ConnectionUtil.getConnection();
		System.out.println(conn);
		try {
			conn.close();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
	}*/



}
