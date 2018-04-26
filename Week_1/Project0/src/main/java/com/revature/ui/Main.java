package com.revature.ui;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.revature.bank.Admin;
import com.revature.bank.BankingSystem;
import com.revature.bank.User;	

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
		
			if(st.equals("1")) {
				
				BufferedReader br = new BufferedReader(new FileReader("C:/Users/abj/Documents/STS-Projects/Project.Zero/src/main/resources/user.ser"));     
				if (br.readLine() == null) {
				    System.out.println("file empty");
				    createUser();

				}
				
			
				else {
					deserialize();
					createUser();
				}
				
			
			} // 1
			
			
			if(st.equals("2")) {
				User.lockUser();
	
			}
			
			if(st.equals("3")) {
				User.displayUser();
						      
			} //3
			
			if(st.equals("4")) {
				User.unlockUser();
			}
			
			if(st.equals("5")) {
				System.exit(0);
			}
		
	} //admin menu



	private static void createUser() {
		// TODO Auto-generated method stub
		
		
		User user = null;
		int i= 3;
		while(i == 3) {
			
			System.out.println("Enter user Name ");
			Scanner sc3  = new Scanner(System.in);
			String uName = sc3.nextLine();
	
			
			System.out.println("Enter user password ");
			Scanner sc4 = new Scanner(System.in);
			String uPwd = sc4.nextLine();
			
			System.out.println("Enter user bank account no ");
			Scanner sc5 = new Scanner(System.in);
			String aNo = (sc4.nextLine());
			int aNo2 = Integer.parseInt(aNo);
		
			user = new User(uName,uPwd,aNo2);					//Creating/Registering a new User Object//
			
			
			System.out.println("Enter [3] to add more users or [any other numbers] to exit"); 
			Scanner sc6  = new Scanner(System.in);
			String nn = sc3.nextLine();
			
			i = Integer.parseInt(nn);
	
	} //while
		
		serializeUser();
		
	} // createUser()



	public static void serializeUser() {
		
		try {
	          FileOutputStream fileOut = new FileOutputStream("C:/Users/abj/Documents/STS-Projects/Project.Zero/src/main/resources/user.ser");
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(BankingSystem.userList);
	          out.close();
	          fileOut.close();
	          System.out.println("data saved in user.ser");
	       } catch (IOException io) {
	          io.printStackTrace();
	}
	
	}
	
	@SuppressWarnings("unchecked")
	public static void deserialize() {
		
		List<User> userList1 = null;
		
		  try {
			  
		          FileInputStream fileIn = new FileInputStream("C:/Users/abj/Documents/STS-Projects/Project.Zero/src/main/resources/user.ser");
		          ObjectInputStream in = new ObjectInputStream(fileIn);
		          userList1 = (ArrayList<User>)in.readObject();
				  BankingSystem.userList = userList1;		// assigning to original arraylist
				  userList1 = null;
				  in.close();
		          fileIn.close();
		      }
		  
		  catch (IOException ex) {
			  System.out.println("IOException is caught");
		  }
		  
		  catch(ClassNotFoundException ex) {
			  System.out.println("ClassNotFoundException is caught");
		  }	
	}
	
	
	
}

