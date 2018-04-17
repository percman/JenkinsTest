package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Prime;

public class PrimeTest {
	@Test
	public void testTest() {
		assertEquals("0", false, Prime.test(0));
		assertEquals("1", false, Prime.test(1));
		assertEquals("2", true, Prime.test(2));
		assertEquals("3", true, Prime.test(3));
		assertEquals("4", false, Prime.test(4));
		assertEquals("5", true, Prime.test(5));
		assertEquals("13", true, Prime.test(13));
		assertEquals("17", true, Prime.test(17));
		assertEquals("18", false, Prime.test(18));
	}
}
