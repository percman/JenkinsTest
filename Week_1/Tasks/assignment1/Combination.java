package com.revature.assignment1;

public class Combination {
	
	public static int combination(int n, int r) {
		
		int num = Factorial.factorial(n);
		int denom = Factorial.factorial(r) * Factorial.factorial(n-r);
		
		return num/denom;
		
	}
	

	public static void main(String[] args) {
	
		System.out.println(combination(10,2));

	}



}
