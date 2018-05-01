package com.revature.project_0;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.CredentialsService;

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
		String name = "andy"; String pword = "boss";
		assertNotNull(CredentialsService.login(new Account(name,pword)));
	}

	
	@Test
	public void test2() {
		assertTrue(User.isInputValid("123"));
	}
	
	@Test
	public void test3() {
		assertTrue(CredentialsService.Availability("andy"));
	}
	
	@Test
	public void test4() {
		assertFalse(CredentialsService.Availability("asfevp"));
	}
	

}
