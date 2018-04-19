package com.revature.exceptions;

public class Recursion {

	public static int calculate(int initialValue) {
		return initialValue * calculate(initialValue--);
	}
}
