import static org.junit.Assert.*;
import static com.revature.menu.Menus.firstRun;
import static com.revature.application.Application.main;

import org.junit.Test;

@SuppressWarnings("unused")
public class TestCase {

	@Test
	void noUserTest() {
		firstRun();
	}
	
	@Test
	void standardRun() {
		String[] arg = null;
		main(arg);
	}

}
