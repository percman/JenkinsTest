package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.singletons.AccountData;

public class Tests {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		AccountData ad = AccountData.getInstance();
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSerialization() {
		fail("Not yet implemented");
	}

}
