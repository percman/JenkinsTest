package com.revature.menus;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.singletons.LogThis;
import com.revature.transactions.EarnCoins;
import com.revature.users.Student;

public class EarnCoinsMenu {
	
	private static Scanner sc = new Scanner(System.in);

	public static void earnCoinsMenu(Student student) {
		LogThis.info("Get Coins Menu");
		System.out.println("You have " + student.getCoins() + " coins!");
		System.out.println("You earn more coins by correctly answering arithmetic problems.");
		System.out.println("1. Solve an addition problem");
		if(student.isBoughtSubtraction()) {
			System.out.println("2. Solve a subtraction problem");
		}
		if(student.isBoughtMultiplication()) {
			System.out.println("3. Solve a multiplication problem");
		}
		if (student.isBoughtDivision()) {
			System.out.println("4. Solve a division problem");
		}
		System.out.println("0. Return to Student Menu");
		
		try {
			while (true) {
				int choice = sc.nextInt();
				switch (choice) {
				case 1:
					solveAddition(student);
					return;
				case 2:
					if(student.isBoughtSubtraction()) {
						solveSubtraction(student);
						return;
					} else {
						LogThis.info("Invalid Choice");
						System.out.println("Your options are:");
						System.out.println("1. Solve an addition problem");
						if(student.isBoughtSubtraction()) {
							System.out.println("2. Solve a subtraction problem");
						}
						if(student.isBoughtMultiplication()) {
							System.out.println("3. Solve a multiplication problem");
						}
						if (student.isBoughtDivision()) {
							System.out.println("4. Solve a division problem");
						}
						System.out.println("0. Return to Student Menu");
					}
					break;
				case 3:
					if(student.isBoughtMultiplication()) {
						solveMultiplication(student);
						return;
					} else {
						LogThis.info("Invalid Choice");
						System.out.println("Your options are:");
						System.out.println("1. Solve an addition problem");
						if(student.isBoughtSubtraction()) {
							System.out.println("2. Solve a subtraction problem");
						}
						if(student.isBoughtMultiplication()) {
							System.out.println("3. Solve a multiplication problem");
						}
						if (student.isBoughtDivision()) {
							System.out.println("4. Solve a division problem");
						}
						System.out.println("0. Return to Student Menu");
					}
					break;
				case 4:
					if (student.isBoughtDivision()) {
						solveDivision(student);
						return;
					} else {
						LogThis.info("Invalid Choice");
						System.out.println("Your options are:");
						System.out.println("1. Solve an addition problem");
						if(student.isBoughtSubtraction()) {
							System.out.println("2. Solve a subtraction problem");
						}
						if(student.isBoughtMultiplication()) {
							System.out.println("3. Solve a multiplication problem");
						}
						if (student.isBoughtDivision()) {
							System.out.println("4. Solve a division problem");
						}
						System.out.println("0. Return to Student Menu");
					}
					break;
				case 0:
					StudentMenu.studentMenu(student);
					return;
				default:
					LogThis.info("Invalid Choice");
					System.out.println("Your options are:");
					System.out.println("1. Solve an addition problem");
					if(student.isBoughtSubtraction()) {
						System.out.println("2. Solve a subtraction problem");
					}
					if(student.isBoughtMultiplication()) {
						System.out.println("3. Solve a multiplication problem");
					}
					if (student.isBoughtDivision()) {
						System.out.println("4. Solve a division problem");
					}
					System.out.println("0. Return to Student Menu");
					break;
				}
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InputMismatchException in Earn Coins Menu " + ime.getMessage());
			earnCoinsMenu(student);
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in Earn Coins Menu " + nsee.getMessage());
			earnCoinsMenu(student);
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateExcdeption in Earn Coins Menu " + ise.getMessage());
			earnCoinsMenu(student);
		} 


	}
	
	private static void solveAddition(Student student) {
		int coins = student.getCoins();
		if(EarnCoins.addition()) {
			student.setCoins(coins + 1);
			LogThis.info(student.getFirstname() + " earned a coin!");
		} else {
			System.out.println("Sorry, your answer was incorrect.");
			System.out.println("You did not earn a coin, try again.");
		}
		earnCoinsMenu(student);
	}
	
	private static void solveSubtraction(Student student) {
		int coins = student.getCoins();
		if(EarnCoins.subtraction()) {
			student.setCoins(coins + 1);
			LogThis.info(student.getFirstname() + " earned a coin!");
		} else {
			System.out.println("Sorry, your answer was incorrect.");
			System.out.println("You did not earn a coin, try again.");
		}
		earnCoinsMenu(student);
	}

	private static void solveMultiplication(Student student) {
		int coins = student.getCoins();
		if(EarnCoins.multiplication()) {
			student.setCoins(coins + 1);
			LogThis.info(student.getFirstname() + " earned a coin!");
		} else {
			System.out.println("Sorry, your answer was incorrect.");
			System.out.println("You did not earn a coin, try again.");
		}
		earnCoinsMenu(student);
	}

	private static void solveDivision(Student student) {
		int coins = student.getCoins();
		if(EarnCoins.division()) {
			student.setCoins(coins + 1);
			LogThis.info(student.getFirstname() + " earned a coin!");
		} else {
			System.out.println("Sorry, your answer was incorrect.");
			System.out.println("You did not earn a coin, try again.");
		}
		earnCoinsMenu(student);
	}

}
