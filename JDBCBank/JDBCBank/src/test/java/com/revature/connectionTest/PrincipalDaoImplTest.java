package com.revature.connectionTest;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.PrincipalDao;
import com.revature.dao.PrincipalDaoImpl;
import com.revature.dao.TeacherDao;
import com.revature.dao.TeacherDaoImpl;
import com.revature.singletons.ConnectionUtil;
import com.revature.singletons.LogThis;
import com.revature.users.Principal;
import com.revature.users.Teacher;

public class PrincipalDaoImplTest {


	static Principal principal = null;
	static Teacher teacher1 = null;
	static Teacher teacher2 = null;
	static Teacher teacher3 = null;
	static Teacher teacher4 = null;
	static Teacher teacher5 = null;
	static List<Teacher> all= new ArrayList<>();
	static List<Teacher> approved = new ArrayList<>();
	static List<Teacher> unapproved = new ArrayList<>();
	static List<Teacher> locked = new ArrayList<>();
	static List<Teacher> unlocked = new ArrayList<>();
	
	private static TeacherDao tdao = TeacherDaoImpl.getInstance();
	private static PrincipalDao dao = PrincipalDaoImpl.getInstance();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		principal = new Principal("pFirstname", "pLastname", "pUsername", "pPassword");
		teacher1 = new Teacher("tFirstname", "tLastname", "tUsername1", "tPassword");
		teacher2 = new Teacher("tFirstname", "tLastname", "tUsername2", "tPassword");
		teacher3 = new Teacher("tFirstname", "tLastname", "tUsername3", "tPassword");
		teacher4 = new Teacher("tFirstname", "tLastname", "tUsername4", "tPassword");
		teacher5 = new Teacher("tFirstname", "tLastname", "tUsername5", "tPassword");
		if (tdao.getTeacher(teacher1.getUsername()) == null) {
			tdao.insertTeacher(teacher1);
		}
		if (tdao.getTeacher(teacher2.getUsername()) == null) {
			tdao.insertTeacher(teacher2);
		}
		if (tdao.getTeacher(teacher3.getUsername()) == null) {
			tdao.insertTeacher(teacher3);
		}
		if (tdao.getTeacher(teacher4.getUsername()) == null) {
			tdao.insertTeacher(teacher4);
		}
		if (tdao.getTeacher(teacher5.getUsername()) == null) {
			tdao.insertTeacher(teacher5);
		}
		if (dao.getPrincipal(principal.getUsername()) == null) {
			dao.insertPrincipal(principal);
		}
		all.add(teacher1);
		all.add(teacher2);
		all.add(teacher3);
		all.add(teacher4);
		all.add(teacher5);
		dao.lockTeacher(teacher1.getUsername());
		dao.lockTeacher(teacher2.getUsername());
		dao.lockTeacher(teacher3.getUsername());
		dao.unlockTeacher(teacher3.getUsername());
		locked.add(teacher1);
		locked.add(teacher2);
		unlocked.add(teacher3);
		unlocked.add(teacher4);
		unlocked.add(teacher5);
		dao.approveTeacher(teacher1.getUsername());
		dao.approveTeacher(teacher3.getUsername());
		dao.approveTeacher(teacher5.getUsername());
		approved.add(teacher1);
		unapproved.add(teacher2);
		approved.add(teacher3);
		unapproved.add(teacher4);
		approved.add(teacher5);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM principal ");
			stmt.execute();
			PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM teacher ");
			stmt2.execute();
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}
	}

//	@Before
//	public void setUp() throws Exception {
//		
//	}

//	@After
//	public void tearDown() throws Exception {
//	
//	}

	@Test
	public void testInsert() {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM principal WHERE p_username = 'pUsername' ");
			stmt2.execute();
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}

		assertTrue(dao.insertPrincipal(principal));
	}
	
	@Test
	public void testGetPrincipal() {
		Principal temp = dao.getPrincipal(principal.getUsername());
		assertTrue(temp.getPassword().equals(dao.getPasswordHash(principal)));
	}
	
	@Test
	public void testUpdatePrincipal() {
		assertTrue(dao.updatePrincipal(principal));
	}
	
	@Test
	public void testGetAllTeachers() {
		assertTrue(all.containsAll(dao.getAllTeachers()) && dao.getAllTeachers().containsAll(all));
	}
	
	@Test
	public void testGetUnapproved() {
		assertTrue(unapproved.containsAll(dao.getUnapprovedTeachers()) && dao.getUnapprovedTeachers().containsAll(unapproved));
	}
	
	@Test
	public void testGetUnlocked() {
		assertTrue(unlocked.containsAll(dao.getUnlockedTeachers()) && dao.getUnlockedTeachers().containsAll(unlocked));
	}
	
	@Test
	public void testGetLocked() {
		assertTrue(locked.containsAll(dao.getLockedTeachers()) && dao.getLockedTeachers().containsAll(locked));
	}

}
