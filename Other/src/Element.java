
public class Element
{
	private String value;
	private String letter;
	private Element parent;
	private Element leftChild;
	private Element rightChild;
	
	// Getter and Setter for the value of the element
	public void setValue(String value) 
	{
		this.value = value;
	}
	public String getValue() 
	{
		return value;
	}
	
	// Getter and Setter for the letter of the element
	public void setLetter(String letter) 
	{
		this.letter = letter;
	}
	public String getLetter() 
	{
		return letter;
	}
	
	// Getter and Setter for the parent of the element
	public void setParent(Element parent) 
	{
		this.parent = parent;
	}
	public Element getParent()
	{
		return parent;
	}
	
	// Getter and Setter for the left Child of the element
	public void setLeftChild(Element leftChild) 
	{
		this.leftChild = leftChild;
	}
	public Element getLeftChild()
	{
		return leftChild;
	}
	
	// Getter and Setter for the right Child of the element
	public void setRightChild(Element rightChild) 
	{
		this.rightChild = rightChild;
	}
	public Element getRightChild()
	{
		return rightChild;
	}
}
