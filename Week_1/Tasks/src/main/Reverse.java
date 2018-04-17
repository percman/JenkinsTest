package main;
import java.lang.IndexOutOfBoundsException;

public class Reverse {
	public static String test(String string) {
		int length = 0;
		while(true) {
			try {
				string.charAt(length);
				length++;
			} catch(IndexOutOfBoundsException e) {
				break;
			}
		}
		char[] returnValue = new char[length];
		for(int i=0; i<length; i++) {
			int newIndex = length - 1 - i;
			returnValue[newIndex] = string.charAt(i);
		}
		return new String(returnValue);
	}
}
