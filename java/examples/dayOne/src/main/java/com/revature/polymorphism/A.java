package com.revature.polymorphism;

public class A {

	private int myNumber;
	
	public A() {
		System.out.println("Printing from inside A's no-arg constructor");
	}
	
	public A(int number) {
		System.out.println("Inside the 1-arg constructor of A");
		this.myNumber = number;
	}
	
	public static void myHiddenMethod() {
		System.out.println("This is class A's myHiddenMethod");
	}
}
