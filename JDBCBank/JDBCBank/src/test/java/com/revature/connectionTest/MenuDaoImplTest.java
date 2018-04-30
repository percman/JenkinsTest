package com.revature.connectionTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.MenuDao;
import com.revature.dao.MenuDaoImpl;
import com.revature.singletons.ConnectionUtil;
import com.revature.singletons.LogThis;

public class MenuDaoImplTest {

	private static MenuDao dao = MenuDaoImpl.getInstance();
	
	static String username1;
	static String username2;
	static String username3;
	static String username4;
	static String username5;
	
	static String type1;
	static String type2;
	static String type3;
	static String type4;
	static String type5;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		username1 = "username1";
		username2 = "username1";
		username3 = "username3";
		username4 = "username4";
		username5 = "username5";
		
		type1 = "student";
		type2 = "student";
		type3 = "teacher";
		type4 = "teacher";
		type5 = "principal";
		
		assertTrue(dao.insertUsername(username1, type1));


	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM username ");
			stmt.execute();
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}

	}

//	@Before
//	public void setUp() throws Exception {
//	}

//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void testInsert() {
		assertTrue(dao.insertUsername(username3, type3));
		assertTrue(dao.insertUsername(username4, type4));
	}
		
	@Test
	public void testIsTaken() {
		assertTrue(dao.usernameTaken(username2));
	}
	
	@Test
	public void testNotTaken() {
		assertFalse(dao.usernameTaken(username5));
	}
	
	@Test
	public void testPrincipalExists() {
		assertTrue(dao.principalExists() == 0);
	}
	
	@Test
	public void testGetType() {
		assertEquals(type1, dao.getType(username1));
	}

}
