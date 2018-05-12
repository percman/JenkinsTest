package com.revature.reversingStrings;

public class StringX {

	public static long stringDiff(int iterations) {
		String s = "";
		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			s += "a";
		}
		long end = System.nanoTime();
		return end - start;
	}
	
	public static long builderDiff(int iterations) {
		StringBuilder sb = new StringBuilder();
		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			sb.append("a");
		}
		long end = System.nanoTime();
		return end - start;
	}
	
	public static long bufferDiff(int iterations) {
		StringBuffer buff = new StringBuffer();
		long start = System.nanoTime();
		for (int i = 0; i < iterations; i++) {
			buff.append("a");
		}
		long end = System.nanoTime();
		return end - start;
	}
	
	public static boolean stringFasterThanBuilder(int iterations) {
		return stringDiff(iterations) < builderDiff(iterations);
	}
	
	public static boolean stringFasterThanBuffer(int iterations) {
		return stringDiff(iterations) < bufferDiff(iterations);
	}
	
	public static boolean bufferFasterThanString(int iterations) {
		return bufferDiff(iterations) < stringDiff(iterations);
	}
	
	public static boolean bufferFasterThanBuilder(int iterations) {
		return bufferDiff(iterations) < builderDiff(iterations);
	}
	
	public static boolean builderFasterThanString(int iterations) {
		return builderDiff(iterations) < stringDiff(iterations);
	}
	
	public static boolean builderFasterThanBuffer(int iterations) {
		return builderDiff(iterations) < bufferDiff(iterations);
	}
}
