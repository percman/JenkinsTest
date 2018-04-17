package test;

import static org.junit.Assert.*;
import org.junit.Test;

import main.Fibonacci;

public class FibonacciTest {
	@Test
	public void testIterative() {
		assertEquals("0 index", 1, Fibonacci.iterative(0));
		assertEquals("1 index", 1, Fibonacci.iterative(1));
		assertEquals("2 index", 2, Fibonacci.iterative(2));
		assertEquals("3 index", 3, Fibonacci.iterative(3));
		assertEquals("4 index", 5, Fibonacci.iterative(4));
	}

	@Test
	public void testRecursive() {
		assertEquals("0 index", 1, Fibonacci.recursive(0));
		assertEquals("1 index", 1, Fibonacci.recursive(1));
		assertEquals("2 index", 2, Fibonacci.recursive(2));
		assertEquals("3 index", 3, Fibonacci.recursive(3));
		assertEquals("4 index", 5, Fibonacci.recursive(4));
	}
}
