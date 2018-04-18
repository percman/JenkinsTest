package assignments;

import java.util.Scanner;

public class FirstAssigment 
{

	public static int Factorial(int n) 
	{
		int result = 1;
		
		if (n == 0)
			return 1;
		else 
			for (int i=2; i<=n; i++)
			{
				result *= i; 
			}
		return result;
	}

	public static int FibRecursive(int n)
	{
		if(n == 0)
			return 0;
		else if(n == 1)
		    return 1;
		else
		    return FibRecursive(n - 1) + FibRecursive(n - 2);
	}
	
	public static int FibIterative(int n)
	{		
			int fib[] = new int[n];
			
			fib[0]=1;
			fib[1]=1;
			
			for (int i=2; i<n; i++)
			{
				fib[i] = fib[i-1]+fib[i-2];
			}	
			
			
		return fib[n-1];
	}
	
	public static String ReverseString (String s)
	{
		String reversed = "";
		int length = s.length();
		
		for (int l=length-1;l>=0; l--)
        {
        reversed = reversed +s.charAt(l);
                   // System.out.println(n);
       // i++;
            }
	
		
		return reversed;
		
	}

	public static void main(String[] args) 
	{
		int n;
		//Scanner Declaration
		Scanner scan = new Scanner(System.in);
		
			
		System.out.println("Please enter a number to take the fib of");
		n = scan.nextInt();
	
		System.out.println("Using a recursive method: " + FibRecursive(n));
		System.out.println("Using a iterative method: " + FibIterative(n));
				
		//Finding factorial
		System.out.println("\nPlease enter a number to find the factorial of");
		int input = scan.nextInt();
		System.out.println(Factorial(input));
		
		//Using factorial for combinations and permutations
		System.out.println("Please enter an n value");
		n = scan.nextInt();
		System.out.println("Please enter an r value");
		int r = scan.nextInt();
		
		System.out.println("The permutation of n and r is: " + 
		(Factorial(n)/(Factorial((n-r)))));
		if (n == r)
		{
			System.out.println("The permutation of n and r is: 1");
		}
		else
		{
		System.out.println("The combination of n and r is: " + 
		Factorial(n)/(Factorial(r)*Factorial(n-r)));
		}		
		
		System.out.println("\nPlease enter a string for reversal");
		String reversable = scan.next();
		
		System.out.println("The reversed String is as follows: " + ReverseString(reversable));
		
		System.out.println("\nPlease enter your first number for swapping: ");
		int num1 = scan.nextInt();
		System.out.println("Please enter your second number for swapping: ");
		int num2 = scan.nextInt();
		
		num1 += num2;
		num2 = num1 - num2;
		num1 = num1 - num2;
		
		System.out.println("Your first number is now: " + num1 
				+ "\nYour second number is now: " + num2);

		System.out.println("\nPlease enter a number for evaluaton");
		n = scan.nextInt();
		
		while (n>1)
		{
			n = n-2;
		} 
		
		if (n == 1)
		{
			System.out.println("This is an odd number");
		}
		else
		{
			System.out.println("This is an even number");
		}
		
		
		
		
	}
}
