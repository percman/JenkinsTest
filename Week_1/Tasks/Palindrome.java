package DayOneHomework;

public class Palindrome
{
	
	public String isPalindrome(String string) 
	{
		int length = string.length(); // Get string length
		for(int i = 0;i<length/2;i++) // Compare current ends of the string
		{
			if(string.charAt(i) != string.charAt(length - 1)) 
			{
				return "no";
			}
			length--;
		}
		
		return "yes";
	}
}
