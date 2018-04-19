package com.revature.dayonehw;

import java.util.Arrays;
import java.util.Scanner;

public class PalindromeTest extends ReverseString{

	public static Boolean isPalindrome(String testword) {
		if(testword.equals(reverseString(testword)))
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		String word;
		
	    Scanner scanner = new Scanner( System.in );
	    System.out.print( "Type the string you would to see tested: " );
	    word = scanner.nextLine();
	    
	    System.out.println(isPalindrome(word));
	}
}
