package com.revature.solutions;

public class Solutions {

	public static boolean palendrome(String word) {
		int len = word.length();
		for (int i = 0; i < len / 2; i++) {
			char front = word.charAt(i);
			char back = word.charAt(len - (i + 1));
			if (front != back) {
				return false;
			}
		}
		return true;
	}

	public static int factorial(int num) {
		int sum = num;
		while (num != 1) {
			num = num - 1;
			sum *= num;
		}
		return sum;
	}

	public static int combination(int n, int k) {
		return (factorial(n)) / (factorial(k) * (factorial(n - k)));
	}

	public static int permutation(int n, int k) {
		return (factorial(n)) / (factorial(n - k));
	}

	public static void intSwap(int i, int j) {
		j = j + i;
		i = j - i;
		j = j - i;
		System.out.println("i is now equal to " + i);
		System.out.println("j is now equal to " + j);
	}

	public static void evenOrOdd(int num) {
		System.out.print(num);
		if (num > 0) {
			while (num > 1)
				num -= 2;
		} else {
			while (num < 1) {
				num += 2;
			}
		}
		if (num == 1) {
			System.out.println(" is odd");
		} else {
			System.out.println(" is even");
		}
	}

	public static void pascalTriangle(int row) {
		System.out.print("Pascals Triangle at row " + row + " is 1 ");
		if (row != 0) {
			for (int i = 1; i < row; i++) {
				System.out.print(combination(row, i) + " ");
			}
			System.out.print("1");
		}
	}

	public static int fibonacciRecursive(int num) {
		if (num == 1 || num == 2) {
			return 1;
		}
		return (fibonacciRecursive(num - 1) + fibonacciRecursive(num - 2));
	}

	public static int fibonacciIteratively(int num) {
		if (num == 1 || num == 2) {
			return 1;
		}
		int first = 1;
		int secound = 1;
		num -= 2;
		while (num > 0) {
			first += secound;
			secound += first;
			num -= 2;
		}
		if (num % 2 == 0) {
			return secound;
		}
		return first;
	}

	/*
	 * public static String reverseString(String word) { }
	 */
	public static void main(String[] args) {
		System.out.println("Palendrome Tests");
		System.out.println(palendrome("racecar"));// palendrome test 1
		System.out.println();
		System.out.println(palendrome("car")); // palendrome test 2
		System.out.println();
		System.out.println("Factorial Tests");
		System.out.println(factorial(5));// factorial test 1
		System.out.println();
		System.out.println(factorial(25));// factorial test 2
		System.out.println();
		System.out.println("Combination Tests");
		System.out.println(combination(5, 2));// combination test 1
		System.out.println();
		System.out.println(combination(10, 3));// combination test 2
		System.out.println();
		System.out.println("Permutation Tests");
		System.out.println(permutation(5, 2));// permutation test 1
		System.out.println();
		System.out.println(permutation(10, 3));// permutation test 2
		System.out.println();
		System.out.println("Int Swap Tests");
		intSwap(-7, 12); // int swap test 1
		System.out.println();
		intSwap(0, 5); // int swap test 2
		System.out.println();
		System.out.println("Even or Odd Tests");
		evenOrOdd(10); // even or Odd test 1
		System.out.println();
		evenOrOdd(101); // even or Odd test 2
		System.out.println();
		evenOrOdd(1002); // even or Odd test 3
		System.out.println();
		evenOrOdd(-15); // even or Odd test 4
		System.out.println();
		evenOrOdd(0); // even or Odd test 5
		System.out.println();
		System.out.println("Pascal Triangle Tests");
		pascalTriangle(0); // pascal test 1
		System.out.println();
		pascalTriangle(9); // pascal test 2
		System.out.println();
		System.out.println("Fibonacci Recursive Tests");
		System.out.println(fibonacciRecursive(10)); // recursive fibonacci test 1
		System.out.println();
		System.out.println(fibonacciRecursive(25)); // recursive fibonacci test 2
		System.out.println();
		System.out.println("Fibonacci Iteratively Tests");
		System.out.println(fibonacciIteratively(10)); // iterative fibonacci test 1
		System.out.println();
		System.out.println(fibonacciIteratively(25)); // iterative fibonacci test 2
		System.out.println();
	}

}
