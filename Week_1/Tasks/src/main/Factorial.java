package main;

public class Factorial {	
	public static int iterative(int index) {
		if(index < 0)
			return -1;
		
		int result = 1;
		
		for(int i=1; i<=index; i++) {
			result = result * i;
		}
		
		return result;
	}
	
	public static int recursive(int index) {
		if(index < 0)
			return -1;
		if(index == 0 || index == 1)
			return 1;
		else 
			return recursive(index-1) * index;
	}
}
