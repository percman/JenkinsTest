package com.andrewsrahn.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.andrewsrahn.main.Administrator;
import com.andrewsrahn.main.Bank;

public class BankTest {
	Bank b;
	
	@Before
	public void setUp() {
		b = new Bank();
	}

	@Test
	public void testGetAdministrator() {
		Administrator a = new Administrator("andrew", "andrewsrahn@gmail.com", "password");
		Administrator c = b.getAdministrator("andrew");
		
	}

	@Test
	public void testGetUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateAdmin() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateUser() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUsers() {
		fail("Not yet implemented");
	}

}
