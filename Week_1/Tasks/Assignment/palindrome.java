package com.revature.DayOne.Assignment;

public class palindrome {

	public static void main(String[] args) {
		System.out.println(isPalindrome("race car"));
	}
	
	public static boolean isPalindrome(String s) {
		s = s.toLowerCase();
		s= s.replaceAll(" ","");
		if(s.length() <= 1)
			return true;
		char front = s.charAt(0);
		char end = s.charAt(s.length()-1);
		if(front != end)
			return false;
		return isPalindrome(s.substring(1, s.length()-1));
	}

}
