package com.revature.DayOne.Assignment;

public class OddorEven {

	public static void main(String[] args) {
		System.out.println(OddEven(231));
	}
	
	public static String OddEven(int num) {
		if(num == 0)
			return "Even";
		if(num == 1)
			return "Odd";
		return OddEven(num -= 2);
	}
	
}
