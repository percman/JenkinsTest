package com.revature.dayonehw;

import java.util.Scanner;

public class EvenOrOdd {
	
	public static Boolean numberEvenOrOdd(int n) {
		
		if(((n / 2) * 2) == n)
			return true;
		else
			return false;
		
	}
	

	public static void main(String[] args) {
		
		int n; 
		
		System.out.println("Please enter a number, even is true and odd is false: ");
		Scanner keyboard = new Scanner(System.in);
		n = keyboard.nextInt();
		
		System.out.println(numberEvenOrOdd(n));
		
		
	}
}
