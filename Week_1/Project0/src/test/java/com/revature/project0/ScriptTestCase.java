package com.revature.project0;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ScriptTestCase {
	private final ByteArrayOutputStream printOut = new ByteArrayOutputStream();
	private final ByteArrayOutputStream printErr = new ByteArrayOutputStream();

	@Before
	public void startStreams() {
	    System.setOut(new PrintStream(printOut));
	    System.setErr(new PrintStream(printErr));
	    Record.loadData();
	}

	@After
	public void restoreStreams() {
	    System.setOut(System.out);
	    System.setErr(System.err);
	}
	
	
	@Test(expected = UserNotFoundException.class)
	public void userNotFoundExceptionTest() throws UserNotFoundException {
		
		FileIO.getUser("bill");
	}
	@Test
	public void getUserTest() throws UserNotFoundException {	
		Assert.assertTrue(Record.users.contains(FileIO.getUser("greg")));
	}
	@Test
	public void getAdminTest() throws UserNotFoundException {
		Assert.assertTrue(Record.admins.contains(FileIO.getAdmin("adam")));
	}
	@Test(expected = UserTypeNotFoundException.class)
	public void typeNotFoundExceptionTest() throws UserTypeNotFoundException  {
	NewUserFactory.getUser("notAType", "stan", "password");
	}
	@Test
	public void addUserTest() throws UserNotFoundException{
		User stan = new User("stan" , "password");
		FileIO.addNewUser(stan);
		Assert.assertTrue(Record.users.contains(FileIO.getUser("stan")));
	}
	@Test
	public void addAdminTest() throws UserNotFoundException{
		Admin tom = new Admin("tom" , "password");
		FileIO.addAdmin(tom);
		Assert.assertTrue(Record.admins.contains(FileIO.getAdmin("tom")));
	}
	@Test
	public void setLockedTest() throws UserNotFoundException{
		Admin tom = new Admin("tom" , "password");
		tom.setLocked(true, FileIO.getUser("greg"));
		Assert.assertTrue(FileIO.getUser("greg").isUserLocked());
		tom.setLocked(false, FileIO.getUser("greg"));
		Assert.assertFalse(FileIO.getUser("greg").isUserLocked());
	}
	@Test
	public void setApprovedTest(){
		User stan = new User("stan" , "password");
		FileIO.addNewUser(stan);
		Assert.assertTrue(stan.isUserUnapproved());
	}
	@Test	
	public void scanForLockedTest() throws UserNotFoundException{
		Admin tom = new Admin("tom" , "password");
		tom.setLocked(true, FileIO.getUser("greg"));		
		FileIO.scanLocked();
		Assert.assertEquals("User has been locked.greg\r\n", printOut.toString());
	}
	@Test
	public void scanForUnlockedTest() throws UserNotFoundException{
		FileIO.scanUnlocked();
		Assert.assertEquals("greg\r\n", printOut.toString());
	}
	@Test
	public void scanForApprovedTest(){
		User stan = new User("stan" , "password");
		FileIO.addNewUser(stan);
		FileIO.scanApproved();
		Assert.assertEquals("stan\r\n", printOut.toString());
	}

}
