package com.revature.spendCoinsTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.transactions.SpendCoins;
import com.revature.users.Student;

public class Division {

	private static Student student = new Student();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		student.setCoins(40);
	}

	@Test
	public void testBuyDivision() {
		
		SpendCoins.division(student);
		
		assertTrue(student.getCoins() == 10 && student.isBoughtDivision() == 1);
	}

}
