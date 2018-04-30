package com.revature.main;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.util.ConnectionUtil;

public class User   {
	  
	
	public User() {}
	
	
	public final static void UserVerify() {
	

		
		while(true) {
	
				
			/*IF VERIFIED*/
			
				System.out.println("Please enter options: 1)withdraw    2)deposit  4)Exit");
				Scanner o = new Scanner(System.in);
				String option = o.nextLine();
				
				switch(option) {
				
				case "1":
				
						System.out.println("Please enter the amount you want to withdraw");
						Scanner oo = new Scanner(System.in);
						String withdraw = oo.nextLine();
						
						System.out.println("Please enter the account No");
						Scanner o1 = new Scanner(System.in);
						String acno = o1.nextLine();
						int accnountNo = Integer.parseInt(acno);
						
						int index = 0; 
						try(Connection conn = ConnectionUtil.getConnection()){
							
							CallableStatement stmt = conn.prepareCall("{CALL WITHDRAW(?,?}");
							stmt.setInt(++index, accnountNo);
							stmt.setString(++index, withdraw);
							
							stmt.executeUpdate();
							
						} catch(SQLException sqle) {
							System.err.println(sqle.getMessage());
							System.err.println("SQL state " + sqle.getSQLState());
							System.err.println("Error Code: " + sqle.getErrorCode());
						}
						
						
						
						break;
					
					
				
				case "2":
					
					System.out.println("Please enter the amount you want to deposit");
					Scanner ooo = new Scanner(System.in);
					String deposit = ooo.nextLine();
					
					System.out.println("Please enter the account No");
					Scanner oo1 = new Scanner(System.in);
					String acno1 = oo1.nextLine();
					int accnountNo1 = Integer.parseInt(acno1);
					
					int index1 = 0; 
					try(Connection conn = ConnectionUtil.getConnection()){
						
						CallableStatement stmt = conn.prepareCall("{CALL deposit(?,?}");
						stmt.setInt(++index1, accnountNo1);
						stmt.setString(++index1, deposit);
						
						stmt.executeUpdate();
						
					} catch(SQLException sqle) {
						System.err.println(sqle.getMessage());
						System.err.println("SQL state " + sqle.getSQLState());
						System.err.println("Error Code: " + sqle.getErrorCode());
					}
					
						

			
						
				case "4":
					System.exit(0);
					
						
	
				default: 
					
					System.out.println("Wrong option");
					System.exit(0);
				} // switch
				
			}
						
						}
					}
					
	
		
				
	
	


