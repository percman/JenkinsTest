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

import com.revature.dao.StudentDao;
import com.revature.dao.StudentDaoImpl;
import com.revature.dao.TeacherDao;
import com.revature.dao.TeacherDaoImpl;
import com.revature.singletons.ConnectionUtil;
import com.revature.singletons.LogThis;
import com.revature.users.Student;
import com.revature.users.Teacher;

public class TeacherDaoImplTest {

	static Teacher teacher = null;
	static Student student1 = null;
	static Student student2 = null;
	static Student student3 = null;
	static Student student4 = null;
	static Student student5 = null;
	static List<Student> all= new ArrayList<>();
	static List<Student> approved = new ArrayList<>();
	static List<Student> unapproved = new ArrayList<>();
	static List<Student> locked = new ArrayList<>();
	static List<Student> unlocked = new ArrayList<>();
	
	private static TeacherDao dao = TeacherDaoImpl.getInstance();
	private static StudentDao sdao = StudentDaoImpl.getInstance();
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		teacher = new Teacher("tFirstname", "tLastname", "tUsername", "tPassword");
		student1 = new Student("sFirstname", "sLastname", "sUsername1", "sPassword");
		student2 = new Student("sFirstname", "sLastname", "sUsername2", "sPassword");
		student3 = new Student("sFirstname", "sLastname", "sUsername3", "sPassword");
		student4 = new Student("sFirstname", "sLastname", "sUsername4", "sPassword");
		student5 = new Student("sFirstname", "sLastname", "sUsername5", "sPassword");
		if (sdao.getStudent(student1.getUsername()) == null) {
			sdao.insertStudent(student1);
		}
		if (sdao.getStudent(student2.getUsername()) == null) {
			sdao.insertStudent(student2);
		}
		if (sdao.getStudent(student3.getUsername()) == null) {
			sdao.insertStudent(student3);
		}
		if (sdao.getStudent(student4.getUsername()) == null) {
			sdao.insertStudent(student4);
		}
		if (sdao.getStudent(student5.getUsername()) == null) {
			sdao.insertStudent(student5);
		}
		if (dao.getTeacher(teacher.getUsername()) == null) {
			dao.insertTeacher(teacher);
		}
		all.add(student1);
		all.add(student2);
		all.add(student3);
		all.add(student4);
		all.add(student5);
		dao.lockStudent(student1.getUsername());
		dao.lockStudent(student2.getUsername());
		dao.lockStudent(student3.getUsername());
		dao.unlockStudent(student3.getUsername());
		locked.add(student1);
		locked.add(student2);
		unlocked.add(student3);
		unlocked.add(student4);
		unlocked.add(student5);
		dao.approveStudent(student1.getUsername());
		dao.approveStudent(student3.getUsername());
		dao.approveStudent(student5.getUsername());
		approved.add(student1);
		unapproved.add(student2);
		approved.add(student3);
		unapproved.add(student4);
		approved.add(student5);

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM student ");
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
			PreparedStatement stmt2 = conn.prepareStatement("DELETE FROM teacher WHERE t_username = 'tUsername' ");
			stmt2.execute();
		} catch (SQLException sqle) {
			LogThis.warn(sqle.getMessage());
			LogThis.warn("SQL state: " + sqle.getSQLState());
			LogThis.warn("Error Code: " + sqle.getErrorCode());
		}

		assertTrue(dao.insertTeacher(teacher));
	}
	
	@Test
	public void testGetTeacher() {
		Teacher temp = dao.getTeacher(teacher.getUsername());
		assertTrue(temp.getPassword().equals(dao.getPasswordHash(teacher)));
	}
	
	@Test
	public void testUpdateTeacher() {
		assertTrue(dao.updateTeacher(teacher));
	}
	
	@Test
	public void testGetAllStudents() {
		assertTrue(all.containsAll(dao.getAllStudents()) && dao.getAllStudents().containsAll(all));
	}
	
	@Test
	public void testGetUnapproved() {
		assertTrue(unapproved.containsAll(dao.getUnapprovedStudents()) && dao.getUnapprovedStudents().containsAll(unapproved));
	}
	
	@Test
	public void testGetUnlocked() {
		assertTrue(unlocked.containsAll(dao.getUnlockedStudents()) && dao.getUnlockedStudents().containsAll(unlocked));
	}
	
	@Test
	public void testGetLocked() {
		assertTrue(locked.containsAll(dao.getLockedStudents()) && dao.getLockedStudents().containsAll(locked));
	}
	
	@Test
	public void testIfApproved() {
		assertTrue(dao.getApproved(teacher.getUsername()) == 0);
	}
	
	@Test
	public void testIfLocked() {
		assertTrue(dao.getLocked(teacher.getUsername()) == 0);
	}

}
