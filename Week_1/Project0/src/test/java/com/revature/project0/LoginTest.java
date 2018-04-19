package com.revature.project0;

import static org.junit.Assert.*;

import org.junit.Test;

public class LoginTest {

	@Test
	//test to see if a user is found in the list correctly
	public void usernameTest() {
		User user = new User("user","password");
		FileIO.addNewUser(user);
		assertTrue(Login.userExists("user"));
	}
	@Test
	//test to see if a false positive is given by the method
	public void notAUserTest() {
		User user = new User("user","password");
		FileIO.addNewUser(user);
		assertFalse(Login.userExists("fakeUser"));
	}
	@Test
	//test to see if the password is found and matched correctly 
	public void passwordTest() {
		User user = new User("user","password");
		FileIO.addNewUser(user);
		assertTrue(Login.checkPassword("password","user"));
	}
	@Test
	//test to see if the password can be matched incorrectly
	public void wrongPasswordTest() {
		User user = new User("user","password");
		FileIO.addNewUser(user);
		assertFalse(Login.checkPassword("notPassword","user"));
	}

}
