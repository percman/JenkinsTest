package com.revature.dayonehw;

import java.util.Arrays;
import java.util.Scanner;

public class IntegerSwap {
	
	public static int[] intSwap(int[] arr) {
		//note to self: this works because:
		//	adds the two numbers together
		//	THEN subtracts...
		//	the first value which has been overwritten by the second
		//	works in runtime :) 
		arr[1] = (arr[0] + arr[1]) - (arr[0] = arr[1]);
		return arr;
	}

	public static void main(String[] args) {
		int arr[];
		arr = new int[2];
		
		System.out.println("Enter the first integer to be swapped in an array: ");
		Scanner keyboard = new Scanner(System.in);
		arr[0] = keyboard.nextInt();
		
		System.out.println("Enter the first integer to be swapped in an array: ");
		Scanner keyboard1 = new Scanner(System.in);
		arr[1] = keyboard1.nextInt();
		
		System.out.println("Initial: " + Arrays.toString(arr));
		System.out.println("Final: " + Arrays.toString(intSwap(arr)));

	}
}
