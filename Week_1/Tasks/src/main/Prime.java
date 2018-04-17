package main;

public class Prime {
	public static boolean test(int value) {
		if(value < 2)
			return false;
		
		for(int i=2; i<value; i++) 
			if(value%i == 0)
				return false;
		
		return true;
	}
}
