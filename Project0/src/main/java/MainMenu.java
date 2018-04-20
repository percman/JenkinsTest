import java.util.Scanner;

public class MainMenu {

	public static void startProgram() {
		String username = null;
		String password = null;
		boolean correctInput=true;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Welcome to Project 0!"
				+ "\nThis is a grocery store app that helps you"
				+ "\nmaintain your grocery shopping bugget"
				+ "\nDo you already have an account with us?");
		System.out.println("\nEnter 1 for yes and 2 for no");
		//possible input mismatch exception
		int ifHaveAnAccount = scan.nextInt();

		while (ifHaveAnAccount > 2 || ifHaveAnAccount< 1)
		{
			System.out.println("Invalid input. Try again.");
			ifHaveAnAccount = scan.nextInt();
		}
				
		switch(ifHaveAnAccount)
		{
		case 1:
			while(correctInput!=true)
			{
			System.out.println("Please enter valid user information");
			}

			System.out.println("Welcome Back!");
			System.out.println("To login as an admin enter '1' "
					+ "\nTo login to a user account, press '2' ");
			int userType = scan.nextInt();
			
			while (userType > 2 || userType< 1)
			{
				System.out.println("Invalid input. Try again.");
				userType = scan.nextInt();
			}
			 if (userType == 1) {
				 System.out.println("You are in admin");
			 }
			
			if (userType == 2) {
				System.out.println("You are in a users account");
				
			}
			
			break;
		case 2:
			//Account Creation
			System.out.println("Please enter your Username.");
			username = scan.next();
			System.out.println("Please enter your Password.");
			password = scan.next();
			
			UserInfo myFirstAccount = new UserInfo(username, password);
			System.out.println(myFirstAccount.toString());
			
			System.out.println("Congratulations on making your account!");
			System.out.println("Welcome to the grocery store lol");
			break;
		default : 
			System.out.println("defaulted");
		}
	}
}
