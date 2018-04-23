import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


import com.revature.bank.*;

public class BankTest {

	static Admin admin = new Admin("AdminName", "AdminPassword");
	static User user =new User("UserName", "UserPassword");
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Admin.addAdminToMap(admin.username, admin);
		User.addUserToMap(user.username, user);
	}

	@After
	public void reset() {
		user.withdraw(user.getBalance());
		admin.approved=false;
		user.approved=false;
		user.locked=false;
	}
	
	@Test
	public void adminCreate() {
		assertTrue(Admin.adminMap.containsKey("AdminName"));
	}
	
	@Test
	public void userCreate() {
		assertTrue(User.userMap.containsKey("UserName"));
	}
	
	@Test
	public void deposit() {
		user.deposit(500.50);
		assertEquals(500.50, user.getBalance(), 0.004);
	}
	
	@Test
	public void withdraw() {
		user.deposit(200);
		user.withdraw(100);
		assertEquals(100, user.getBalance(), 0.004);
	}
	
	@Test
	public void overwithdraw() {
		user.deposit(100);
		user.withdraw(100.01);
		assertEquals(100, user.getBalance(), 0.004);
	}
	
	@Test
	public void approveUser() {
		admin.approveUser(user);
		assertTrue(user.approved);
	}
	
	@Test
	public void approveAdmin() {
		admin.approveAdmin(admin);
		assertTrue(admin.approved);
	}

	@Test
	public void lockUser() {
		admin.lockUser(user);
		assertTrue(user.locked);
	}
	
	@Test
	public void serializeAdmin() {
		File adminFile = new File("src/test/resources/admin.txt");
		Serialize.serializeAdmin(adminFile);
		Map<String, Admin> testMap = new HashMap<>();
		testMap = Admin.adminMap;
		Admin.adminMap = null;
		Serialize.deserializeAdmin(adminFile);
		assertEquals(testMap.keySet(), Admin.adminMap.keySet());
	}
	
	@Test
	public void serializeUser() {
		File userFile = new File("src/test/resources/user.txt");
		Serialize.serializeUser(userFile);
		Map<String, User> testMap = new HashMap<>();
		testMap = User.userMap;
		Admin.adminMap = null;
		Serialize.deserializeUser(userFile);
		assertEquals(testMap.keySet(), User.userMap.keySet());
	}
}
