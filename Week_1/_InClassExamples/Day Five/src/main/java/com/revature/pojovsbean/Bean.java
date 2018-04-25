package com.revature.pojovsbean;

import java.io.Serializable;

public class Bean implements Serializable {

	private static final long serialVersionUID = 8430483253117161040L;
	private String name;
	
	public Bean() {}
	
	public Bean(String name) {
		this.name = name;
	}
	
	static class SomeInnerClass {
		{
			System.out.println("I'm still a bean!");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Bean other = (Bean) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
