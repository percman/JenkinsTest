package com.revature.reversingStrings;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StringXTest {

	@Test
	public void stringFasterThanBuilder() {
		assertFalse(StringX.stringFasterThanBuilder(1000));
	}
	
	@Test
	public void stringFasterThanBuffer() {
		assertFalse(StringX.stringFasterThanBuffer(1000));
	}
	
	@Test
	public void builderFasterThanString() {
		assertTrue(StringX.builderFasterThanString(1000));
	}
	
	@Test
	public void builderFasterThanBuffer() {
		assertTrue(StringX.builderFasterThanBuffer(1000));
	}
	
	@Test
	public void bufferFasterThanString() {
		assertTrue(StringX.bufferFasterThanString(1000));
	}
	
	@Test
	public void bufferFasterThanBuilder() {
		assertFalse(StringX.bufferFasterThanBuilder(1000));
	}

}
