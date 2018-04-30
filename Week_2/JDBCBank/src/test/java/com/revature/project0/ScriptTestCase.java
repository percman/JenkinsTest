package com.revature.project0;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.dao.users.AdminService;
import com.revature.dao.users.UserService;
import com.revature.data.FileIO;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UserTypeNotFoundException;
import com.revature.users.Admin;
import com.revature.users.NewUserFactory;
import com.revature.users.User;

public class ScriptTestCase {
	private final ByteArrayOutputStream printOut = new ByteArrayOutputStream();
	private final ByteArrayOutputStream printErr = new ByteArrayOutputStream();

	@Before
	public void startStreams() {
	    System.setOut(new PrintStream(printOut));
	    System.setErr(new PrintStream(printErr));
	}

	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}
	
	
	@Test(expected = UserNotFoundException.class)
	public void userNotFoundExceptionTest() throws UserNotFoundException {
		UserService.getUser("john");
	}
	@Test(expected = UserTypeNotFoundException.class)
	public void typeNotFoundExceptionTest() throws UserTypeNotFoundException  {
	NewUserFactory.getUser("notAType", "stan", "password");
	}
	@Test
	@Ignore
	public void addUserTest() throws UserNotFoundException{
		User stan = new User("stan" , "password");
		UserService.addUser(stan);
		Assert.assertEquals(UserService.getUser("stan"),stan);
	}
	@Test
	@Ignore
	public void addAdminTest() throws UserNotFoundException{
		Admin tom = new Admin("tom" , "password");
		AdminService.addAdmin(tom);
		Assert.assertEquals(AdminService.getAdmin("stan"),tom);
	}
	@Test
	public void setLockedTest() throws UserNotFoundException{
		UserService.lockUser(UserService.getUser("megan"));
		Assert.assertTrue(UserService.isUserLocked(UserService.getUser("megan")));
		UserService.unlockUser(UserService.getUser("megan"));
		Assert.assertFalse(UserService.isUserLocked(UserService.getUser("megan")));
	}
	@Test
	@Ignore
	public void setApprovedTest() throws UserNotFoundException{
		User jimmy = new User("jimmy" , "password");
		UserService.addUser(jimmy);
		Assert.assertTrue(UserService.isUserUnapproved(jimmy));
		UserService.approveUser(jimmy);
		Assert.assertFalse(UserService.isUserUnapproved(jimmy));
	}
	@Test	
	public void scanForLockedTest() throws UserNotFoundException{
		UserService.lockUser(UserService.getUser("megan"));		
		FileIO.scanLocked();
		Assert.assertEquals("megan", printOut.toString());
	}
	@Test
	public void scanForUnlockedTest() throws UserNotFoundException{
		FileIO.scanUnlocked();
		Assert.assertEquals("megan\njimmy\nstan\nhank", printOut.toString());
	}
	@Test
	public void scanForApprovedTest(){
		User stan = new User("stan" , "password");
		//UserService.addUser(new User("hank","password"));
		FileIO.scanApproved();
		Assert.assertEquals("megan" + "\n" + "jimmy" + "\n", printOut.toString());
	}
   @Test
	public void getUsersTest()  {
	List<User> user = UserService.getUsers();
	Assert.assertEquals(user.get(0).getUsername(),"megan");
	Assert.assertEquals(user.get(1).getUsername(),"jimmy");
	Assert.assertEquals(user.get(2).getUsername(),"stan");
	Assert.assertEquals(user.get(3).getUsername(),"hank");
	}
	@Test
	public void getAdminsTest()  {
	List<Admin> admin = AdminService.getAdmins();
	Assert.assertEquals(admin.get(0).getUsername(),"dave");
	Assert.assertEquals(admin.get(1).getUsername(),"tom");
	}
}
