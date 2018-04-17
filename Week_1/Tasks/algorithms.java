package com.homework;

import java.util.Arrays;

public class algorithms {
	
	public static void main(String[] args) throws Exception {
		System.out.println(fibbonaciRecursive(15));
		System.out.println(fibbonaciIterative(15));
		System.out.println(factorial(6));
		System.out.println(combination(5,3));
		System.out.println(permutation(5,3));
		System.out.println(palindrome("racecar"));
		System.out.println(palindrome("racecars"));
		int array[] = new int[] {1,2,3};
		System.out.println(swap(array, 0, 2));
		System.out.println(stringReverse("Cameron Skaggs Is Cool"));
		pascalRow(5);
		System.out.println(odd(5));
		System.out.println(odd(-20));
		System.out.println(odd(0));

	}
	
	public static int fibbonaciRecursive(int num) {
		if(num == 0) {
			return 0;
		}
		if(num == 1) {
			return 1;
		}
		return fibbonaciRecursive(num-1) + fibbonaciRecursive(num-2);
	}
	public static int fibbonaciIterative(int num) {
		int sum = 0;
		int prev1 = 1;
		int prev2 =  1;
		for(int i = 0; i < num; i++) {
			prev1 = prev2;
			prev2 = sum;
			sum = prev1 + prev2;
		}
		return sum;	
	}
	
	public static int factorial(int num) {
		if(num == 0) {
			return 1;
		}
		return num * factorial(num-1);
	}
	public static void pascalRow(int row) {
	    for (int i = 0; i < row; i++) {
	        for (int j = 0; j <= i; j++) {
	            System.out.print(combination(i, j) + " ");
	        }
	        System.out.println();
	    }
	}
	public static int combination(int n, int k) {
		int nFactorial =  factorial(n);
		int kFactorial =  factorial(k);
		int diffFactorial = factorial(n-k);
		return nFactorial / (kFactorial * diffFactorial);
	}
	public static int permutation(int n, int k) {
		int nFactorial =  factorial(n);
		int diffFactorial = factorial(n-k);
		return nFactorial / diffFactorial;
	}
	public static boolean palindrome(String s) {
		String rs = new StringBuilder(s).reverse().toString();
		if(s.equals(rs)) {
			return true;
		}
		return false;
	}
	public static int[] swap(int[] array, int i, int i2) {
		int [] array2 = array.clone();
		array2[i] = array[i2];
		array2[i2] = array[i];
		return array2;
	}
	public static String stringReverse(String s) throws Exception{
		int i = 0;
		try {		
			while(true) {
				s.charAt(i);
				i++;
			}
		}
			catch(IndexOutOfBoundsException ex) {
				i--;
				System.out.println(i);
			}
		char[] array = new char[i+1];
		int size = i+1;
		for(int j=0; j < size; j++) {
			System.out.println("index " + i);
			array[j] = s.charAt(i);
			System.out.println(s.charAt(i));
			i--;
		}
		String s2 = new String(array);
		return s2;
	}
	public static boolean odd(int n) {
		if(n > 0) {
			while(n > 0) {
				n = n-2;
			}
			if(n == 0) {
				return false;
			}
			return true;
		}
		if(n < 0) {
			while(n < 0) {
				n = n+2;
			}
			if(n == 0) {
				return false;
			}
			return true;
		}
		else {
			return false;
		}
	}
}
