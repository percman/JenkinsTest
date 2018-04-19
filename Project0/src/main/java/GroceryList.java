
public class GroceryList {

	String item = null;
	double price = 0.00;
	int numberInStock = 0;
	
	public GroceryList() {
		super();
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

	//Creates a grocery list
	GroceryList icecream = new GroceryList("Ice cream", 3.26, 30);
	GroceryList flour = new GroceryList("Flour", 4.00, 10);
	GroceryList butter = new GroceryList("Butter", 1.23, 16);
	GroceryList milk = new GroceryList("Milk", 3.28, 35);
	GroceryList sugar = new GroceryList("Sugar", 5.00, 15);
	GroceryList vanilla = new GroceryList("Vanilla", 2.13, 60);
	GroceryList bakingPowder = new GroceryList("Baking Powder", 2.50, 100);
	GroceryList salt = new GroceryList("Salt", 2.79, 100);
	GroceryList cream = new GroceryList("Cream", 6.73, 5);
	GroceryList cinnamon = new GroceryList("Cinnamon", .95, 30);
	GroceryList bacon = new GroceryList("Bacon", 15.95, 4);
	//GroceryList bacon = new GroceryList("Cheese", 9.95, 12);
	

}
