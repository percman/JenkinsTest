package test;

import static org.junit.Assert.*;
import java.util.Arrays;
import org.junit.Test;

import main.Pascal;

public class PascalTest {
	@Test
	public void testElement() {
		assertEquals("row 0, column 0", 1, Pascal.element(0, 0));
		assertEquals("row 1, column 0", 1, Pascal.element(1, 0));
		assertEquals("row 1, column 1", 1, Pascal.element(1, 1));
		assertEquals("row 2, column 0", 1, Pascal.element(2, 0));
		assertEquals("row 2, column 1", 2, Pascal.element(2, 1));
		assertEquals("row 2, column 2", 1, Pascal.element(2, 2));
		assertEquals("row 4, column 3", 4, Pascal.element(4, 3));
		assertEquals("row 7, column 3", 35, Pascal.element(7, 3));
	}
	
	int[] row0 = {1};
	int[] row1 = {1,1};
	int[] row2 = {1,2,1};
	int[] row3 = {1,3,3,1};
	int[] row4 = {1,4,6,4,1};
	int[] row5 = {1,5,10,10,5,1};
	int[] row6 = {1,6,15,20,15,6,1};
	int[] row7 = {1,7,21,35,35,21,7,1};
	
	@Test
	public void testRow() {
		assertTrue(Arrays.equals(row0, Pascal.row(0)));
		assertTrue(Arrays.equals(row1, Pascal.row(1)));
		assertTrue(Arrays.equals(row2, Pascal.row(2)));
		assertTrue(Arrays.equals(row3, Pascal.row(3)));
	}
	
	int[][] triangle0 = {row0};
	int[][] triangle1 = {row0, row1};
	int[][] triangle2 = {row0, row1, row2};
	int[][] triangle3 = {row0, row1, row2, row3};
	int[][] triangle4 = {row0, row1, row2, row3, row4};
	int[][] triangle5 = {row0, row1, row2, row3, row4, row5};
	int[][] triangle6 = {row0, row1, row2, row3, row4, row5, row6};
	int[][] triangle7 = {row0, row1, row2, row3, row4, row5, row6, row7};
	
	@Test
	public void testTriangle() {

		assertTrue(Arrays.deepEquals(triangle0, Pascal.triangle(0)));
		assertTrue(Arrays.deepEquals(triangle1, Pascal.triangle(1)));
		assertTrue(Arrays.deepEquals(triangle2, Pascal.triangle(2)));
		assertTrue(Arrays.deepEquals(triangle3, Pascal.triangle(3)));
		assertTrue(Arrays.deepEquals(triangle4, Pascal.triangle(4)));
		assertTrue(Arrays.deepEquals(triangle5, Pascal.triangle(5)));
		assertTrue(Arrays.deepEquals(triangle6, Pascal.triangle(6)));
		assertTrue(Arrays.deepEquals(triangle7, Pascal.triangle(7)));
	}

}
