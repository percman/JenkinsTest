package com.revature.factoryTest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.factory.ReimbursementFactory;
import com.revature.reimbursement.LodgingReimbursement;
import com.revature.reimbursement.OtherReimbursement;
import com.revature.reimbursement.TravelReimbursement;

public class FactoryTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test1() {
		assertTrue(ReimbursementFactory.getReimbursement(4) instanceof TravelReimbursement);
	}
	
	@Test
	public void test2() {
		assertTrue(ReimbursementFactory.getReimbursement(3) instanceof OtherReimbursement);
	}
	
	@Test
	public void test3() {
		assertFalse(ReimbursementFactory.getReimbursement(4) instanceof LodgingReimbursement);
	}
}
