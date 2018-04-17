package test;

import static org.junit.Assert.*;
import main.EvenOdd;
import org.junit.Test;

public class EvenOddTest {

	@Test
	public void test() {
		assertEquals(EvenOdd.test(2), true);
		assertEquals(EvenOdd.test(3), false);
		assertEquals(EvenOdd.test(100), true);
		assertEquals(EvenOdd.test(101), false);
	}

}
