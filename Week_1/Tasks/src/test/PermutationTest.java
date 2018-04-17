package test;

import static org.junit.Assert.*;

import org.junit.Test;

import main.Permutation;

public class PermutationTest {

	@Test
	public void testTest() {
		assertEquals(Permutation.test(0, 0), 1);
		assertEquals(Permutation.test(4, 2), 12);
		assertEquals(Permutation.test(8, 2), 56);	
	}

}
