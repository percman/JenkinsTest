package test;

import static org.junit.Assert.*;
import org.junit.Test;

import main.Factorial;

public class FactorialTest {
	@Test
	public void testIterative() {
		assertEquals("0!", 1, Factorial.iterative(0));
		assertEquals("1!", 1, Factorial.iterative(1));
		assertEquals("2!", 2, Factorial.iterative(2));
		assertEquals("3!", 6, Factorial.iterative(3));
	}

	@Test
	public void testRecursive() {
		assertEquals("0!", 1, Factorial.recursive(1));
		assertEquals("1!", 1, Factorial.recursive(1));
		assertEquals("2!", 2, Factorial.recursive(2));
		assertEquals("3!", 6, Factorial.recursive(3));
	}
}