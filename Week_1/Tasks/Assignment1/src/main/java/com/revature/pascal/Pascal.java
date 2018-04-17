package com.revature.pascal;

import java.util.Scanner;

public class Pascal {
	public static void triangle(int n) {
		for(int i = 0; i<=n; i++) {
			for(int j = 0; j<=n; j++) {
				if(i == n) {
					System.out.print(value(i, j)+ " ");
				}
			}
		}

	}
	
	public static int value(int n, int k) {
		if (k == 0) return 1;
		else if (n == k) return 1;
		else return value(n-1, k-1) + value(n-1, k);
	}
	
	public static void main(String[] args) {
		System.out.println("This will print the nth row of Pascal's Triangle");
		System.out.println("With n=0 being the top of the triangle");
		System.out.print("Enter the n of your choosing: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		triangle(n);
		sc.close();
	}
}
