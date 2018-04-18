package com.revature.assignment1;

public class Permutation {

	public static int permutation(int n, int r) {
		
		int num = Factorial.factorial(n);
		int denom = Factorial.factorial(n-r);
		
		return num/denom;
		
	}
	

	public static void main(String[] args) {
	
		System.out.println(permutation(10,2));

	}

}
