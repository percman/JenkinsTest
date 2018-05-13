package design;

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
		Properties props = new Properties();
		try (InputStream in = ConnectionUtil.class.getClassLoader().getResourceAsStream("db.properties")) {
			props.load(in);
			Class.forName("oracle.jdbc.OracleDriver");
			String url = props.getProperty("jdbc.url");
			String username = props.getProperty("jdbc.username");
			String password = props.getProperty("jdbc.password");
			return DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			logger.error(e.getErrorCode());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}	
}