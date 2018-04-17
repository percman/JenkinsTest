package com.revature.intswap;

import java.util.Scanner;

public class Swap {
	public static void switcheroo(int x, int y) {
		x = x+y;
		y = x-y;
		x = x-y;
		System.out.println("Your numbers, in order, are now " + x + " and " + y + ".");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter two integers to swap: ");
		int a = sc.nextInt();
		int b = sc.nextInt();
		switcheroo(a, b);
		sc.close();
	}
}
