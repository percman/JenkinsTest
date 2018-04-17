package com.revature.dayonehw;

import java.util.Arrays;
import java.util.Scanner;



public class PascalTriangle extends MathCalculations{

	
	public static void pascalRow(int row) {
		

		int arr[];
		arr = new int[row];
		
	       for (int n = 0; n < row; n++) {
	           for (int k = 0; k <= n; k++) {
	               arr[k] = combination(n, k);
	           }
	       }
           System.out.println(Arrays.toString(arr));
	}
	
	public static void pascalTriangle(int row) {
		
	       for (int n = 0; n < row; n++) {
	           for (int k = 0; k <= n; k++) {
	               System.out.print(combination(n, k) + " ");
	           }
	           System.out.println();
	       }
	}
	
	public static void main(String[] args) {

		int row;
		
		System.out.println("Pascal's triangle specific row: ");
		Scanner keyboard = new Scanner(System.in);
		row = keyboard.nextInt();
		
		pascalRow(row);
		
		System.out.println("Pascal's triangle up to: ");
		Scanner keyboard1 = new Scanner(System.in);
		row = keyboard1.nextInt();
		
		pascalTriangle(row);
		
	}
}
