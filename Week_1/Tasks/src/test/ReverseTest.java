package test;

import static org.junit.Assert.*;
import main.Reverse;
import org.junit.Test;

public class ReverseTest {

	@Test
	public void test() {
		assertEquals(Reverse.test("veggies"), "seiggev");
		assertEquals(Reverse.test("protein"), "nietorp");
		assertEquals(Reverse.test("banana over chocolate"), "etalocohc revo ananab");
	}

}
