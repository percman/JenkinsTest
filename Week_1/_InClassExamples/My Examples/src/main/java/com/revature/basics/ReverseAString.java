package com.revature.basics;

public class ReverseAString {

	public static String reverse(String s) {
		int index = 0;
		String reversed = "";
		
		while(true) {
			try {
				s.charAt(index);
				index++;
			} catch (Exception e) {
				index--;
				for (int i = -1; index > i; index--) {
					reversed += s.charAt(index);
				}
				break;
			}
		}
		
		return reversed;
	}
	
	public static void main(String[] args) {
		System.out.println(ReverseAString.reverse("Cameron"));
	}
}
