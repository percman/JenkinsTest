package com.revature.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	private ConnectionUtil() {
	}
	
	public static Connection getConnection() {
		Properties props = new Properties();
		try (InputStream in = ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
			props.load(in);
			Class.forName("oracle.jdbc.OracleDriver");
			return DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"), props.getProperty("jdbc.password"));
		} catch (FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		} catch (ClassNotFoundException cnfe) {
			System.err.println(cnfe.getMessage());
		}
		return null;
	}
}
