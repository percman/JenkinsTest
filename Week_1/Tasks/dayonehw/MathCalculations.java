package com.revature.dayonehw;

import java.util.Arrays;
import java.util.Scanner;

public class MathCalculations {
	
	public static int factorial(int n) {
		int arr[];
		arr = new int[n+1];
		int fact = 1;
		
		for(int i = 1; i <= n; i++) {
			arr[i] = fact * i;
			fact = fact * i;
		}
		
		return fact;		
	}
	
	public static int combination(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n-k));
	}
	
	public static int permutation(int n, int k) {
		return factorial(n) / (factorial(n - k));
	}
	
	public static void main(String[] args) {
		int n;
		int k;
		
		System.out.println("Factorial up to: ");
		Scanner keyboard = new Scanner(System.in);
		n = keyboard.nextInt();
		System.out.println(factorial(n));
		
		System.out.println("Combination of: ");
		Scanner keyboard1 = new Scanner(System.in);
		n = keyboard1.nextInt();
		System.out.println("... and combination of:");
		Scanner keyboard2 = new Scanner(System.in);
		k = keyboard2.nextInt();
		
		System.out.println(combination(n,k));
		
		System.out.println("Permutation of: ");
		Scanner keyboard11 = new Scanner(System.in);
		n = keyboard11.nextInt();
		System.out.println("... and permutation of:");
		Scanner keyboard21 = new Scanner(System.in);
		k = keyboard21.nextInt();
		
		System.out.println(permutation(n,k));
	}
}
