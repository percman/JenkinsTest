package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Palindrome;

public class PalindromeTest {
	@Test
	public void testTest() {
		assertEquals(Palindrome.test("A man, a plan, a canal, Panama!"), true);
		assertEquals(Palindrome.test("Was it a car or a cat I saw?"), true);
		assertEquals(Palindrome.test("madam"), true);
		assertEquals(Palindrome.test("racecar"), true);
		assertEquals(Palindrome.test("i like pie"), false);
	}
}
