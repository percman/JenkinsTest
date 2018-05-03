import java.io.*;

public class HuffmanEncoding 
{
	public static void main(String[]args) 
	{
		// Open File
		FileReader input = null;
		try
		{
			input = new FileReader(args[0]);
		} 
		catch (FileNotFoundException e)
		{
			System.out.print("Error opening file");
			return;
		}	
		
		// Declare an array of elements of length 26
		Element[] alphabet = new Element[26];
		// Fill the array with 26 elements
		for(int i = 0;i<26;i++)
		{
			alphabet[i] = new Element();
		}
		// Index of the element array
		int index = 0;
		// Symbol to keep track of what we read in from the file
		int symbol = 0;
		boolean keepSymbol = false;
		do 
		{
			try 
			{
				// Only read the next symbol if the most recent read was actually used.
				if(keepSymbol == false) 
				{
					symbol = input.read();
				}
				// Since we know we will use the symbol this time around, we want to use the next one
				else
				{
					keepSymbol = false;
				}
				
				// Check for a Letter
				if((symbol >= 65 && symbol <= 90) || (symbol >= 97 && symbol <= 122))
				{
					alphabet[index].setLetter((char)symbol + "");
				}
				
				// Check for a number
				else if(symbol >= 48 && symbol <= 57) 
				{
					alphabet[index].setValue((char)symbol + "");
					// Check if the number has more than 1 digit
					symbol = input.read();
					if(symbol >= 48 && symbol <= 57) 
					{
						String tempValue = alphabet[index].getValue() + "" + (char)symbol;
						alphabet[index].setValue(tempValue);
					}
					// Otherwise keep track of that value for the next cycle through
					else 
					{
						keepSymbol = true;
					}
				}
				
				// Once both values have been filled, move to the next index. This will mitigate input errors
				if(alphabet[index].getLetter() != null && alphabet[index].getValue() != null) 
				{
					index++;
				}
				
				// Discard all other symbols _CHECK FOR SPACES AND DASHES HERE
				else if(symbol != -1)
				{
					// Invalid symbol detected
					// System.out.println("Invalid symbol detected");
				}
			}
			catch(IOException e)
			{
				System.out.println("Error reading file");
			}
		} while (symbol != -1);
		
		TreeFunctions.TreeMethods(alphabet);
	}
	
}