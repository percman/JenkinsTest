package com.revature.transactions;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.revature.singletons.LogThis;

public class EarnCoins {
	
	private static int a = (int) Math.random() * 10;
	private static int b = (int) Math.random() * 10;
	private static Scanner sc = new Scanner(System.in);

	
	public static int getA() {
		return a;
	}
	
	public static int getB() {
		return b;
	}


	public static boolean addition() {
		boolean correct = false;


		System.out.println(a + " + " + b + " =");
		try {
			int c = sc.nextInt();
			if (a + b == c) {
				correct = true;
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in addition " + ime.getMessage());
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in addition " + nsee.getMessage());
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in addition " + ise.getMessage());
		}

		return correct;
	}

	public static boolean subtraction() {
		boolean correct = false;


		if (a >= b) {
			System.out.println(a + " - " + b + " =");
			try {
				int c = sc.nextInt();
				if (a - b == c) {
					correct = true;
				}
			} catch (InputMismatchException ime) {
				LogThis.warn("InpupMismatchException in subtraction " + ime.getMessage());
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in subtraction " + nsee.getMessage());
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in subtraction " + ise.getMessage());
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
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in subtraction " + nsee.getMessage());
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in subtraction " + ise.getMessage());
			}
		}

		return correct;
	}

	public static boolean multiplication() {
		boolean correct = false;


		System.out.println(a + " x " + b + " =");
		try {
			int c = sc.nextInt();
			if (a * b == c) {
				correct = true;
			}
		} catch (InputMismatchException ime) {
			LogThis.warn("InpupMismatchException in multiplication " + ime.getMessage());
		} catch (NoSuchElementException nsee) {
			LogThis.warn("NoSuchElementException in multiplication " + nsee.getMessage());
		} catch (IllegalStateException ise) {
			LogThis.warn("IllegalStateException in multiplication " + ise.getMessage());
		}

		return correct;
	}
	
	public static boolean division() {
		boolean correct = false;


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
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in division " + nsee.getMessage());
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in division " + ise.getMessage());
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
			} catch (NoSuchElementException nsee) {
				LogThis.warn("NoSuchElementException in division " + nsee.getMessage());
			} catch (IllegalStateException ise) {
				LogThis.warn("IllegalStateException in division " + ise.getMessage());
			}
		}

		return correct;
	}


}
