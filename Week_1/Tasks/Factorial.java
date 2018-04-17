package DayOneHomework;

public class Factorial
{
	
	public int Factorial(int n)
	{
		if(n<=1) // base case
		{
			return 1;
		}
		else 
		{
			return n * Factorial(n-1);
		}
	}
}
