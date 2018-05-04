package util.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import exception.CreatePersonException;
import model.Administrator;
import model.PersonFactory;
import model.User;
import util.Stream;

public class StreamTest {
	static Map<String, User> users;
	static Administrator andrew;
	static User cameron;
	static User andy;
	static User vince;
	static List<User> pending;
	static List<User> rejected;
	static List<User> approved;

	@BeforeClass
	public static void setUp() {
		users = new HashMap<>();
		try {
			andrew = (Administrator) PersonFactory.create("administrator", "andrew", "password", 0, false);
			andy = (User) PersonFactory.create("user", "andy", "password", 0, true);
			cameron = (User) PersonFactory.create("user", "cameron", "password", 20, false);
			vince = (User) PersonFactory.create("user", "vince", "password", 20, false);
		} catch(CreatePersonException e) {
			System.err.println(e);
		}
		
		andy.setReject(andrew);
		vince.setApprove(andrew);
		users.put("cameron", cameron);
		users.put("andy", andy);
		users.put("vince", vince);

		approved = new ArrayList<>();
		rejected = new ArrayList<>();
		pending = new ArrayList<>();

		approved.add(vince);
		rejected.add(andy);
		pending.add(cameron);
	}

	@Test
	public void testApproved() {
		assertEquals(Stream.approved(users), approved);
	}

	@Test
	public void testRejected() {
		assertEquals(Stream.rejected(users), rejected);
	}

	@Test
	public void testPending() {
		assertEquals(Stream.pending(users), pending);
	}
	
	@Test
	public void testPendingUsers() {
		assertEquals(Stream.pendingUsers(users), "pending: cameron");
	}
	
	@Test
	public void testLockedUsers() {
		assertEquals(Stream.lockedUsers(users), "locked: andy");
	}
}
