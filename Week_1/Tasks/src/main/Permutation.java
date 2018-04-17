package main;
import main.Factorial;

public class Permutation {
	public static int test(int n, int k) {
		if(k > n)
			return 0;
		else
			return Factorial.recursive(n) / Factorial.recursive( n - k );
	}
}
