
import static org.junit.Assert.*;

//import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import com.revature.fi.Math;
import com.revature.fi.Greeting;

public class LambdaTest {

	static com.revature.fi.Math addition = null;
	static Math subtraction = null;
	static Math multiplication = null;
	static Math division = null;
	static Greeting greeter1 = null;
	static Greeting greeter2 = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		addition = (num1, num2) -> num1 + num2;
		subtraction = (a, b) -> a - b;
		multiplication = (x, y) -> {
			return x * y;
		};
		division = (n, m) -> {
			if (m == 0) {
				return 0;
			}
			return n / m;
		};
		greeter1 = s -> s + " says hello!";
		greeter2 = (String s) -> {
			return s + " says hello!";
		};
	}

	@Test
	public void lambdaAddition() {
		assertTrue(30 == addition.compute(15, 15));
	}

	@Test
	public void lambdaSubtraction() {
		assertTrue(0 == subtraction.compute(10, 10));
	}

	@Test
	public void lambdaMultiplication() {
		assertTrue(150 == multiplication.compute(10, 15));
	}

	@Test
	public void lambdaDivision() {
		assertTrue(5 == division.compute(15, 3));
	}

	@Test
	public void lambdaDivisionByZero() {
		assertTrue(0 == division.compute(1, 0));
	}
	
	@Test
	public void lambdaGreeter() {
		assertTrue(greeter1.sayHello("Amelia").equals(greeter2.sayHello("Amelia")));
	}

}
