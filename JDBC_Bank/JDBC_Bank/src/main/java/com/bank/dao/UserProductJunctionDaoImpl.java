package com.bank.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.Product;
import com.bank.model.User;
import com.bank.model.UserProduct;
import com.bank.service.ProductService;
import com.bank.service.UserService;
import com.bank.util.ConnectionUtil;

public class UserProductJunctionDaoImpl implements UserProductJunctionDao{

	private static UserProductJunctionDaoImpl instance;
	
	private UserProductJunctionDaoImpl() {}
	
	public static UserProductJunctionDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserProductJunctionDaoImpl();
		}
		return instance;
	}

	@Override
	public List<UserProduct> getAllUserProduct() {
		List<UserProduct> cart = new ArrayList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT u.user_id, u.user_firstname,  u.user_lastname, "
					+ "p.product_name, p.product_price FROM USER_PRODUCT_JUNCTION j " 
					+ "INNER JOIN user_table u ON j.user_id = u.user_id " 
					+ "INNER JOIN product p ON j.product_id = p.product_id");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cart.add(new UserProduct(rs.getInt("user_id"), rs.getInt("product_id")));
			}
			return cart;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public UserProduct getCart(String user_firstname, String user_lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addToCart(User user, Product product) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL insert_junction(?,?)}");
			stmt.setInt(++index, UserService.getUserId(user));
			stmt.setInt(++index, ProductService.getProductId(product));
			return stmt.executeUpdate() > 0;
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

}