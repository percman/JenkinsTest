package com.revature.polymorphism;

public class Parent {

	public void myMethod() {
		System.out.println("Hey! I'm inside the Parent class");
	}
	
	public A someName() {
		System.out.println("Inside the parent myCovariantMethod");
		return new A();
	}
	
	public static void main(String[] args) {
		Parent parent = new Parent();
		Parent child2 = new Child();
		Child child = new Child();
		
//		parent.myMethod();
//		child.myMethod();
//		child2.myMethod();
		
		System.out.println(parent.someName());
		System.out.println();
		System.out.println(child.someName());
	}
}


class Child extends Parent {
	
	public void myMethod() {
		System.out.println("Hey! I'm in the Child class");
	}
	
//	public String myCovariantMethod() {
//		System.out.println("Inside the Child myCovariantMethod");
//		return "Hello, World!";
//	}
	
	public B someName() {
		System.out.println("Inside the Child myCovariantMethod");
		return new B();
	}
}

class AsManyClassesAsYouLike {
}
