package main;

import java.util.List;

public class Swap {
	public static List<Integer> test(List<Integer> integers) {
		int one = integers.get(0).intValue();
		int two = integers.get(1).intValue();
		
		one = one + two;
		two = one - two;
		one = one - two;
		
		integers.set(0, new Integer(one));
		integers.set(1, new Integer(two));
		
		return integers;
	}
}
