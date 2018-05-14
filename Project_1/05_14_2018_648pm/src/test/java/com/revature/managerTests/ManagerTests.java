package com.revature.managerTests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.manager.Manager;

public class ManagerTests {

	Manager manager = new Manager();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test1() {
		manager.setManager_id(7);
		assertTrue(manager.getId() != manager.getManager_id());
	}
	
	@Test
	public void test2() {
		
	}

}
