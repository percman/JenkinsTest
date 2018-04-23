package com.revature.project_0;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LoginTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("Starting Test");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("Finished test");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("Starting new test");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test Finished");
	}

	@Test
	public void test1() {
		File admin = new File("src/main/resources/andy.txt");
		Account andy = SerializationOfNewAccount.deserializeAccount(admin);
		assertTrue(Login.UserOrAdmin(andy));
	}
	@Test
	public void test2() {
		File user = new File("src/main/resources/example.txt");
		Account example = SerializationOfNewAccount.deserializeUser(user);
		assertFalse(Login.UserOrAdmin(example));
	}
	@Test
	public void test3() {
		assertTrue(Login.uNameExist("andy"));
	}
	
	@Test
	public void test4() {
		assertFalse(Login.uNameExist("asdf0987"));
	}
	
	@Test
	public void test5() {
		assertTrue(User.isStringInteger("123"));
	}
	@Test
	public void test6() {
		assertFalse(User.isStringInteger("12a"));
	}

}
