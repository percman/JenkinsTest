package com.revature.hs;
import com.revature.hs.user.Admin;
import com.revature.hs.user.User;
import org.junit.Test;

import static com.revature.hs.user.dao.UserService.addUser;
import static org.junit.Assert.*;


public class DuplicateAccountTest {

	//NOTE, to maintain good data practices, there's currently no user delete function for cleanup.
	@Test
	public void createDuplicates () {
		User usr = new Admin("test", "testpass", "admin");
		try {
			addUser(usr);
		} catch (com.revature.hs.user.exceptions.DuplicateUserNameException e) {
			fail();
		}

		try {
			addUser(usr);
		} catch (com.revature.hs.user.exceptions.DuplicateUserNameException e) {
			assertTrue(true);
		}
	}
}
