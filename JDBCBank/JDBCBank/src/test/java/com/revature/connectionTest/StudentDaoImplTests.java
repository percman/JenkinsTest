package com.revature.connectionTest;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.StudentDao;
import com.revature.dao.StudentDaoImpl;
import com.revature.users.Student;

public class StudentDaoImplTests {

	static Student student;
	
	private static StudentDao dao = StudentDaoImpl.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		student = new Student("sFirstname", "sLastname", "sUsername", "sPassword");
//		dao.truncateStudent();
	}

//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		dao.truncateStudent();
//	}

//	@Before
//	public void setUp() throws Exception {
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}

	@Test
	public void testInsert() {
		dao.truncateStudent();
		assertTrue(dao.insertStudent(student));
	}
	
	@Test
	public void testGetStudent() {
		assertTrue(dao.getStudent("sUsername").equals(student));
	}

}
