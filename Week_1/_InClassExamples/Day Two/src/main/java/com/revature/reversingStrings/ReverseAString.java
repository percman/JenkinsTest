package com.revature.reversingStrings;

public class ReverseAString {

	/* This method will reverse a String only using charAt() */
	public static String reverse(String input) {
		String toBeReturned = "";
		int index = 0;
		while (true) {
			try {
				input.charAt(index);
				index++;
			} catch (IndexOutOfBoundsException e) {
				index--;
				for (int i = -1; index > i; index--) {
					toBeReturned += input.charAt(index);
				}
				break;
			}
		}
		return toBeReturned;
	}
}
