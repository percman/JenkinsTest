package com.revature.hs.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


// This class was taken directly from my notes of an example written by my instructor William.
// I only changed the package name.
public class ConnectionUtil {
	private ConnectionUtil(){}

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
			System.err.println(e.getMessage());
			System.err.println("SQL STATE: " + e.getSQLState());
			System.err.println("ERROR CODE: " + e.getErrorCode());
		}
		return conn;
	}

	public static void main(String[] args) {
		Connection conn = ConnectionUtil.getConnection();
		System.out.println(conn);
		try {
			conn.close();
		}catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL STATE: " + e.getSQLState());
			System.err.println("ERROR CODE: " + e.getErrorCode());
		}
	}
}
