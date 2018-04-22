import static com.revature.users.Admin.addUser;
import static com.revature.users.SerializationOfUsers.userFile;
import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.users.User;

@SuppressWarnings("unused")
public class ApplicationTest {

	@Test
	public void noUserData() {
				
		userFile.delete();
		
		com.revature.application.Application.main(null);
	}
	
	@Test
	public void newUserFileTest() {
				
		userFile.delete();
		
		User p = addUser("adamL", "easypass", true, true, 53.6);
		User v = addUser("admjl", "ExCeLl3nT_passW0rd", false, false, 573.6);
		User f = addUser("neat", "guy", true, false, 531.6);
		User a = addUser("anything", "true", true, false, 100);
		User b = addUser("what", "thing", true, false, 200);
		User c = addUser("dude", "no", true, true, 300);
		
		com.revature.application.Application.main(null);
	}
	
	@Test
	public void applicationTest() {		
		com.revature.application.Application.main(null);
	}
	
}
