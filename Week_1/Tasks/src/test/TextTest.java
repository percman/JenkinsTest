package test;

import static org.junit.Assert.*;
import org.junit.Test;
import main.Text;

public class TextTest {
	@Test
	public void testNumber() {
		assertEquals(Text.number("dummy"), 2);
		assertEquals(Text.number("industry"), 2);
		assertEquals(Text.number("bananas"), 0);
	}
}