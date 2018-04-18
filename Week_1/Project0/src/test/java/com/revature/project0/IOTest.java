package com.revature.project0;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class IOTest {

	
	@Test
	//test to see if the File IO class properly scans for approval
	public void approveScanTest() {
		User tom = new User("tom","password");
		FileIO.addUser(tom);
		User greg = new User("greg","password");
		FileIO.addUser(greg);
		ArrayList<User> temp = FileIO.scanApproved();
		assertEquals(temp.get(0).username,greg);
	}
	@Test
	//test to see if the File IO class properly scans for locked users
	public void lockScanTest() {
		User tom = new User("tom","password");
		FileIO.addUser(tom);
		User greg = new User("greg","password");
		greg.setLocked(true);
		FileIO.addUser(greg);
		ArrayList<User> temp = FileIO.scanLocked();
		assertEquals(temp.get(0).username,greg);
	}
	@Test
	//test to see if the File IO class properly scans for unlocked users
	public void unlockScanTest() {
		User tom = new User("tom","password");
		FileIO.addUser(tom);
		User greg = new User("greg","password");
		greg.setLocked(true);
		FileIO.addUser(greg);
		ArrayList<User> temp = FileIO.scanUnlocked();
		assertEquals(temp.get(0).username,tom);
	}

}
