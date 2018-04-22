package com.revature.project0;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Project_0.ListOfUsers;
import Project_0.User;

public class ListOfUsersTest {

	static User user = new User();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		user.setUsername("Bob");
		ListOfUsers.addUser(user);
		user.setAccountBalance(10);
	}

	@Test
	public void test() {
		assertTrue(user.getUsername().equals("Bob"));
	}

	@Test
	public void test2() {
		assertTrue(ListOfUsers.getUserByUsername("Bob") == user);
	}
	
	@Test
	public void test3() {
		assertTrue(ListOfUsers.getNumberOfUsers() == 1);
	}
	
	@Test
	public void test4() {
		user.approve();
		assertTrue(user.isApproved() == 2);
	}
	
	@Test
	public void test5() {
		user.setPending();
		assertTrue(ListOfUsers.numberOfPending() == 1);
	}
	
	@Test
	public void test6() {
		user.deny();
		assertFalse(ListOfUsers.numberOfPending() == 1);
	}
	
	@Test
	public void test7() {
		user.promoteAdmin();
		assertTrue(user.getAdmin() == true);
	}
	
	@Test
	public void test8() {
		user.demoteAdmin();
		assertFalse(user.getAdmin());
	}
	
	@Test
	public void test9() {
		assertTrue(user.getAccountBalance().equals("10.00"));
	}
	
	@Test
	public void test10() {
		assertTrue(ListOfUsers.usernameExists("Bob"));
	}
}
