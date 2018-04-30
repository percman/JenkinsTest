import org.junit.BeforeClass;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import com.revature.application.*;
import com.revature.model.*;

public class ProjectTest {
	
	
	
	@Test
	public void menuTest() {
		
		Application a = new Application();
		a.menu();
	}
	
	@Test
	public void loginTest() {
		
		Application a = new Application();
		a.logIn();
	}
	
	
	@Test
	public void testApprovalCode() {
		Customer customer = new Customer("Shawn", "password");
		assertEquals(0, customer.getApprovalCode());
	}
	
	
}
