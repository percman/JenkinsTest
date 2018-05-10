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
	private Logger logger;
	
	private ConnectionUtil(Logger logger) {
		this.logger = logger;
	}
	public Logger getLogger() {
		return logger;
	}
	public static Connection connect(Logger logger) {
		InputStream in = null;
		Properties props = new Properties();
		try {
			in = new FileInputStream("src/main/resources/db.properties");
			props.load(in);
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException sqle) {	
			logger.error("SQL State: " + sqle.getSQLState());
			logger.error("Error Code: " + sqle.getErrorCode());
			logger.error(sqle.getMessage());
			return null;
		} catch (FileNotFoundException fnfe) {
			logger.error(fnfe);
			return null;
		} catch (IOException ioe) {
			logger.error(ioe);
			return null;
		}
	}	
}