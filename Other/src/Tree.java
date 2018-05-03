
public class Tree
{
	public void InsertionSort(Element[] alphabet)
	{
		/*
		 * This will grab the next element in the array and then compare it against all
		 * previous elements until it rests in the appropriate spot.
		 */
		for (int index = 0; index < alphabet.length; index++)
		{
			// Special case when we only have 1 element
			if (index != 0)
			{
				// As long as the there is a bigger element to the left, keep swapping the
				// values
				while (Integer.parseInt(alphabet[index].getValue()) < Integer.parseInt(alphabet[index - 1].getValue()))
				{
					// Rearrange the elements in the array
					Element temp = alphabet[index];
					alphabet[index] = alphabet[index - 1];
					alphabet[index - 1] = temp;
					// Special case where we don't want to compare the first element to what comes
					// before
					if (index > 1)
					{
						index--;
					}
				}
			}
		}
	}

	public Element generateTree(Element[] alphabet)
	{
		// Read in the first two elements for the first two leaves
		int index = 0;
		int nextElementIndex = 0;
		while (index < 24)
		{
			// Create a new element
			Element nextElement = new Element();
			// If element on the left is longer than element on the right then swap them
			if (alphabet[index+1].getLetter().length() > alphabet[index].getLetter().length()) {
				Element temp = alphabet[index];
				alphabet[index] = alphabet[index+1];
				alphabet[index+1] = temp;
			}
			// If element on the left comes after element on the right then 
			else if (alphabet[index+1].getLetter().compareTo(alphabet[index].getLetter()) > 0) {
				Element temp = alphabet[index];
				alphabet[index] = alphabet[index+1];
				alphabet[index+1] = temp;
			}
			// Make those the children of the new Element
			nextElement.setRightChild(alphabet[index]); // set the parent of the right child
			nextElement.setLeftChild(alphabet[index + 1]); // set the parent of the left child
			// Put the combined quantities into the new element
			nextElement.setValue(Integer.parseInt(alphabet[index].getValue()) // This adds the numbers
					+ Integer.parseInt(alphabet[index + 1].getValue()) + "");
			// This combines the Strings
			nextElement.setLetter(alphabet[index].getLetter() + "" + alphabet[index + 1].getLetter());
			// Remove the two elements we have used
			alphabet[index] = null; // set this one to null
			alphabet[index + 1] = nextElement; // overwrite this one with the new parent
			nextElementIndex = index + 1; // Get the index of the newly inserted element
			// Bubblesort the nextElement into the correct place
			while (Integer.parseInt(alphabet[nextElementIndex].getValue()) > Integer
					.parseInt(alphabet[nextElementIndex + 1].getValue()))
			{
				Element temp = alphabet[nextElementIndex];
				alphabet[nextElementIndex] = alphabet[nextElementIndex + 1];
				alphabet[nextElementIndex + 1] = temp;
				if (nextElementIndex < 24)
				{
					nextElementIndex++;
				}
			}

			index++;
		}

		// Special case for last root node
		// Create a new element
		Element nextElement = new Element();
		// If element on the left is longer than element on the right then swap them
		if (alphabet[index+1].getLetter().length() > alphabet[index].getLetter().length()) {
			Element temp = alphabet[index];
			alphabet[index] = alphabet[index+1];
			alphabet[index+1] = temp;
		}
		// If element on the left comes after element on the right then 
		else if (alphabet[index+1].getLetter().compareTo(alphabet[index].getLetter()) > 0) {
			Element temp = alphabet[index];
			alphabet[index] = alphabet[index+1];
			alphabet[index+1] = temp;
		}
		// Make that element the parent of the first two elements in the array
		nextElement.setRightChild(alphabet[index]); // set the parent of the right child
		nextElement.setLeftChild(alphabet[index + 1]); // set the parent of the left child
		// Put the combined quantities into the new element
		nextElement.setValue(Integer.parseInt(alphabet[index].getValue()) // This adds the numbers
				+ Integer.parseInt(alphabet[index + 1].getValue()) + "");
		// This combines the Strings
		nextElement.setLetter(alphabet[index].getLetter() + "" + alphabet[index + 1].getLetter());
		// Remove the two elements we have used
		alphabet[index] = null; // set this one to null
		alphabet[index + 1] = nextElement; // overwrite this one with the new parent
		
		return nextElement;
	}
	
	public void displayTree(Element currentRoot) {
		if(currentRoot == null)
		{
			// System.out.println("penguins"); - Debugger code
			return;
		}

		System.out.print(currentRoot.getLetter() + " " + currentRoot.getValue());
		System.out.println("  ");
		displayTree(currentRoot.getLeftChild());
		
		displayTree(currentRoot.getRightChild());
	}
	
	public String traverseTreeWithLetters(Element currentRoot, String targetString, String huffmanValue) {
		//System.out.println(currentRoot.getChildLeft().getLetter());
		if (currentRoot.getLeftChild() == null && currentRoot.getLeftChild() == null) {
			return huffmanValue;
		}
		else if (currentRoot.getLeftChild().getLetter().contains(targetString)) {
			huffmanValue += 1;
			currentRoot = currentRoot.getLeftChild();
			huffmanValue = traverseTreeWithLetters(currentRoot,targetString,huffmanValue);
		}
		else if (currentRoot.getRightChild().getLetter().contains(targetString)) {
			huffmanValue += 0;
			currentRoot = currentRoot.getRightChild();
			huffmanValue = traverseTreeWithLetters(currentRoot,targetString,huffmanValue);
		}
		return huffmanValue;
	}
}
