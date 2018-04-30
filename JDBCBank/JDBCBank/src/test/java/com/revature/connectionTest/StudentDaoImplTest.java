package com.revature.connectionTest;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.StudentDao;
import com.revature.dao.StudentDaoImpl;
import com.revature.users.Student;

public class StudentDaoImplTest {

	static Student student;

	private static StudentDao dao = StudentDaoImpl.getInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		student = new Student("sFirstname", "sLastname", "sUsername", "sPassword");
		// dao.truncateStudent();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		dao.deleteStudent(student.getUsername());
	}

	@Before
	public void setUp() throws Exception {
		if (dao.getStudent(student.getUsername()) == null) {
			dao.insertStudent(student);
		}
	}
	//
	// @After
	// public void tearDown() throws Exception {
	// }

	@Test
	public void testInsert() {
		dao.deleteStudent(student.getUsername());
		assertTrue(dao.insertStudent(student));
	}

	@Test
	public void testGetStudent() {
		assertTrue(dao.getStudent("sUsername").equals(student));
	}

	@Test
	public void testUpdateStudent() {
		assertTrue(dao.updateStudent(student));
	}

	@Test
	public void testGetHashPassword() {
		Student temp = dao.getStudent(student.getUsername());
		assertTrue(temp.getPassword().equals(dao.getPasswordHash(student)));

	}

	@Test
	public void testDeleteStudent() {
		assertTrue(dao.deleteStudent(student.getUsername()));
	}
	
	@Test
	public void testIfApproved() {
		assertTrue(dao.getApproved(student.getUsername()) == 0);
	}
	
	@Test
	public void testIfLocked() {
		assertTrue(dao.getLocked(student.getUsername()) == 0);
	}
}
