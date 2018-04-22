import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class MainMenu implements Serializable{

	static List<UserInfo> createdUsers = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;

	public static void serializePerson(List<UserInfo> createdUsers2, File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(file.getPath())));
			// This method serialized the provided UserInfo object to the provided File destination
			out.writeObject(createdUsers2);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				// ALWAYS CLOSE RESOURCES
				out.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<UserInfo> deserializePerson(File file) {
		ObjectInputStream in = null;
		try {
			in = new ObjectInputStream(new FileInputStream(new File(file.getPath())));
			return (ArrayList<UserInfo>) in.readObject();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static void startProgram() {
		
		String username = null;
		String password = null;
		boolean correctInput=true;
		
		createdUsers = deserializePerson(new File("src/main/resources/UserInformation.txt"));
		System.out.println(createdUsers);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Project 0!");
		System.out.println("This is a budgeting app that helps you");
		System.out.println("maintain your grocery shipping budget.");
		System.out.println("If you already have an account, press 1");
		System.out.println("To create an account, press 2");
		System.out.println("To exit, please press 0.");
		
		//possible input mismatch exception
		int ifHaveAnAccount = scan.nextInt();

		while (ifHaveAnAccount > 2 || ifHaveAnAccount< 1)
		{
			System.out.println("Invalid input. Try again.");
			ifHaveAnAccount = scan.nextInt();
		}
		
		while(ifHaveAnAccount != 0) 
		{

			switch(ifHaveAnAccount)
			{
			case 1:
				while(correctInput!=true)
				{
				System.out.println("Please enter valid user information");
				}
	
				System.out.println("Welcome Back!");
	
				int userType = scan.nextInt();
				
				while (userType > 2 || userType< 1)
				{
					System.out.println("Invalid input. Try again.");
					userType = scan.nextInt();
				}
				 if (userType == 1) {
					 
					 int adminFunction = 0;
					 System.out.println("1. Approve an account");
					 System.out.println("2. Lock/Unlock account");
					 
					 switch (adminFunction)
					 {
					 case 1:
						 break;
					 case 2:
						 break;
					 }
				 }
				
				if (userType == 2) {
					System.out.println("Please enter your Username. In Option 1.");
					username = scan.next();
					System.out.println("Please enter your Password.");
					password = scan.next();
	//				int userFunction = 0;
	//				 System.out.println("1. Add to grocery list");
	//				 System.out.println("2. Remove from grocery list");
	//				 System.out.println("3. View my list");
	//				 
	//				 switch(userFunction) {
	//				 case 1:
	//					 break;
	//				 case 2:
	//					 break;
	//				 case 3:
	//					 break;
	//					
	//				 }
				}
				break;
				
			case 2:
				//Account Creation
				System.out.println("Please enter your Username.In option 2.");
				username = scan.next();
				System.out.println("Please enter your Password.");
				password = scan.next();
				
				UserInfo user = new UserInfo(username, password);
				createdUsers.add(user);
				
				System.out.println("Congratulations on making your account!");
				System.out.println("Happy Shopping!");
				
				break;	
			case 0:
				System.exit(0);
			default : 
				System.out.println("defaulted");
			}
	
			System.out.println("Welcome to Project 0!");
			System.out.println("This is a budgeting app that helps you");
			System.out.println("maintain your grocery shipping budget.");
			System.out.println("If you already have an account, press 1");
			System.out.println("To create an account, press 2");
			System.out.println("To exit, please press 0.");
			ifHaveAnAccount = scan.nextInt();
		}
		serializePerson(createdUsers,new File("src/main/resources/UserInformation.txt"));
	}
}
