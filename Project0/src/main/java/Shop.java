import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Shop implements Serializable{

	private static final long serialVersionUID = -3749034210857064800L;
	//public static ArrayList<Item> cart = new ArrayList<>();
	public static int numItems = 0;
	public static double total = 0.0;
	static Map <Integer,Item> cart = new HashMap<>();
	public static void addToCart(Item newItem) {
		total += newItem.getPrice();
		cart.put(numItems,newItem);
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
