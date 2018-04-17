package main;

public class Fibonacci {
	public static int iterative(int index) {
		int left = 1;
		int right = 1;
		int sum = 2;
		for(int i=0; i<index; i++) {
			left = right;
			right = sum;
			sum = left + right;
		}
		return left;
	}
	
	public static int recursive(int index) {
		if(index == 0 || index == 1)
			return 1;
		else
			return recursive(index-2) + recursive(index-1);
	}
}
