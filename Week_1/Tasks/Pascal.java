package DayOneHomework;

public class Pascal
{
	// Generate the nth row of pascals triangle
	public String generateRow(int n) 
	{
		Factorial fact = new Factorial();
		String output = "";
		for(int i=0;i<=n;i++) 
		{
			output += (fact.Factorial(n))/(fact.Factorial((n-i)) * fact.Factorial(i)) + " ";
		}
		
		return output;
	}
}
