package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.singletons.LogThis;
import com.revature.transactions.SpendCoins;
import com.revature.users.Student;

public class SpendCoinsMenu {

	private static Scanner sc = new Scanner(System.in);

	public static void spendCoinsMenu(Student student) {
		LogThis.info("Spend Your Coins Menu");
		System.out.println("You have " + student.getCoins() + " coins!");
		System.out.println("You can use coins to gain access to more types of arithmetic problems.");
		if(student.isBoughtSubtraction() == 1) {
			System.out.println("1. Buy subtraction problems for 10 coins");
		}
		if(student.isBoughtMultiplication() == 1) {
			System.out.println("2. Buy multiplication problems for 20 coins");
		}
		if (student.isBoughtDivision() == 1) {
			System.out.println("3. Buy division problems 30 coins");
		}
		if(student.isBoughtSubtraction() == 1 && student.isBoughtMultiplication() == 1 && student.isBoughtDivision() == 1) {
			System.out.println("There are no more types of arithmetic problems left for you to buy");
		}
		System.out.println("0. Return to Student Menu");
		
		try {
			while (true) {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					if (student.isBoughtSubtraction() == 1) {
						System.out.println("You have already bought subtraction problems");
						spendCoinsMenu(student);
						return;
					} 
					
					if (student.getCoins() < 10) {
						System.out.println("You do not have enough coins to buy subtraction problems");
						spendCoinsMenu(student);
						return;
					}
					
					SpendCoins.subtraction(student);
					System.out.println("You can now try to solve subtraction problems");
					spendCoinsMenu(student);
					return;
				case 2:
					if (student.isBoughtMultiplication() == 1) {
						System.out.println("You have already bought multiplication problems");
						spendCoinsMenu(student);
						return;
					} 
					
					if (student.getCoins() < 20) {
						System.out.println("You do not have enough coins to buy multiplication problems");
						spendCoinsMenu(student);
						return;
					}
					
					SpendCoins.multiplication(student);
					System.out.println("You can now try to solve multiplication problems");
					spendCoinsMenu(student);
					return;
				case 3:
					if (student.isBoughtDivision() == 1) {
						System.out.println("You have already bought division problems");
						spendCoinsMenu(student);
						return;
					} 
					
					if (student.getCoins() < 30) {
						System.out.println("You do not have enough coins to buy division problems");
						spendCoinsMenu(student);
						return;
					}
					
					SpendCoins.division(student);
					System.out.println("You can now try to solve division problems");
					spendCoinsMenu(student);
					return;
				case 0:
					StudentMenu.studentMenu(student);
					return;
				default:
					LogThis.info("Invalid Choice");
					System.out.println("Your options are:");
					if(student.isBoughtSubtraction() == 1) {
						System.out.println("1. Buy subtraction problems for 10 coins");
					}
					if(student.isBoughtMultiplication() == 1) {
						System.out.println("2. Buy multiplication problems for 20 coins");
					}
					if (student.isBoughtDivision() == 1) {
						System.out.println("3. Buy division problems 30 coins");
					}
					if(student.isBoughtSubtraction() == 1 && student.isBoughtMultiplication() == 1 && student.isBoughtDivision() == 1) {
						System.out.println("There are no more types of arithmetic problems left for you to buy");
					}

					System.out.println("0. Return to Student Menu");
					break;
				}
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Spend Coins Menu " + ime.getMessage());
			spendCoinsMenu(student);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Spend Coins Menu " + nsee.getMessage());
			spendCoinsMenu(student);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateExcdeption in Spend Coins Menu " + ise.getMessage());
			spendCoinsMenu(student);
		}
	}

}
