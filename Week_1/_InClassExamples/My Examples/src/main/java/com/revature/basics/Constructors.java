package com.revature.basics;

public class Constructors {

	private int myInt;
	private String myString;
	private char myChar;
	
	public Constructors() {
		this(1, "Hello World", 'c');
	}
	
	public Constructors(int i, String s, char c) {
		this.myInt = i;
		this.myString = s;
		this.myChar = c;
	}
	
	public Constructors(String s, char c) {
		this(15);
		this.myString = s;
		this.myChar = c;
	}
	
	public Constructors(int i) {
		this.myInt = i;
	}

	public int getMyInt() {
		return myInt;
	}

	public void setMyInt(int myInt) {
		this.myInt = myInt;
	}

	public String getMyString() {
		return myString;
	}

	public void setMyString(String myString) {
		this.myString = myString;
	}

	public char getMyChar() {
		return myChar;
	}

	public void setMyChar(char myChar) {
		this.myChar = myChar;
	}

	@Override
	public String toString() {
		return "Constructors [myInt=" + myInt + ", myString=" + myString + ", myChar=" + myChar + "]";
	}
	
	public static void main(String... args) {
		Constructors c1 = new Constructors();
		Constructors c2 = new Constructors(25);
		Constructors c3 = new Constructors("Constructor 3", 'w');
		Constructors c4 = new Constructors(35, "My custom constructor", 'f');
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		
	}
	
	
}
