package com.revature.dayone;

public class OrderOfInitialization {
	
	// Data Members <=> Variables <=> State
	String myInstanceString = "This is an instance string";
	static String myStaticString = "This is a static string";
	
	/*
	 * Order of Initialization
	 * 1. Static variables
	 * 2. Static initializers
	 * 3. Instance variables
	 * 4. Instance initializers
	 * 5. Constructors
	 * 
	 */
	
	{
		System.out.println("Inside the Instance Initializer");
	}
	
	static {
		System.out.println("Inside the Static Initializer");
	}
	
	public OrderOfInitialization() {
		System.out.println("Insice the Constructor");
	}
	
	public static void main(String[] args) {
		// Barebones, the minimum to instantiate an object is
		new OrderOfInitialization();
	}
}
