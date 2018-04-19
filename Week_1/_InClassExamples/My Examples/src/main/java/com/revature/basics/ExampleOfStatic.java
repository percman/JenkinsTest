package com.revature.basics;

public class ExampleOfStatic {

	static int myStaticInt;
	int myInstanceInt;
	
	public void increment() {
		myStaticInt++;
		this.myInstanceInt++;
	}
	
	public static void printExample(ExampleOfStatic ex) {
		System.out.println("Instance: " + ex.myInstanceInt);
		System.out.println("Static: " + myStaticInt);
	}
	
	public static void main(String[] args) {
		ExampleOfStatic ex1 = new ExampleOfStatic();
		ExampleOfStatic ex2 = new ExampleOfStatic();
		ExampleOfStatic ex3 = new ExampleOfStatic();
		
		// Call increment 3 times
		ex1.increment();
		ex1.increment();
		ex1.increment();
		
		// Call increment 4 times
		ex2.increment();
		ex2.increment();
		ex2.increment();
		ex2.increment();
		
		// Call increment() 2 times
		ex3.increment();
		ex3.increment();
		
		printExample(ex1);
		printExample(ex2);
		printExample(ex3);
	}
}
