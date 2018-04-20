package com.revature.polymorphism;

public class Parent {
	
	public void myMethod() {
		System.out.println("Hey! I'm inside the Parent class");
	}
	
	public Object myCovarientMethod() {
		System.out.println("Inside the parent mCovarientMethod");
		return new Object();
	}
	
	public static void main(String[] args) {
		Parent parent = new Parent();
		Parent child2 = new Child();
		Child child = new Child();
		
//		parent.myMethod();
//		child.myMethod();
//		child2.myMethod();
		
		System.out.println(parent.myCovarientMethod());
		System.out.println();
		System.out.println(child.myCovarientMethod());
	}
}


class Child extends Parent{
	
	public void myMethod() {
		System.out.println("Hey! I'm inside the Child class");
	}
	
//	public String myCovarientMethod() {
//		System.out.println("Inside the Child myCovarientMethod");
//		return "Hello, World!";
//	}
	
	public A myCovarientMethod() {
		System.out.println("Hey! I'm inside the Child class");
		return new C();
	}
}

class AsManyClassesAsYouLike{
	//They just can't be public, only one and that has to be the file name
	
	
}