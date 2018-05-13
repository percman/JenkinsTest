package com.revature.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.logging.LogThis;

public class ConnectionUtil {


	private ConnectionUtil() {}

	public static Connection getConnection() {
//			Properties props = new Properties();
			try {
//				props.load(ConnectionUtil.class.getResourceAsStream("ers_db.properties"));
				Class.forName("oracle.jdbc.OracleDriver");
//				return DriverManager.getConnection(props.getProperty("jdbc.url"), 
//						props.getProperty("jdbc.username"),
//						props.getProperty("jdbc.password"));
				return DriverManager.getConnection("jdbc:oracle:thin:@jta-1804.cc9fuhhtz6xr.us-east-1.rds.amazonaws.com:1521:ORCL",
						"ers_user",
						"ers_password");
				
//			} catch (FileNotFoundException fnfe) {
//				fnfe.printStackTrace();
//			} catch (IOException ioe) {
//				ioe.printStackTrace();
			} catch (SQLException sqle) {
				System.err.println(sqle.getMessage());
				System.err.println("SQL State: " + sqle.getSQLState());
				System.err.println("Error Code: " + sqle.getErrorCode());
			} catch (ClassNotFoundException cnfe) {
				System.err.println(cnfe.getMessage());
			}
			return null;		
	}
	
	public static void main(String[] args) {
		
	}

}
