package com.revature.exceptions;

public class Application {
	
	public static void main(String[] args) {
		try {
			myRiskyCode();
		} catch (GrandChildException gce) {
			gce.printStackTrace();
		} catch (ChildException ce) {
			ce.printStackTrace();
		} catch (ParentException pe) {
			pe.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void recursionTest() {
		try {
			Recursion.calculate(1000000);
		} finally {
			System.out.println("Finally will always execute, unless there is a call to System.exit() or "
					+ "a catastrophic system failure");
		}
	}
	
	public static void finallyTest(boolean isSystemExitCalled) {
		System.out.println("Hey! we just called finallyTest");
		try {
			System.out.println("Now, we are inside the try-block");
			System.out.println("Was System.exit() called? " + isSystemExitCalled);
			if (isSystemExitCalled) {
				System.exit(0);
			}
		} finally {
			System.out.println("Finally will always execute, unless there is a call to System.exit() or "
					+ "a catastrophic system failure");
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
	
	
	public static void myRiskyCode() throws GrandChildException {
		throw new GrandChildException();
	}
	
}
