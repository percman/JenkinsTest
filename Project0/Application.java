import java.util.Scanner;

public class Application 
{
	
	public static void main(String[] args) 
	{
		String username = null;
		String password = null;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Welcome to Project 0!"
				+ "\nThis is a grocery store app where "
				+ "you create your shopping \nlist and check out "
				+ "your items."
				+ "\nDo you already have an account with us?");
		System.out.println("\nEnter 1 for yes and 2 for no");
		
		int ifHaveAnAccount = scan.nextInt();

		while (ifHaveAnAccount > 2 || ifHaveAnAccount< 1)
		{
			System.out.println("Invalid input. Try again.");
			ifHaveAnAccount = scan.nextInt();
		}
				
		switch(ifHaveAnAccount)
		{
		case 1:
			System.out.println("now in the case 1");
			break;
			
		case 2:
			//Account Creation
			System.out.println("Please enter your Username.");
			username = scan.next();
			System.out.println("Please enter your Password.");
			password = scan.next();
			System.out.println("Don't forget to write your username "
					+ "and password down in a safe place");
			System.out.println("Your username and password have been saved!");
			break;
		default : 
			System.out.println("defaulted");
		}
		
		System.out.println("username: " + username + " password: " + password);
	}

}
