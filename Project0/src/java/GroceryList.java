import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class GroceryList implements Serializable{


	private static final long serialVersionUID = 1541672010742004469L;

	String item = null;
	double price = 0.00;
	int numberInStock = 0;
	
	Item icecream = new Item("Ice cream", 3.26, 30);
	Item flour = new Item("Flour", 4.00, 10);
	Item butter = new Item("Butter", 1.23, 16);
	Item sugar = new Item("Sugar", 5.00, 15);
	Item vanilla = new Item("Vanilla", 2.13, 60);
	Item bakingPowder = new Item("Baking Powder", 2.50, 100);
	Item salt = new Item("Salt", 2.79, 100);
	Item cream = new Item("Cream", 6.73, 5);
	Item cinnamon = new Item("Cinnamon", .95, 30);
	Item bacon = new Item("Bacon", 15.95, 4);
	Item milk = new Item("Milk", 3.28, 35);

	Map <Integer,Item> items = new HashMap<>();
	
	public GroceryList() {
		super();
		items.put(1,icecream);
		items.put(2,flour);
		items.put(3,butter);
		items.put(4,sugar);
		items.put(5,vanilla);
		items.put(6,bakingPowder);
		items.put(7,salt);
		items.put(8,cream);
		items.put(9,cinnamon);
		items.put(10,bacon);
		items.put(11,milk);
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public int getNumberInStock() {
		return numberInStock;
	}

	public void setNumberInStock(int numberInStock) {
		this.numberInStock = numberInStock;
	}

	public GroceryList(String newItem, double newPrice, int newNumberInStock) {
		item = newItem;
		price = newPrice;
		numberInStock = newNumberInStock;
	}

}
