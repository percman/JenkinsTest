package com.revature.jdbc;

import java.sql.Connection;

/**
 * Class that will ensure there is only 1 connection object for the duration of the program
 * @author Jesse
 *
 */

public class ConnectionObject {

	private static Connection connInstance;
	
	private ConnectionObject() {
		connInstance = ConnectionUtil.getConnection();
	}
	
	public static Connection getInstance() {
		if(connInstance == null) {
			new ConnectionObject();
		}
		return connInstance;
	}
}
