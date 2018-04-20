package com.revature.exceptions;

public class Applicaltion {

	public static void main(String[] args) {

		try {
			myRiskyCode();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void recursionTest() {

		try {
			Recursion.calculate(10000000);
		} finally {
			System.out.println("Finally will always execute, unless there is a call to Sytem.exit()"
					+ "or there is a catashtrophic system failure");
		}

	}

	public static void finallyTest(boolean isSystemExitCalled) {

		System.out.println("Hey! We just called finallyTest");
		try {
			System.out.println("Now, we are inside the try-block");
			System.out.println("Was System.exit() called? " + isSystemExitCalled);
			if (isSystemExitCalled) {
				System.exit(0);
			}
		} finally {
			System.out.println("Finally will always execute, unless there is a call to Sytem.exit()"
					+ "or there is a catashtrophic system failure");
		}
	}

	public static void memoryTest() throws InterruptedException {

		int iteratorValue = 20;
		System.out.println("======================> OutOfMemoryError Test started");
		for (int outer = 1; outer < iteratorValue; outer++) {
			System.out.println("Iteration " + outer + " => Free Memory: " + Runtime.getRuntime().freeMemory());
			int loop1 = 2;
			int[] memoryFillIntVar = new int[iteratorValue];
			do {
				memoryFillIntVar[loop1] = 0;
				loop1--;
			} while (loop1 > 0);
			iteratorValue = iteratorValue * 5;
			System.out.println("\nRequired memory for the next loop: " + iteratorValue);
			Thread.sleep(1000);
		}
	}

	public static void myRiskyCode() throws GrandchildException {

		throw new GrandchildException();

	}
}
