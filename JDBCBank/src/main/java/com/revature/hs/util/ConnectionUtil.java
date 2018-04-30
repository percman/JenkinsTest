package com.revature.hs.util;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


// This class was taken directly from my notes of an example written by my instructor William.
// I only changed the package name and log statements
public class ConnectionUtil {
	private ConnectionUtil(){}
	private static final Logger logger = Logger.getLogger(ConnectionUtil.class);

	public static Connection getConnection() {
		Connection conn = null;
		Properties props = new Properties();
		try (InputStream in = new FileInputStream("src/main/resources/db.properties");){
			props.load(in);
			conn = DriverManager.getConnection(props.getProperty("jdbc.url"), props.getProperty("jdbc.username"),
					props.getProperty("jdbc.password"));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return conn;
	}

	public static void main(String[] args) {
		Connection conn = ConnectionUtil.getConnection();
		System.out.println(conn);
		try {
			conn.close();
		}catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
	}
}
