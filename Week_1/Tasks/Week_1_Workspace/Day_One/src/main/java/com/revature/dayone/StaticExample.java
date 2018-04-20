package com.revature.dayone;

public class StaticExample {
	
	int myInstanceInt;
	static int myStaticInt;
	
	public void increment() {
		this.myInstanceInt++;
		myStaticInt++;
	}
	public static void main(String[] args) {
		StaticExample ex1 = new StaticExample();
		StaticExample ex2 = new StaticExample();
		StaticExample ex3 = new StaticExample();
		
		// Call increment on ex1 4 times
		ex1.increment();
		ex1.increment();
		ex1.increment();
		ex1.increment();
		
		// Call increment on ex2 2 times
		ex2.increment();
		ex2.increment();
		
		// Call increment on ex3 3 times
		ex3.increment();
		ex3.increment();
		ex3.increment();
		
		System.out.println("ex1.myInstanceInt =" + ex1.myInstanceInt);
		System.out.println("ex1.myStaticInt =" + ex1.myStaticInt);
		System.out.println("ex2.myInstanceInt =" + ex2.myInstanceInt);
		System.out.println("ex2.myStaticInt =" + ex2.myStaticInt);
		System.out.println("ex3.myInstanceInt =" + ex3.myInstanceInt);
		System.out.println("ex3.myStaticInt =" + ex3.myStaticInt);

	}

}
