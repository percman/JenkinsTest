package com.revature.basics;

public class OrderOfInitialization {
	
	String instance = "Instance variable";
	static String stat = "Static variable";

	{
		System.out.println("Instance Initializer");
	}
	
	static {
		System.out.println("Static Initializer");
	}
	
	public OrderOfInitialization() {
		System.out.println("Constructor");
	}
	
	public static void main(String[] args) {
		new OrderOfInitialization();
	}
}
