import java.util.ArrayList;

public class Shop {

//	removeFromCart
	public static ArrayList<Item> cart = new ArrayList<>();
	public static int numItems = 1;
	public static double total = 0.0;
	
	
	public static void addToCart(Item newItem) {
		total += newItem.getPrice();
		cart.add(newItem);
		numItems++;		
	}

	public static void removeFromCart(Item removingItem){
		cart.remove(removingItem);
	}
	
	public static double getTotal() {
		return total;
	}

	public static void setTotal(double total) {
		Shop.total = total;
	}

	
}
