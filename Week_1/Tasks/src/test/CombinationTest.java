package test;

import static org.junit.Assert.*;
import main.Combination;
import org.junit.Test;

public class CombinationTest {

	@Test
	public void testTest() {
		assertEquals(Combination.test(0, 0), 1);
		assertEquals(Combination.test(0, 1), 0);
		assertEquals(Combination.test(1, 0), 1);
		assertEquals(Combination.test(4, 0), 1);
		assertEquals(Combination.test(4, 2), 6);
	}

}
