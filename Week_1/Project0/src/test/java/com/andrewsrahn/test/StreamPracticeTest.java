package com.andrewsrahn.test;

import static org.junit.Assert.*;
import java.util.Map;
import java.util.HashMap;
import com.andrewsrahn.main.User;
import com.andrewsrahn.main.Administrator;
import com.andrewsrahn.main.StreamPractice;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class StreamPracticeTest {
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

		andrew = new Administrator("andrew", "andrewsrahn@gmail.com", "password");

		cameron = new User("cameron", "cameron@gmail.com", "password");

		andy = new User("andy", "ahnman341@gmail.com", "password");
		andy.setRejectedBy(andrew);

		vince = new User("vince", "vince@gmail.com", "password");
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
		assertEquals(StreamPractice.approved(users), approved);
	}
	
	@Test
	public void testRejected() {
		assertEquals(StreamPractice.rejected(users), rejected);
	}
	
	@Test
	public void testPending() {
		assertEquals(StreamPractice.pending(users), pending);
	}

}
