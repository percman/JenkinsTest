package com.revature.scanner;

import java.util.Scanner;

/**
 * Static scanner so that multiple scanners do not have to be constantly opened
 * @author Jesse
 *
 */

public class BankScanner {

	private BankScanner() {}
	
	public static Scanner input = new Scanner(System.in);
	
}
