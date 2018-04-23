package com.revature.bank;
import java.util.Scanner;

public class Admin {
	
	private static final String aPwd = "admin";

	public String getaPwd() {
		return aPwd;
	}
	
	public Admin() {};
	
	public final static boolean AdminVerify() {
		
		Scanner admin = new Scanner(System.in);
		System.out.print("Enter admin password (password = admin) :");
		String aPwd = admin.nextLine();
		return (Admin.aPwd.equals(aPwd));	
	}	
}
