package com.revature.connectionTest;

import java.sql.SQLException;

import static org.junit.Assert.*;
import org.junit.Test;

import com.revature.singletons.ConnectionUtil;


public class ConnectionTest {

	static java.sql.Connection conn;
	
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//	}
//
//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void test() {
		conn = ConnectionUtil.getConnection();
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
