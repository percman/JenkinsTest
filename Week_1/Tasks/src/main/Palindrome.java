package main;

public class Palindrome {
	public static boolean test(String string) {
		string = string.toLowerCase();
		string = string.replaceAll("[!?, ]", "");
		String copy = new StringBuilder(string).reverse().toString();
		if(copy.equals(string))
			return true;
		else
			return false;
	}
}
