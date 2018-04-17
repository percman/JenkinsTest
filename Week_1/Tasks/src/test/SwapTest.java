package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.Swap;

public class SwapTest {
	List<Integer> before;
	List<Integer> after;

	@Before
	public void setUp() throws Exception {
		before = new ArrayList<Integer>();
		before.add(0, new Integer(0));
		before.add(1, new Integer(1));
		
		after = new ArrayList<Integer>();
		after.add(0, new Integer(1));
		after.add(1, new Integer(0));
	}

	@Test
	public void testTest() {
		assertEquals(Swap.test(before), after);
	}

}
