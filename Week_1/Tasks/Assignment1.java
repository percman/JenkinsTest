package assignment1;
/**
	Fibonacci (recursively / iteratively)
	Factorial / Combination / Permutation
	Pascal's triangle (print the nth row, triangle)
	Reverse a string (only using charAt)
	Program to test if string is a palindrome
	Write a method that swaps two int's without using a third
	Write a method that determines if a number is even or odd without modulus

 * @author David Kim
 */
public class Assignment1 {

	public static int fibRecursive(int n) {
		if (n == 1 || n == 2)
			return 1;
		else
			return (fibRecursive(n - 2) + fibRecursive(n - 1));
	}
	
	public static int fibIterative(int n) {
		int x = 1;
		int y = 1;
		for (int i = 1; i < n; i++)
		{
			int temp = x;
			x = y;
			y = temp + y;
		}
		return x;
	}   
	
	public static int nFactorial(int n) {
		if (n == 0)
			return 1;
		else
			return (n * nFactorial(n - 1));
	}
	
	public static int combination(int n, int r) {
		
		int difference = n - r;
		
		int nFactorial = 1;
		while (n != 0) {
			nFactorial = nFactorial * n;
			n = n - 1;
		}
		
		int rFactorial = 1;
		while (r != 0) {
			rFactorial = rFactorial * r;
			r = r - 1;
		}
		
		int nrFactorial = 1;
		while (difference != 0) {
			nrFactorial = nrFactorial * difference;
			difference = difference - 1;
		}
		
		return nFactorial / (rFactorial * nrFactorial);
		
	}
	
	public static int permutation(int n, int r) {
		int difference = n - r;
		
		int nFactorial = 1;
		while (n != 0) {
			nFactorial = nFactorial * n;
			n = n - 1;
		}
		
		int nrFactorial = 1;
		while (difference != 0) {
			nrFactorial = nrFactorial * difference;
			difference = difference - 1;
		}
		
		return nFactorial / nrFactorial;
	}
	
	public static void pascals(int n) {
		int[][] array = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if(i == j || j == 0)
					array[i][j] = 1;
				else
					array[i][j] = array[i - 1][j - 1] + array[i - 1][j];
				
			}
		}
		
		for (int i = 0; i < array.length; i++)
			if(array[n - 1][i] != 0)
				System.out.print(array[n - 1][i] + " ");
		
		System.out.println();
		
		for (int i = 0; i < array.length; i++ ) {
			for (int j = 0; j < array.length; j++) {
				if (array[i][j] != 0)
				System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}	
	}   
	
	public static String reverse(String s) {
		String result = "";
		for (int i = 0; i < s.length(); i++) {
			result = s.charAt(i) + result;
		}
			
		return result;
	}
	
	public static boolean palindrome(String s) {
		String v ="";
		v.concat(s);
		v.toLowerCase();
		String u = reverse(v);
		if (v.equals(u))
			return true;
		else 
			return false;
	}
	
	public static void swap(int a, int b) { // a = 1, b = 3
		a = b - a;   // a = 2, b = 3
		b = b - a;   // a = 2, b = 1
		a = a + b;  
		
		System.out.println(a + " " + b);
	}
	
	public static String evenOrOdd(int n) {
		String e = "even";
		String o = "odd";
		
		if ((n / 2) * 2 == n)
			return e;
		else
			return o;
	}
	
	
	
	

	public static void main(String[] args) {
		
		int a = fibRecursive(7);
		System.out.println(a);
		
		int b = fibIterative(3);
		System.out.println(b);
		
		int c = nFactorial(3);
		System.out.println(c);
		
		int d = combination(5, 2);
		System.out.println(d);
		
		int e = permutation(10, 3);
		System.out.println(e);
		
		String s = "hello";
		System.out.println(reverse(s));  
		
		String t = "A Santa at Nasa";
		System.out.println(palindrome(t)); 
		
		System.out.println(evenOrOdd(3));
		
		swap(15, 52); 
		
		pascals(6);

	}
}
