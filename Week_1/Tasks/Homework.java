package com.revature.homework;

public class Homework {

	public int fiboRecursion(int input) {
		if (input == 0) {
			return 0;
		} else if (input == 1) {
			return 1;
		} else {	
			return fiboRecursion(input-1) + fiboRecursion(input-2);
		}
	}
	
	public int fiboIterate(int input) {
		int x = 0;
		int y = 1;
		int temp;
		for(int i = 0; i < input; i++) {
			temp = x;
			x = y;
			y = y + temp;
		}
		return y;
	}
	
	public int factorial(int input) {
		if (input == 1) {
			return 1;
		} else {
			return factorial(input-1) * input;
		}
	}
	
	public int combination(int limit, int set) {
		return (factorial(set)/((factorial(limit)*(factorial(set-limit)))));
	}
	
	public int permutation(int limit, int set) {
		return factorial(set)/factorial(set-limit);
	}
	
	public void pascalTriangle(int row) {
		int[] array1 = new int[10];
		int[] array2 = new int[10];

		for(int i = 1; i < 10; i++) {
			array1[i] = 0;
		}
		for(int i = 0; i < row; i++) {
			if (i == 0) {
				array2[i] = 1;
			} else {
				array2[i] = array1[i-1] + array1[i+1];
			}
			for(int j = 0; j < row; j++) {
				array1[j] = array2[j];
			}
		}
		for(int i = 0; i < 10; i++) {
			System.out.println(array1[i] + " ");
		}
	}
	
//	public char reverseString(String input) {
//		char[] reverse = new char[10];
//		
//		
//		
//	}
	
	public boolean palindrome(String input) {
		for(int i = 0; i < input.length()/2; i++) {
			if(input.charAt(i) != input.charAt(input.length()-i)) {
				return false;
			}
		} 
		return true;
	}
	
	public void swap(int input1, int input2) {
		input1 += input2;
		input2 = input1 - input2;
		input1 = input1 - input2;
	}
	
	public boolean evenOdd(int input) {
		if((input/2)*2 == input) {
			return true;
		} else {
			return false;
		}
	}
	
}
