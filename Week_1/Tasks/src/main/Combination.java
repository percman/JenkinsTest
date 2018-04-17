package main;
import main.Factorial;

public class Combination {
	public static int test(int n, int k) {
		if(k > n)
			return 0;
		if(k <= n)
			return Factorial.recursive(n) / Factorial.recursive(k) / Factorial.recursive( n - k );
		else
			return 2147483647;
			
	}
}
