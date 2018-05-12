package com.revature.polymorphism;

public class B extends A{

	private int myNumber;
	
	public B() {
		System.out.println("Printing from inside B's no-arg constructor");
	}
	
	public B(int myNumber) {
		super(myNumber);
		System.out.println("Inside the 1-arg constructor of B");
		this.myNumber = myNumber;
	}
	
	public static void myHiddenMethod() {
		System.out.println("This is class B's myHiddenMethod");
	}
}
