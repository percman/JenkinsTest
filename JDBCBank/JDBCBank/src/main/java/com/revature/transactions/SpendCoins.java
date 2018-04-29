package com.revature.transactions;

import com.revature.menus.SpendCoinsMenu;
import com.revature.users.Student;

public class SpendCoins {
	
	public static void subtraction(Student student) {
		int coins = student.getCoins();
		student.setCoins(coins - 10);
		student.setBoughtSubtraction(1);
		SpendCoinsMenu.spendCoinsMenu(student);
	}
	
	public static void multiplication(Student student) {
		int coins = student.getCoins();
		student.setCoins(coins - 20);
		student.setBoughtMultiplication(1);
		SpendCoinsMenu.spendCoinsMenu(student);
	}
	
	public static void division(Student student) {
		int coins = student.getCoins();
		student.setCoins(coins - 30);
		student.setBoughtDivision(1);
		SpendCoinsMenu.spendCoinsMenu(student);
	}
	
}
