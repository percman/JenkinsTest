package com.revature.main;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.util.ConnectionUtil;


public class Main {

	public static void main(String[] args) throws IOException {
		
		System.out.print("Enter [1 for admin] [2 for customer]");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
	
		if(s.equals("1")) {
			
			if(Admin.AdminVerify()) {
				System.out.println("**admin verified** ");
				while(true) {
				adminMenu();
				}
			}
						
		} //if admin
		
		if(s.equals("2")) {
				User.UserVerify();
		}
		
	} // main

	

	private static void adminMenu() throws IOException {
	
	
		System.out.println("Menu options: Create a user(1)/lock a user(2)/display all users(3)/unlock a user(4)/exit(5)");
		
		Scanner sc = new Scanner(System.in);
		String st = sc.nextLine();
		
		Scanner sc1 = new Scanner(System.in);
		String st1 = sc1.nextLine();
		
		Scanner sc2 = new Scanner(System.in);
		String st2 = sc2.nextLine();
		
		Scanner sc3 = new Scanner(System.in);
		String st3 = sc2.nextLine();
		
		
		
		
			if(st.equals("1")) {
				
				int index = 0; 
				try(Connection conn = ConnectionUtil.getConnection()){
					
					CallableStatement stmt = conn.prepareCall("{CALL (?,?,?)}");
					stmt.setString(1, st1);
					stmt.setString(2, st2);
					stmt.setString(3,  st3);
					
					//stmt.executeUpdate();
					stmt.execute();
					
				} catch(SQLException sqle) {
					System.err.println(sqle.getMessage());
					System.err.println("SQL state " + sqle.getSQLState());
					System.err.println("Error Code: " + sqle.getErrorCode());
				}
					
			} // 1
			
			
			if(st.equals("2")) {
				
				System.out.println("Enter customer account number to lock");
				Scanner sc4 = new Scanner(System.in);
				String st4 = sc4.nextLine();
				int acno = Integer.parseInt(st4);
				
				int index = 0; 
				try(Connection conn = ConnectionUtil.getConnection()){
					
					CallableStatement stmt = conn.prepareCall("{CALL lock_account(?}");
					stmt.setInt(++index, acno);
					
					stmt.executeUpdate();
					
				} catch(SQLException sqle) {
					System.err.println(sqle.getMessage());
					System.err.println("SQL state " + sqle.getSQLState());
					System.err.println("Error Code: " + sqle.getErrorCode());
				}
			
	
			}
			
			if(st.equals("3")) {
				
					int index = 0;
					try(Connection conn = ConnectionUtil.getConnection()){
						PreparedStatement stmt = conn.prepareStatement("SELECT * FROM customer");
						//stmt.setString(++index, );
						ResultSet rs = stmt.executeQuery();
					
						
						while(rs.next()) {
							System.out.println("customer ID : " + rs.getString(1) + " customer first Name : " + rs.getString(2)
							
									+" customer last Name : " + rs.getString(3) + " customer city " + rs.getString(4));
						}
					
					} catch(SQLException sqle) {
						System.err.println(sqle.getMessage());
						System.err.println("SQL state " + sqle.getSQLState());
						System.err.println("Error Code: " + sqle.getErrorCode());
					}
				
				
						      
			} //3
			
			if(st.equals("4")) {
				System.out.println("Enter customer account number to UN lock the account");
				Scanner sc5 = new Scanner(System.in);
				String st5 = sc5.nextLine();
				int acno = Integer.parseInt(st5);
				
				int index = 0; 
				try(Connection conn = ConnectionUtil.getConnection()){
					
					CallableStatement stmt = conn.prepareCall("{CALL unlock_account(?}");
					stmt.setInt(++index, acno);
					
					stmt.executeUpdate();
					
				} catch(SQLException sqle) {
					System.err.println(sqle.getMessage());
					System.err.println("SQL state " + sqle.getSQLState());
					System.err.println("Error Code: " + sqle.getErrorCode());
				}
			
			}
			
			if(st.equals("5")) {
				System.exit(0);
			}
		
	} //admin menu



	}

