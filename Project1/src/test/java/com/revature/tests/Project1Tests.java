package com.revature.tests;



import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.util.ConnectionUtil;

public class Project1Tests {

	Connection conn=ConnectionUtil.getConnection();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConnection() {
		try (Connection conn = ConnectionUtil.getConnection()){
			Statement stmt=conn.createStatement();
			conn.close();
			assertTrue(stmt.isClosed());
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
	
	}

}
