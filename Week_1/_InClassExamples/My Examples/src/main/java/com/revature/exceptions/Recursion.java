package com.revature.exceptions;

public class Recursion {
	

	public static int factorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("Forgot to include termination step");
		}
		return n * factorial(n-1);
	}
	
	public static void main(String[] args) {
		try {
			Recursion.factorial(5000000);
		}  finally {
			System.out.println("Finally FTW");
		}
	}
}
