package com.revature.fibonacci;

import java.util.Scanner;

public class Control {
	public static void main(String[] args) {
		Fibonacci solve = new Fibonacci();
		Scanner sc = new Scanner(System.in);
		System.out.println("Prints the first n numbers of the Fibonacci sequence using iteration");
		System.out.print("Choose n: ");
		int n = sc.nextInt();
		solve.iterative(n);
		System.out.println();
		System.out.println("Prints the nth value of the Fibonacci sequence using recursion");
		System.out.print("Choose n: ");
		n = sc.nextInt();
		System.out.println(solve.recursive(n));
		sc.close();
	}
}