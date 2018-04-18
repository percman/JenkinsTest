package com.revature.assignment1;

public class Factorial {
	
	public static int factorial(int i) {
		
	int fac=i;
	
	while(i!=1) {
		fac = fac * (i-1);
		i--;
	}
	
	return fac;
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(factorial(10));
		
	}



}
