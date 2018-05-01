import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static com.revature.menu.Menus.welcomeScreen;

import org.junit.Test;

import com.revature.logstatus.LogHere;

public class ExceptionTests {

	@Test
	public void ChoiceInputThrown() {
		try {
			System.setIn(new BufferedInputStream(new FileInputStream("src/test/resources/inputtest")));
			welcomeScreen();
			System.exit(0);
		} catch (FileNotFoundException fnfe) {
			LogHere.warn(fnfe.getMessage());
		}
	}
	
	
}
