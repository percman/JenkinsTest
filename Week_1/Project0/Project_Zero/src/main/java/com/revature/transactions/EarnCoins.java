package com.revature.transactions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;

import com.revature.singletons.LogThis;

public class EarnCoins {
	
	private static Scanner sc = new Scanner(System.in);

	private static Random random = new Random();


	public static boolean addition() {
		boolean correct = false;

		int a = random.nextInt(11);
		int b = random.nextInt(11);

		System.out.println(a + " + " + b + " =");
		try {
			int c = sc.nextInt();
			if (a + b == c) {
				correct = true;
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in addition " + ime.getMessage());
			System.out.println("Please enter a number, try again");
			addition();
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in addition " + nsee.getMessage());
			System.out.println("Please enter a number, try again");
			addition();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in addition " + ise.getMessage());
			System.out.println("Please enter a number, try again");
			addition();
		}

		return correct;
	}

	public static boolean subtraction() {
		boolean correct = false;

		int a = random.nextInt(11);
		int b = random.nextInt(11);

		if (a >= b) {
			System.out.println(a + " - " + b + " =");
			try {
				int c = sc.nextInt();
				if (a - b == c) {
					correct = true;
				}
			} catch (InputMismatchException ime) {
				LogThis.warn("InpupMismatchException in subtraction " + ime.getMessage());
				System.out.println("Please enter a number, try again");
				subtraction();
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in subtraction " + nsee.getMessage());
				System.out.println("Please enter a number, try again");
				subtraction();
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in subtraction " + ise.getMessage());
				System.out.println("Please enter a number, try again");
				subtraction();
			}
		} else {
			System.out.println(b + " - " + a + " =");
			try {
				int c = sc.nextInt();
				if (b - a == c) {
					correct = true;
				}
			} catch (InputMismatchException ime) {
				LogThis.warn("InpupMismatchException in subtraction " + ime.getMessage());
				System.out.println("Please enter a number, try again");
				subtraction();
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in subtraction " + nsee.getMessage());
				System.out.println("Please enter a number, try again");
				subtraction();
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in subtraction " + ise.getMessage());
				System.out.println("Please enter a number, try again");
				subtraction();
			}
		}

		return correct;
	}

	public static boolean multiplication() {
		boolean correct = false;

		int a = random.nextInt(11);
		int b = random.nextInt(11);

		System.out.println(a + " x " + b + " =");
		try {
			int c = sc.nextInt();
			if (a * b == c) {
				correct = true;
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in multiplication " + ime.getMessage());
			System.out.println("Please enter a number, try again");
			multiplication();
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in multiplication " + nsee.getMessage());
			System.out.println("Please enter a number, try again");
			multiplication();
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in multiplication " + ise.getMessage());
			System.out.println("Please enter a number, try again");
			multiplication();
		}

		return correct;
	}
	
	public static boolean division() {
		boolean correct = false;

		int a = random.nextInt(11);
		int b = random.nextInt(11);

		if (a >= b) {
			System.out.println(a + " / " + b + " =");
			try {
				int c = sc.nextInt();
				System.out.println("With a remainder of");
				int d = sc.nextInt();
				if (a / b == c && a % b == d) {
					correct = true;
				}
			} catch (InputMismatchException ime) {
				LogThis.warn("InpupMismatchException in division " + ime.getMessage());
				System.out.println("Please enter a number, try again");
				division();
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in division " + nsee.getMessage());
				System.out.println("Please enter a number, try again");
				division();
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in division " + ise.getMessage());
				System.out.println("Please enter a number, try again");
				division();
			}
		} else {
			System.out.println(b + " - " + a + " =");
			try {
				int c = sc.nextInt();
				System.out.println("With a remainder of");
				int d = sc.nextInt();
				if (b / a == c && b % a == d) {
					correct = true;
				}
			} catch (InputMismatchException ime) {
				LogThis.warn("InpupMismatchException in division " + ime.getMessage());
				System.out.println("Please enter a number, try again");
				division();
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in division " + nsee.getMessage());
				System.out.println("Please enter a number, try again");
				division();
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in division " + ise.getMessage());
				System.out.println("Please enter a number, try again");
				division();
			}
		}

		return correct;
	}


}
