package DayOneHomework;

public class ReverseString 
{
	
	public String reverseString(String string) 
	{
		String tempString = "";
		int stringLength = 0;
		
		int x = 0;
		// First get the length of the string
		try 
		{
			while(string.charAt(x) != 0)
			{
				tempString += string.charAt(x);
				stringLength++;
				x++;
			}
		}
		catch (StringIndexOutOfBoundsException e)
		{
			// We are expecting this error and want it to go away
		}
		
		stringLength--; // Reset length back to correct length
		string = ""; // Empty the original string
		
		// Place the characters back into the string in reverse order
		for(int i = stringLength; i>=0; i--)
		{
			string += tempString.charAt(i);
		}
		
		return string;
	}
}
