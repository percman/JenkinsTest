package com.bank.dao;

import java.util.List;
import com.bank.model.Product;

public interface ProductDao {

	List<Product> getAllProducts();
	Product getProduct(Product productName);
	boolean insertProduct(Product product);
	boolean deleteProduct(String product);
	int getProductId(Product product);
}
