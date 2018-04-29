package com.revature.spendCoinsTest;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.transactions.SpendCoins;
import com.revature.users.Student;

public class Multiplication {

	private static Student student = new Student();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		student.setCoins(40);
	}


	@Test
	public void testBuyMultiplication() {
		
		SpendCoins.multiplication(student);
		
		assertTrue(student.getCoins() == 20 && student.isBoughtMultiplication() == 1);
	}

}
