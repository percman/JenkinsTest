package com.revature.evenodd;

import java.util.Scanner;

public class EvenTest {

	public static void test(int a) {
		int half = a/2;
		float pointFive = (float)a/2;
		if (pointFive == (float)half){
			System.out.println("Even");
		}
		else System.out.println("Odd");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter an integer to test if it is even or odd: ");
		int x = sc.nextInt();
		test(x);
		sc.close();
	}
}
