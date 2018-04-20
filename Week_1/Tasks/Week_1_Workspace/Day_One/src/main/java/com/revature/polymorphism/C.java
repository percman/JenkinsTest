package com.revature.polymorphism;

public class C extends B {
	
	private int myNumber;
	
	public C() {
		System.out.println("Printing from inside C's no-arg constructor");
	}
	
	public C(int number) {
		super(number);
		System.out.println("Inside the 1-arg constructor of C");
		this.myNumber = number;
	}
	public static void main(String[] args) {
		A a = new A(10);
		A.myHiddenMethod();
		System.out.println();
		A b = new B(10);
		b.myHiddenMethod();
		System.out.println();
		A c = new C(10);
		c.myHiddenMethod();
	}
	
	public static void myHiddenMethod() {
		System.out.println("This is class C's myHiddenMethod");
	}
	

}
