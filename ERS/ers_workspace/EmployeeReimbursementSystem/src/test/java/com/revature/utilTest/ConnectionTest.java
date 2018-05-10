package com.revature.utilTest;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.revature.util.ConnectionUtil;

public class ConnectionTest {

	@Test
	public void test() {
		Connection conn = ConnectionUtil.getConnection();
		try {
			conn.close();
			assertTrue(true);
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
			fail("Connection Unsuccessful"); 
		}
	}

}
