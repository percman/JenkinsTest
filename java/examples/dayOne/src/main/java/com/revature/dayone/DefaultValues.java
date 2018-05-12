package com.revature.dayone;

import java.io.FileInputStream;

public class DefaultValues {

	// Default values are strictly for the class level 
	static boolean myBoolean;	// Undeterminable size, default is false
	static byte myByte;			// 8-bit, default = 0
	static char myChar;			// 16-bit, default = 0
	static short myShort;		// 16-bit, default = 0
	static int myInt;			// 32-bit, default = 0
	static long myLong;			// 64-bit, default = 0
	static float myFloat;		// 32-bit floating point, default = 0.0
	static double myDouble;		// 64-bit floating point, default = 0.0
	static Object myObject;	
	static String myString;
	static FileInputStream in;
	
	public static void main(String[] args) {
		printDefaults();
	}
	
	public static void printDefaults() {
		System.out.println("myBoolean = " + myBoolean);
		System.out.println("myByte = " + myByte);
		System.out.println("myChar = " + myChar);
		System.out.println("myShort = " + myShort);
		System.out.println("myInt = " + myInt);
		System.out.println("myLong = " + myLong);
		System.out.println("myFloat = " + myFloat);
		System.out.println("myDouble = " + myDouble);
		System.out.println("myObject = " + myObject);
		System.out.println("myString = " + myString);
		System.out.println("in = " + in);
	}
}
