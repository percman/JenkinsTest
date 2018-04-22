package com.andrewsrahn.test;

import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import com.andrewsrahn.main.User;
import com.andrewsrahn.main.Administrator;
import com.andrewsrahn.main.Stream;
import java.util.List;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.Before;

public class StreamTest {
	Map<String, User> users;
	Administrator andrew;
	User cameron;
	User andy;
	User vince;
	List<User> pending;
	List<User> rejected;
	List<User> approved;

	@Before
	public void setUp() {
		users = new HashMap<>();
		andrew = new Administrator("andrew", "password");
		andy = new User("andy", "password", 0, true);
		cameron = new User("cameron", "password", 20, false);
		vince = new User("vince", "password", 20, false);
		
		andy.setRejectedBy(andrew);
		vince.setApprovedBy(andrew);
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