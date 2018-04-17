package com.revature.factorial;

public class Calculations {
	public int factorial(int n) {
		int product = 1;
		for(int i = 1;i<=n;i++) {
			product = product*i;
		}
		return product;
	}
	public int combinations(int n, int k) {
		return (factorial(n))/(factorial(k)*factorial(n-k));
	}
	public int permutations(int n, int k) {
		return(factorial(n))/(factorial(n-k));
	}
}
