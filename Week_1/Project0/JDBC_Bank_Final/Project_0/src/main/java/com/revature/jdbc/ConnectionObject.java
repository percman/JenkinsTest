package com.revature.jdbc;

import java.sql.Connection;

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
