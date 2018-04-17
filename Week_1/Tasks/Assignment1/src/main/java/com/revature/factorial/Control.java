package com.revature.factorial;

import java.util.Scanner;

public class Control {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Calculations solve = new Calculations();
		System.out.println("Solves for n factorial");
		System.out.println("And solves for combinations and permutations of n");
		System.out.print("Enter n: ");
		int n = sc.nextInt();
		System.out.print("Enter k for number of items: ");
		int k = sc.nextInt();
		System.out.println("n! = "+solve.factorial(n));
		System.out.println(solve.combinations(n, k)+ " combinations");
		System.out.println(solve.permutations(n, k)+" permutations");
		sc.close();
	}
}
