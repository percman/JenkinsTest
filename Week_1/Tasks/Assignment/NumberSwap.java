package com.revature.DayOne.Assignment;

public class NumberSwap {

	public static void main(String[] args) {
		Swap(5,10);
	}
	
	public static void Swap(int a, int b) {
		a += b;
		b = a - b;
		a-=b;
		
		System.out.println(a+" "+b);
	}

}
