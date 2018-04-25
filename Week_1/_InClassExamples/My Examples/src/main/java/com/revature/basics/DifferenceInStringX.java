package com.revature.basics;

public class DifferenceInStringX {
	
	public static long stringDifference(int iterations) {
		String s = "";
		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			s += "a";
		}
		long end = System.nanoTime();
		return end - start;
	}
	
	public static long builderDifference(int iterations) {
		StringBuilder sb = new StringBuilder();
		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			sb.append("a");
		}
		long end = System.nanoTime();
		return end - start;
	}
	
	public static long bufferDifference(int iterations) {
		StringBuffer buffer = new StringBuffer();
		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			buffer.append("a");
		}
		long end = System.nanoTime();
		return end - start;
	}
	
	public static boolean stringFasterThanBuilder(int iterations) {
		return stringDifference(iterations) < builderDifference(iterations);
	}
	
	public static boolean stringFasterThanBuffer(int iterations) {
		return stringDifference(iterations) < bufferDifference(iterations);
	}
	
	public static boolean bufferFasterThanString(int iterations) {
		return bufferDifference(iterations) < stringDifference(iterations);
	}
	
	public static boolean bufferFasterThanBuilder(int iterations) {
		return bufferDifference(iterations) < builderDifference(iterations);
	}
	
	public static boolean builderFasterThanString(int iterations) {
		return builderDifference(iterations) < stringDifference(iterations);
	}
	
	public static boolean builderFasterThanBuffer(int iterations) {
		return builderDifference(iterations) < bufferDifference(iterations);
	}
}
