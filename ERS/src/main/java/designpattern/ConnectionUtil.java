package designpattern;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnectionUtil {
	private static ConnectionUtil connectionUtil;
	private Connection connection;
	private Logger logger;
	
	private ConnectionUtil(Logger logger) {
		InputStream in = null;
		Properties props = new Properties();
		try {
			in = new FileInputStream("src/main/resources/db.properties");
			props.load(in);
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException sqle) {	
			logger.error("SQL State: " + sqle.getSQLState());
			logger.error("Error Code: " + sqle.getErrorCode());
			logger.error(sqle.getMessage());
			connection = null;
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe);
			connection = null;
		} catch (IOException ioe) {
			logger.error(ioe);
			connection = null;
		}
	}
	public static ConnectionUtil getConnectionUtil() {
		return connectionUtil;
	}
	public Connection getConnection() {
		return connection;
	}
	public Logger getLogger() {
		return logger;
	}
	public static ConnectionUtil getInstance(Logger logger) {
		if(connectionUtil == null) {
			connectionUtil = new ConnectionUtil(logger);
		}
		return connectionUtil;
	}	
}