package com.revature.homework;

// Fibonacci (recursively / iteratively)		iterative DONE			recursive   REQUIREMENTS WERE UNCLEAR
// Factorial / Combination / Permutation												ALL DONE
// Pascal's triangle (print the nth row, triangle)
// Reverse a string (only using charAt)													ALL DONE
// Program to test if string is a palindrome											ALL DONE
// Write a method that swaps two int's without using a third							ALL DONE
// Write a method that determines if a number is even or odd without modulus			ALL DONE


public class Homework {
	public static void main(String[] args) {
		Homework problem = new Homework();
		
		System.out.println("Here is the solution for Fibonacci recursion");
		System.out.println(problem.fibonacciRecursion(5));
		System.out.println();
		
		
		System.out.println("Here is the answer for Fibonacci iteration");
		problem.fibonacciIteration(5);
		System.out.println();
		System.out.println();
		
		System.out.println("Here is the answer for factorial");
		System.out.println(problem.factorial(5));
		System.out.println();
		
		System.out.println("Here is the answer for combination");
		problem.combination(2, 1);
		System.out.println();
		
		System.out.println("Here is the answer for permutation");
		problem.permutation(5, 3);
		System.out.println();
		
		System.out.println("Here is the answer for Pascal's triangle, nth row");
		problem.nPascal(7);
		System.out.println();
		
		System.out.println("Here is the answer for Pascal's triangle");
		problem.trianglePascal(7);
		System.out.println();
		
		
		System.out.println("Reverse a string");
		System.out.println(problem.reverse("Reverse a string"));
		System.out.println();
		
		
		System.out.println("Is emordnilap a palandrome?");
		problem.palindrome("emordnilap");
		System.out.println();
		System.out.println("Is rotator a palindrome?");
		problem.palindrome("rotator");
		System.out.println();
		
		
		System.out.println("swap two ints without using a third");
		problem.swapInts(32, 5);
		System.out.println();
		
		
		System.out.println("even or odd?");
		System.out.println("57?");
		problem.evenOdd(57);
		System.out.println();
		
	}
	
	
	
	public int fibonacciRecursion(int n) {
		
		if(n == 1) {
			return 1;
		}
		else if(n == 2) {
			return 1;
		}
		else {
			return fibonacciRecursion(n-1) + fibonacciRecursion(n-2);			
		}
	}
	
	public void fibonacciIteration(int total) {
		int[] fib = new int[total];
		fib[0] = 1;
		fib[1] = 1;
		if(total == 1) {
			System.out.println("1");
		}
		else if(total == 2) {
			System.out.println("1, 1");
		}
		else {
			for(int i = 2; i < total; i++) {
				fib[i] = fib[i-1] + fib[i-2];
			}
		}
		
		// Printing
		
		System.out.print(fib[0]);
		
		for(int i = 1; i < total; i++) {
			System.out.print(", " + fib[i]);
		}
	}
	
	public int factorial(int base) {

		int total = 1;
		if(base == 0) {
			return 1;
		}
		for(int i = 2; i <= base; i++) {
			total = total*i;
		}
		return total;
	}
	
	public int combination(int n, int k) {
		int c = factorial(n) / (factorial(k) * factorial(n-k));
		System.out.println(c);
		return c;
	}
	
	public int permutation(int n, int k) {
		int c = factorial(n) /  factorial(n-k);
		System.out.println(c);
		return c;
	}
	
	public int combi(int n, int k) {
		int c = factorial(n) / (factorial(k) * factorial(n-k));
		return c;
	}
	
	public void nPascal(int row) {
		if(row == 0) {
			System.out.println("1");
		}
		else if(row == 1) {
			System.out.println("1, 1");
		}
		else {
			int[] output = new int[(row+1)];
			for(int column = 0; column <= row; column++) {
				output[(column)] = combi((row-1), (column-1)) + combi((row-1), (column));
			}
			// Printing
			System.out.print(output[0]);
			
			for(int i = 1; i <= row; i++) {
				System.out.print(", " + output[i]);
			}
			System.out.println();
		}
		
	}
	
	public void trianglePascal(int row) {
		for(int i = 0; i <= row; i++) {
			nPascal(i);
		}
	}
	
	public String reverse(String given) {
		int n = given.length()-1;
		String output = "";
		while(n >= 0) {
			char next = given.charAt(n);
			output = output + next;
			n--;
		}
		return output;
	}
	
	public void palindrome(String given) {
		String backwards = reverse(given);
		if(given.equals(backwards)) {
			System.out.println("This IS A palindrome");
		}
		else {
			System.out.println("This IS NOT A palindrome");
		}
	}
	
	public void swapInts(int first, int second) {
		System.out.println("The first integer is " + first);
		System.out.println("The second integer is " + second);
		System.out.println();
		
		first = first + second;
		second = first + second;
		first = second - first;
		second = second - first;
		second = second - first;
		
		System.out.println("The first integer is " + first);
		System.out.println("The second integer is " + second);
		
	}
	
 	public void evenOdd(int num) {
		if(num == 0) {
			System.out.println("The number provided was EVEN");
		}
		else if(num == 1) {
			System.out.println("The number provided was ODD");
		}
		else {
			evenOdd(num-2);
		}
	}
	
}
