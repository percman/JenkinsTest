package com.revature.polymorphism;

public class ExampleOfOverloading {

	/*
	 * Method Overloading: Same method name, different method parameters
	 */
	
	
	public int add(int a, int b) {
		System.out.println("Inside the 2-int add method");
		return a + b;
	}
	
	public int add(int x, int y, int z) {
		System.out.println("Inside the 3-int add method");
		return x + y + z;
	}
	
	public int add(int p, double q) {
		System.out.println("Inside the 1-int, 1-double add method");
		return p + (int) q;
		
		// Going from a wide type to a narrow type, does it require casting? Yes
		// Going from a narrow type to a wider type does not require casting
	}
	
	public static void main(String[] args) {
		ExampleOfOverloading ex = new ExampleOfOverloading();
		System.out.println(ex.add(10, 20));
		System.out.println(ex.add(10, 20, 30));
		System.out.println(ex.add(10, 20.0));
	}
	
	
	
}
