package com.revature.main;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

import com.revature.model.CustLogin;
import com.revature.service.CustLoginService;
import com.revature.util.ConnectionUtil;

public class User   {
	  
	static int verified;
	
	public User() {}
	
	
	public final static void UserVerify() {
		
		Scanner uN = new Scanner(System.in);
		System.out.print("Please enter user Name ");
		String uName1 = uN.nextLine();
		
		Scanner uP = new Scanner(System.in);
		System.out.print("Please enter user password ");
		String uPwd1 = uP.nextLine();
		
		int index = 0; 
		try(Connection conn = ConnectionUtil.getConnection()){
			
			CallableStatement stmt = conn.prepareCall("{CALL check_my_login(?,?,?)}");
			stmt.setString(++index, uName1);
			stmt.setString(++index, uPwd1);
			stmt.registerOutParameter(3, Types.NUMERIC);
			stmt.executeUpdate();
			
			  verified = stmt.getInt(3);
			
			System.out.println(verified);
				
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL state " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		
			
		while(true) {
			
			
			if(verified == 1) {
				
				System.out.println("verified");
			
				System.out.println("Please enter options: 1)withdraw    2)deposit  3) Balance 	4)Exit");
				Scanner o = new Scanner(System.in);
				String option = o.nextLine();
				
				switch(option) {
				
				case "1":
					
						System.out.println("Please enter the account No");
						Scanner o1 = new Scanner(System.in);
						String acno = o1.nextLine();
						int accnountNo = Integer.parseInt(acno);
				
						System.out.println("Please enter the amount you want to withdraw");
						Scanner oo = new Scanner(System.in);
						String withdraw = oo.nextLine();
						int withdrawAmount = Integer.parseInt(withdraw);
						
								
						int index1 = 0; 
						try(Connection conn = ConnectionUtil.getConnection()){
							
							CallableStatement stmt = conn.prepareCall("{CALL WITHDRAW(?,?)}");
							stmt.setInt(++index1, accnountNo);
							stmt.setInt(++index1, withdrawAmount);
							
							stmt.executeUpdate();
							
						} catch(SQLException sqle) {
							System.err.println(sqle.getMessage());
							System.err.println("SQL state " + sqle.getSQLState());
							System.err.println("Error Code: " + sqle.getErrorCode());
						}
						
						break;
					
					
				
				case "2":
					
					System.out.println("Please enter the account No");
					Scanner oo1 = new Scanner(System.in);
					String acno1 = oo1.nextLine();
					int accnountNo1 = Integer.parseInt(acno1);
					
					System.out.println("Please enter the amount you want to deposit");
					Scanner ooo = new Scanner(System.in);
					String deposit = ooo.nextLine();
					int depositamount = Integer.parseInt(deposit);
					
								
					int index11 = 0; 
					try(Connection conn = ConnectionUtil.getConnection()){
						
						CallableStatement stmt = conn.prepareCall("{CALL deposit(?,?)}");
						stmt.setInt(++index11, accnountNo1);
						stmt.setInt(++index11, depositamount);
						
						stmt.executeUpdate();
						
					} catch(SQLException sqle) {
						System.err.println(sqle.getMessage());
						System.err.println("SQL state " + sqle.getSQLState());
						System.err.println("Error Code: " + sqle.getErrorCode());
					}
					
					break;
			
				case "3":
						System.out.println("Please enter the account No");
						Scanner oo6 = new Scanner(System.in);
						String acno6 = oo6.nextLine();
						int accountno6 = Integer.parseInt(acno6);
					
						try(Connection conn = ConnectionUtil.getConnection()){
						
							CallableStatement stmt = conn.prepareCall("{CALL get_Balance(?,?)}");
							stmt.setInt(1, accountno6);
							stmt.registerOutParameter(2, Types.NUMERIC);		
							stmt.executeUpdate();
							
							int balance = stmt.getInt(2);
					
							System.out.println("Your balance is " + balance );
						
					} catch(SQLException sqle) {
						System.err.println(sqle.getMessage());
						System.err.println("SQL state " + sqle.getSQLState());
						System.err.println("Error Code: " + sqle.getErrorCode());
					}
					
					break;
						
				case "4":
					System.exit(0);
					
						
	
				default: 
					
					System.out.println("Wrong option");
					System.exit(0);
				} // switch
				
			
			}
					
		}
						}		}
					
	
		
				
	
	


