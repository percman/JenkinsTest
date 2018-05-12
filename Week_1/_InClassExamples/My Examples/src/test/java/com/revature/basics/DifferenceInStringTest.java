package com.revature.basics;

import static org.junit.Assert.*;

import org.junit.Test;

public class DifferenceInStringTest {

	@Test
	public void stringFasterThanBuilder() {
		assertFalse(DifferenceInStringX.stringFasterThanBuilder(150));
	}
	
	@Test
	public void stringFasterThanBuffer() {
		assertFalse(DifferenceInStringX.stringFasterThanBuffer(150));
	}
	
	@Test
	public void builderFasterThanBuffer() {
		assertTrue(DifferenceInStringX.builderFasterThanBuffer(150));
	}
	
	@Test
	public void builderFasterThanString() {
		assertTrue(DifferenceInStringX.builderFasterThanString(150));
	}
	
	@Test
	public void bufferFasterThanBuilder() {
		assertFalse(DifferenceInStringX.bufferFasterThanBuilder(150));
	}
	
	@Test
	public void bufferFasterThanString() {
		assertTrue(DifferenceInStringX.bufferFasterThanString(150));
	}
}
