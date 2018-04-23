import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;


public class MethodTesting {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String name = " ";
		double price = 0.0;
		int quantity = 0;
		
		Item k = new Item(name, price, quantity);
		
	}

	@Test//Add new item
	public void test() {
		fail("Not yet implemented");
	}

}
