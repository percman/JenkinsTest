import static com.revature.menu.Menus.loginAttempt;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.Random;

import org.junit.Test;
import static com.revature.service.UserService.getAnyUser;
import static com.revature.service.UserService.getAllUsers;
import static com.revature.service.UserService.insertUser;
import static com.revature.service.UserService.updateUser;
import static com.revature.service.UserService.getUser;
import static com.revature.service.UserService.insertAdmin;




import com.revature.users.User;
@SuppressWarnings("unused")
public class DaoTests {

	
	
	@Test
	public void ConnectionException() {

		com.revature.util.ConnectionUtil.main(null);
		
		assertTrue(true);
		
	}
	
	@Test
	public void UserDataExists(){
		System.out.println(getAnyUser());
		System.out.println(getAllUsers());

		assert(getAnyUser() != null);
		assert(getAllUsers() != null);
	}
	
	protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        salt.append("a");
        while (salt.length() < 15) { 
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
	
	@Test
	public void InsertAndUpdate() {
		Random rand = new Random();
		
		String randomname = getSaltString();
		String randompass = getSaltString();
		double randombal = rand.nextDouble();
		
//		System.out.println(randomname);
//		System.out.println(randompass);
//		System.out.println(randombal);
		
		User randuser = new User(randomname, randompass, randombal);	
		assertTrue(insertUser(randuser));
		assertFalse(insertUser(randuser));

		
//		System.out.println(getUser(randuser.getUsername()).getUsername());
//		System.out.println(randuser.getUsername());		
		assertTrue((getUser(randuser.getUsername()).getUsername()).equals(randuser.getUsername()));
	}
	
	
	@Test
	public void AdminLockedTest() {
		Random rand = new Random();

		String randomname = getSaltString();
		String randompass = getSaltString();
		double randombal = rand.nextDouble();
		
		User randuser = new User(randomname, randompass, randombal);	
		assertTrue(insertAdmin(randuser));
		assertTrue(getUser(randuser.getUsername()).isAdminstatus());
		assertFalse(getUser(randuser.getUsername()).isLocked());
	}
	


}
