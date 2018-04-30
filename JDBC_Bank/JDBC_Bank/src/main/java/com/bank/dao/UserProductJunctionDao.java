package com.bank.dao;

import java.util.List;

import com.bank.model.Product;
import com.bank.model.User;
import com.bank.model.UserProduct;

public interface UserProductJunctionDao {
	
	List<UserProduct> getAllUserProduct();
	UserProduct getCart(String user_firstname, String user_lastname);
	boolean addToCart(User user,Product product );

}
