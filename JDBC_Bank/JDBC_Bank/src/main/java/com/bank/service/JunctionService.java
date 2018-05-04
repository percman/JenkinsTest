package com.bank.service;

import java.util.List;

import com.bank.dao.UserProductJunctionDao;
import com.bank.dao.UserProductJunctionDaoImpl;
import com.bank.model.Product;
import com.bank.model.User;
import com.bank.model.UserProduct;

public class JunctionService {

private static UserProductJunctionDao dao = UserProductJunctionDaoImpl.getInstance();
	
	private JunctionService() {}
	
	public static List<UserProduct> getAllUserProduct(){
		return dao.getAllUserProduct();
	}
	
	public static UserProduct getCart(String user_firstname, String user_lastname) {
		return dao.getCart(user_firstname, user_lastname);
		
	}
	
	public static boolean addToCart(User user,Product product ) {
		return dao.addToCart(user, product);
		
	}
	
}
