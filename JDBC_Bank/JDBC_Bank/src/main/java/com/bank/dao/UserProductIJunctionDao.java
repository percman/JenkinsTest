package com.bank.dao;

import java.util.List;
import com.bank.model.UserProduct;

public interface UserProductIJunctionDao {
	
	List<UserProduct> getAllUserProduct();
	UserProduct getCart(String user_firstname, String user_lastname);

}
