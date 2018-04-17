package com.revature.dayonehw;

import java.util.Arrays;
import java.util.Scanner;

public class Fibonacci {
	
	public static void iterative(int n) {
		int arr[];
		arr = new int[n+1];
		
		arr[0] = 0;
		arr[1] = 1;
		
		if(n == 0) System.out.println("You didn't want to print any numbers.");
		
		for(int i = 2; i <= n; i++) {
			arr[i] = arr[i-1] + arr[i-2];
		}
		
		System.out.println(Arrays.toString(arr));

	}
	
	public static int recursive(int n) {
		if(n == 0) return 0;
		else if (n == 1) return 1;
		else return recursive(n-1) + recursive(n-2);
	}

	public static void main(String[] args) {
		int n; 
		
		System.out.println("Please enter how many you would like to print: ");
		Scanner keyboard = new Scanner(System.in);
		n = keyboard.nextInt();
		
		iterative(n);
		
		for(int i = 0; i <= n; i++) {
			System.out.print(recursive(i) + " ");
		}
		
	}
}
