package com.revature.dayonehw;

import java.util.Arrays;
import java.util.Scanner;

public class ReverseString {
	
	public static int countString(String word) {
		int count = 0;
		
	    try {
	        while (true) {
	            word.charAt(count);
	            count++;
	        }
	    } catch (IndexOutOfBoundsException e) {
	       return count;
	    }
	
	}
	
	public static String reverseString(String word) {
		int count = countString(word);
		char[] arr;
		arr = new char[count];
		
		for(int i = 0; i < count; i++) {
			arr[count - i - 1] = word.charAt(i);
		}
		
		String reversedWord = new String(arr);
	    
	    return reversedWord;
	    
	}
	
	public static void main(String[] args) {
		String word;
		
	    Scanner scanner = new Scanner( System.in );
	    System.out.print( "Type the string you would like reversed: " );
	    word = scanner.nextLine();
	    
	    System.out.println(reverseString(word));

		
	}

}
