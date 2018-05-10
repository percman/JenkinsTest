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
			Properties props = new Properties();
			try (InputStream in = new FileInputStream("../../../../db_properties/ers_db.properties")) {
				props.load(in);
				return DriverManager.getConnection(props.getProperty("jdbc.url"), 
						props.getProperty("jdbc.username"),
						props.getProperty("jdbc.password"));
				
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
				
			} catch (IOException ioe) {
				ioe.printStackTrace();
				
			} catch (SQLException sqle) {
				LogThis.warn(sqle.getMessage());
				LogThis.warn("SQL State: " + sqle.getSQLState());
				LogThis.warn("Error Code: " + sqle.getErrorCode());
			}
		return null;
	}

}
