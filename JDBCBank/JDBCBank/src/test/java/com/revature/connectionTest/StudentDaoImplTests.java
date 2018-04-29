package com.revature.connectionTest;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.dao.StudentDao;
import com.revature.dao.StudentDaoImpl;
import com.revature.users.Person;
import com.revature.users.Student;

public class StudentDaoImplTests {

	static Student student;
	
	private static StudentDao dao = StudentDaoImpl.getInstance();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		student = new Student("sFirstname", "sLastname", "sUsername", "sPassword");
	}

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
	public void testInsert() {
		assertTrue(dao.insertStudent(student));
	}

}
