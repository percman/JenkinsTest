package com.revature.project_0;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.model.Account;
import com.revature.service.AccountAccessService;

public class AccountAccessTest {
	Account account = new Account("andy","boss");
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Test Starting");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test has ended");
	}

	@Test
	public void test() {
		assertTrue(AccountAccessService.isAdmin(account));
	}
	
	@Test
	public void test2() {
		assertFalse(AccountAccessService.isLocked(account));
	}
	
	@Test
	public void test3() {
		assertFalse(AccountAccessService.isPending(account));
	}

}
