package com.revature.singleton;

public class SingletonExample {

	/*
	 * 3 things we need for a Singleton:
	 * - private static field, matching the type of our class
	 * - private constructor
	 * - public static getInstance() method, which will either instantiate or return the existing object in memory
	 */
	
	// 1. private static field, matching the type of our class
	private static SingletonExample instance;
	
	// 2. private constructor
	private SingletonExample() {}
	
	// 3. public static getInstance() method
	public static SingletonExample getInstance() {
		if (instance == null) {
			instance = new SingletonExample();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		SingletonExample e1 = SingletonExample.getInstance();
		SingletonExample e2 = SingletonExample.getInstance();
		SingletonExample e3 = SingletonExample.getInstance();
		
		System.out.println(e1.hashCode());
		System.out.println(e2.hashCode());
		System.out.println(e3.hashCode());
		System.out.println(e1 == e2);
		System.out.println(e1 == e3);
		System.out.println(e2 == e3);
	}
	
}
