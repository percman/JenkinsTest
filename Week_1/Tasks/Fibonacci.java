package DayOneHomework;

public class Fibonacci 
{
	// Returns the nth Fibonacci number using recursion. A fib number is equal to the sum of the two previous numbers which makes for
	// and elegant recursive solution.
	public int recursiveFib(int n) 
	{
		if(n==0) // Base case
		{
			return 0;
		}
		else if(n==1) // Other base case
		{
			return 1;
		}
		else // Recursive loop
		{
			return recursiveFib(n-1) + recursiveFib(n-2);
		}
	}
	
	// Returns the nth Fibonacci number using iteration.
	public int iterativeFib(int n) 
	{
		int sum = 0;
		int x = 0;
		int y = 1;
		for(int i = 0;i<n;i++) 
		{
			x = y;
			y = sum;
			sum = x + y;
		}
		
		return sum;
	}
}
