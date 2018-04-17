package com.revature.DayOne.Assignment;

public class FactorialCombinationPermutation {

	public static void main(String[] args) {
		System.out.println(fact(3));
		System.out.println(comb(5,2));
		System.out.println(perm(7,3));

	}
	
	public static int fact(int num) {
		int hold = num;
		num --;
		for(int i = num; i >1 ; i--) {
			hold *= i;
		}
		return hold;
	}
	
	public static int comb(int a, int b) {
		return fact(a)/(fact(b)*fact(a-b));
		
	}
	
	public static int perm(int a, int b) {
		return fact(a)/fact(a-b);
		
	}

}
