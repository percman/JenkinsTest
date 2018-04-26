package SpendCoins;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.transactions.SpendCoins;
import com.revature.users.Student;

public class Subtraction {
	
	private static Student student = new Student();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		student.setCoins(40);
	}

	@Test
	public void testBuySubtraction() {

		SpendCoins.subtraction(student);
		
		assertTrue(student.getCoins() == 30 && student.isBoughtSubtraction());
		
	}

}
