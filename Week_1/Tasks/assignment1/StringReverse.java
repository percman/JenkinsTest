package com.revature.assignment1;

public class StringReverse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String a = "ssass";
		String temp=null;
		
		for(int i=a.length()-1; i>=0; i--) {
			System.out.print(a.charAt(i));
			temp = Character.toString(a.charAt(i));
		}
		
		StringBuilder s = new StringBuilder();
		s.append(a);
		s = s.reverse();
		
		if(a.equals(s)) {
			System.out.println("The string is palindrome");
		}
		System.out.println("the string is not palindrome");
		
		
		
	}

}
