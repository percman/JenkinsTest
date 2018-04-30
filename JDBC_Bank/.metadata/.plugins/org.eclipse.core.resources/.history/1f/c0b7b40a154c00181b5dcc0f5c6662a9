package com.bank.model;

import java.io.Serializable;

public class UserProduct implements Serializable{

	private static final long serialVersionUID = -8155859916197069318L;
	private int cart_id;
	private int user_id;
	private int product_id;
	
	public UserProduct() {}

	public UserProduct(int cart_id, int user_id, int product_id) {
		super();
		this.cart_id = cart_id;
		this.user_id = user_id;
		this.product_id = product_id;
	}

	public int getCart_id() {
		return cart_id;
	}

	public void setCart_id(int cart_id) {
		this.cart_id = cart_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getProduct_id() {
		return product_id;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cart_id;
		result = prime * result + product_id;
		result = prime * result + user_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserProduct other = (UserProduct) obj;
		if (cart_id != other.cart_id)
			return false;
		if (product_id != other.product_id)
			return false;
		if (user_id != other.user_id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserProduct [cart_id=" + cart_id + ", user_id=" + user_id + ", product_id=" + product_id + "]";
	}
	
	
	

}
