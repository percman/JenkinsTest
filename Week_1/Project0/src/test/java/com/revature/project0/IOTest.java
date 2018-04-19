package com.revature.project0;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

public class IOTest {

	
	@Test
	//test to see if the File IO class properly scans for approval
	public void approveScanTest() {
		MovieBarn.users.clear();
		User tom = new User("tom","password");
		FileIO.addUser(tom);
		tom.approved();
		FileIO.addUser(tom);
		User greg = new User("greg","password");
		FileIO.addUser(greg);
		Set<User> temp = FileIO.scanApproved();
		Iterator<User> itr = temp.iterator();
		assertEquals((User)itr.next(),greg);
	}
	@Test
	//test to see if the File IO class properly scans for locked users
	public void lockScanTest() {
		User tom = new User("tom","password");
		FileIO.addUser(tom);
		User greg = new User("greg","password");
		greg.setLocked(true);
		FileIO.addUser(greg);
		Set<User> temp = FileIO.scanLocked();
		Iterator<User> itr = temp.iterator();
		assertEquals((User)itr.next(),greg);
	}
	@Test
	//test to see if the File IO class properly scans for unlocked users
	public void unlockScanTest() {
		User tom = new User("tom","password");
		FileIO.addUser(tom);
		User greg = new User("greg","password");
		greg.setLocked(true);
		FileIO.addUser(greg);
		Set<User> temp = FileIO.scanUnlocked();
		Iterator<User> itr = temp.iterator();
		assertEquals((User)itr.next(),tom);
	}

}
