package com.revature.connection;

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
	private static final Logger logger = Logger.getLogger(ConnectionUtil.class);
	private ConnectionUtil() {
		
	}
	public static Connection getConnection() {
		String url ="jdbc:oracle:thin:@project1.cwcyxr5bvd9v.us-east-1.rds.amazonaws.com:1521:ORCL";
		String username="DLani";
		String password="password";
		InputStream in = null;
		Properties props = new Properties();
		
		try {
			//in = new FileInputStream("src/main/resources/db.properties");
			//props.load(in);
			//Things needed to connect url,password,username
			return DriverManager.getConnection(url,username,password);
		}catch(SQLException sqle) {
			System.out.println(sqle.getMessage());
			logger.error(sqle.getMessage(), sqle);
			logger.error(sqle.getSQLState(),sqle);
			logger.error(sqle.getErrorCode(),sqle);
		} /*catch (FileNotFoundException fnfe) {
			logger.error(fnfe.getMessage(),fnfe);
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(),ioe);
		}*/
		return null;
		}


}

