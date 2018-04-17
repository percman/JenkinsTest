package com.revature.DayOne.Assignment;

public class ReverseString {

	public static void main(String[] args) {
		System.out.println(reverse("MISSISSPPI"));
		

	}
	
	public static String reverse(String s){
		String r = "";
		int size = 0;
		try {
			while(true) {
				s.charAt(size);
				size++;
			}
		}catch(Exception e) {
			for(int i = size-1; i >= 0; i--) {
				r += s.charAt(i);
			}
		}		
		return r;
		
	}

}
