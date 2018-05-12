package com.revature.reversingStrings;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ReverseAStringTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("This @BeforeClass will be run once, before any other method in this class");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("This @AfterClass will be run once, after all other methods in the class");
	}

	@Before
	public void setUp() throws Exception {
		System.out.println("This @Before will be run once before each @Test method");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("This @After will be run once after each @Test method");
	}

	@Test
	public void test() {
		assertTrue("dlroW olleH".equals(ReverseAString.reverse("Hello World")));
	}
	
	@Test
	@Ignore
	public void test1() {
		assertTrue("mailliW".equals(ReverseAString.reverse("William")));
	}

}
