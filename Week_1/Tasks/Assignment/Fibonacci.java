package com.revature.DayOne.Assignment;

public class Fibonacci {

	public static void main(String[] args) {
		System.out.println(rFib(6));
		System.out.println(iFib(8));

	}
	
	public static int rFib(int n) {
		if(n==1 || n ==2) {
			return 1;
		}
		
		return rFib(n-1) + rFib(n-2);
		
	}
	
	public static int iFib(int n) {
		if (n==1 || n==2)
			return 1;
		int a=1;
		int b=1;
		for (int i=0; i<n-2; i++){
			int hold = a;
			a = b+a;
			b = hold;
		}
		return a;
	}

}
