package com.hw1;

public class HW1 {
	
	//Returns item in the fibonacci sequence at position n (0 indexed)
	// I'm counting the fibonacci sequence as starting @ 0
	public static int iterFib(int n) {
		int first = 0;
		int next = 1;
		int tmp = 0;
		
		while(n-- > 0) {
			tmp = first;
			first = next;
			next = tmp + next;
		}
		return first;
	}
	
	//Returns item in the fibonacci sequence at position n (0 indexed)
	// I'm counting the fibonacci sequence as starting @ 0
	public static int recurFib(int n) {
		if (n == 0) {
			return 0;
		}
		else if (n == 1) {
			return 1;
		}
		else {
			return recurFib(n - 1) + recurFib(n - 2);
		}
		
	}

	public static int factorial(int n) {
		if (n == 0) {
			return 1;
		}
		else {
			return factorial(n - 1) * n;
		}
	}
	
	public static int combination (int setSize, int choices) {
		return factorial(setSize) / (factorial(choices) * factorial(setSize - choices));
	}
	
	public static int permutation (int setSize, int choices) {
		return factorial(setSize) / (factorial(setSize - choices));
	}
	
	public static void pascalTriangle (int rowNum) {
		System.out.print(1);
		int numerator = rowNum;
		for (int a = 1; a <= rowNum; a++) {
			System.out.print(" ");
			if (a != 1) {
				numerator *= rowNum - a + 1;
				System.out.print(numerator / factorial(a));
			}
			else {
				System.out.print(rowNum);
			}
		}
	}
	
	public static String stringReverse (String s) {
		String rev = "";
		int index = 0;
		try {
			while (true) {
				rev = s.charAt(index++) + rev;
			}
		}
		catch (IndexOutOfBoundsException e) {}
		return rev;
	}
	
	public static boolean palindromeChecker (String s) {
		return s.equals(stringReverse(s));
	}
	
	
	public static int[] intSwap(int[] a) {
		a[0] = a[0] ^ a[1];
		a[1] = a[1] ^ a[0];
		a[0] = a[0] ^ a[1];
		return a;
	}
	
	public static boolean isOdd(int a) {
		return (a & 1) == 1;
	}
	
	public static void main(String[] args) {
		System.out.println("First 6 items of fibonacci sequence, iterative");
		for (int n = 0; n < 6; n++) {
			System.out.print(iterFib(n) + " ");
		}
		
		System.out.println("\nFirst 6 items of fibonacci sequence, recursively");
		for (int n = 0; n < 6; n++) {
			System.out.print(recurFib(n) + " ");
		}
		System.out.println("\nFirst 6 factorials.");
		for (int n = 0; n < 6; n++) {
			System.out.println(factorial(n));
		}
		
		System.out.println("COMBINATIONS");
		for (int n = 0; n < 11; n++) {
			System.out.println("10 choose " + n +" is " + combination(10, n));
		}
		
		System.out.println("PERMUTATIONS");
		for (int n = 0; n < 11; n++) {
			System.out.println("10 choose " + n +" is " + permutation(10, n));
		}
		System.out.println("HAVE SOME PASCAL'S TRIANGLE");
		for (int n = 0; n < 6; n++) {
			pascalTriangle(n);
			System.out.println();
		}
		System.out.println("REVERSE OF ELDRITCH IS " + stringReverse("ELDRITCH"));
		
		if (palindromeChecker("rats live on no evil star")) {
			System.out.println("'rats live on no evil star' is a palindrome");
		}
		else {
			System.out.println("'rats live on no evil star' is not a palindrome");
		}
		
		if (palindromeChecker("abomination")) {
			System.out.println("'abomination' is a palindrome");
		}
		else {
			System.out.println("'abomination' is not a palindrome");
		}
		
		int[] tmpArr = new int[2];
		tmpArr[0] = 3;
		tmpArr[1] = 2;
		
		intSwap(tmpArr);
		System.out.println("[3,2] swapped is [" +  tmpArr[0] + "," + tmpArr[1] + "]");
	

		if (isOdd(3)) {
			System.out.println("3 is odd");
		}
		else {
			System.out.println("3 is even");
		}
		
		if (isOdd(3000)) {
			System.out.println("3000 is odd");
		}
		else {
			System.out.println("3000 is even");
		}
		

	}
	
}
