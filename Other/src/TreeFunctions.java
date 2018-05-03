
public class TreeFunctions
{
	public static void TreeMethods(Element[] alphabet) {
		// Make a new tree
		Tree tree = new Tree();
		// Sort all the elements in the alphabet
		tree.InsertionSort(alphabet);
		// Generate the tree using that alphabet
		Element rootNode = tree.generateTree(alphabet);
		tree.displayTree(rootNode);
		// Method to traverse the tree given input
		String huffmanValue = tree.traverseTreeWithLetters(rootNode, "H", "");
		System.out.print(huffmanValue);
	}
}
