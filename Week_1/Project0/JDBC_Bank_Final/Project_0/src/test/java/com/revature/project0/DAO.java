package com.revature.project0;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.jdbc.AccountService;
import com.revature.user.User;

public class DAO {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		User user  =  new User(6, "Carl", "Sagan", "Carl", "penguins",
				0,  0, false, false);
		AccountService.insertUser(user);
	}

	@Test
	public void test1() {
		assertTrue(AccountService.getUser(6).getUsername().equals("Carl"));
	}
	
	@Test
	public void test2() {
		assertTrue(AccountService.getUser(6).getFirstName().equals("Carl"));
	}
	
	@Test
	public void test3() {
		assertTrue(AccountService.getUser(6).getLastName().equals("Sagan"));
	}
	
	@Test
	public void test4() {
		assertTrue(AccountService.getUser(6).getAccountNumber() == 6);
	}
	
	@Test
	public void test5() {
		assertTrue(AccountService.getUser("Carl").getUsername().equals("Carl"));
	}
	
	@Test
	public void test6() {
		assertFalse(AccountService.getUser(6).getPassword().equals("penguins"));
	}

}
