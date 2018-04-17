package com.revature.stringreverse;

import java.util.Scanner;

public class Reversal {

	public static String flipFlop(String input) {
		String backward = "";
		int j = 0;
		try {
			do{
				backward = input.charAt(j) + backward;
				j++;
			}while(input.charAt(j)!=0);
		}catch(Exception e) {}
		return backward;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a string to be reversed: ");
		String word = sc.next();
		System.out.println(flipFlop(word));
		if(word.equals(flipFlop(word))) System.out.println("The string is a palindrome!");
		else System.out.println("The string is not a palindrome.");
		sc.close();
	}
}
