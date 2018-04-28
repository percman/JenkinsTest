package com.revature.dao.movie;

import java.io.Serializable;

public class Movie implements Serializable {
	private static final long serialVersionUID = 8173025665216010437L;
	private String title;
	private int inStock,id;
	public Movie(String title,int inStock) {
		super();
		this.inStock = inStock;
		this.title = title;
	}
	
	public Movie() {
		
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		Movie other = (Movie) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public int getInStock() {
		return inStock;
	}

	public void setInStock(int inStock) {
		this.inStock = inStock;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
