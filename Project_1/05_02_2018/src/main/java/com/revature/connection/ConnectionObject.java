package com.revature.connection;

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
	
	public static void main(String[] args) {
		
		Connection conn = ConnectionObject.getInstance();
		System.out.println(conn);
	}
}
