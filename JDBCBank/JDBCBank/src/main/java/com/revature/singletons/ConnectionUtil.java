package com.revature.singletons;

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
						props.getProperty("jdbc.username"),
						props.getProperty("jdbc.password"));
				
			} catch (FileNotFoundException fnfe) {
				LogThis.warn("FileNotFoundException in ConnectionUtil " + fnfe.getMessage());
			} catch (IOException ioe) {
				LogThis.warn("IOException in ConnectionUtil " + ioe.getMessage());
			} catch (SQLException sqle) {
				LogThis.warn(sqle.getMessage());
				LogThis.warn("SQL State: " + sqle.getSQLState());
				LogThis.warn("Error Code: " + sqle.getErrorCode());
			}
		return null;
	}

}
