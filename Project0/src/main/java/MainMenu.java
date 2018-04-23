import java.io.File;
import org.apache.log4j.Logger;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class MainMenu implements Serializable{

	static List<UserInfo> createdUsers = new ArrayList<>();
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(MainMenu.class);

	public static void serializePerson(List<UserInfo> createdUsers, File file) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(new File(file.getPath())));
			// This method serialized the provided UserInfo object to the provided File destination
			out.writeObject(createdUsers);
		} catch (IOException ioe) {
			logger.info("Input/Output Exception");
			ioe.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException ioe) {
				logger.info("Input/Output Exception");
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
		} catch(FileNotFoundException fnfe){
			return (new ArrayList<UserInfo>());
		}
		catch (IOException ioe) {
			logger.info("Input/Output Exception");
			ioe.printStackTrace();
		} catch (ClassNotFoundException cnfe) {
			logger.info("ClassNotFound Exception");
			cnfe.printStackTrace();
		} finally {
			try {
				if (in != null)
				in.close();
			} catch (IOException ioe) {
				logger.info("Input/Output Exception");
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
		
		ArrayList<Item> cart = new ArrayList<Item>();
		Shop userCart = new Shop();

				
		//declare Scanner and display introduction
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to Project 0!");
		System.out.println("This is a budgeting app that helps you");
		System.out.println("maintain your grocery shopping budget.");
		System.out.println("If you already have an account, press 1");
		System.out.println("To create an account, press 2");
		System.out.println("To exit, please press 0.");
		
		//possible input mismatch exception***********************************************

		try {
			int ifHaveAnAccount = scan.nextInt();			
		

		//ensures a valid menu option is chosen
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
	
				System.out.println("\nWelcome Back!");
				System.out.println("Please enter your Username.In option 1.");
				username = scan.next();
				System.out.println("Please enter your Password.");
				password = scan.next();
				
				
					for (int i = 0; i < createdUsers.size(); i ++) 
					{
						if	((createdUsers.get(i).getUsername().equals("Admin"))
								&& (createdUsers.get(i).getPassword().equals("Login"))) {
							
							System.out.println("\nPlease select one of the following: ");
							System.out.println("1. Approve Account.");
							System.out.println("2. Lock Account.");
							System.out.println("3. Unlock Account.");
							int userOption = scan.nextInt();
							
							//Approve Account
							if (userOption == 1){
								System.out.println("Choose the user you would like to edit");
								createdUsers = deserializePerson(new File("src/main/resources/UserInformation.txt"));
								System.out.println(createdUsers);
								
								System.out.println("Please enter their Username.");
								username = scan.next();
								System.out.println("Please enter their Password.");
								password = scan.next();

								for (int j = 0; j < createdUsers.size(); j ++) {
									if((createdUsers.get(j).getUsername().equals(username))
											&& (createdUsers.get(j).getPassword().equals(password)) 
											&& (createdUsers.get(j).isApproved() == false)){
										createdUsers.get(j).setApproved(true);
										
										System.out.println(createdUsers.get(j));
									}
								}
							}
							
							//Lock Account
							if (userOption == 2)
							{
								System.out.println("Choose the user you would like to edit");
								createdUsers = deserializePerson(new File("src/main/resources/UserInformation.txt"));
								System.out.println(createdUsers);
								
								System.out.println("Please enter their Username.");
								username = scan.next();
								System.out.println("Please enter their Password.");
								password = scan.next();

								for (int j = 0; j < createdUsers.size(); j ++) {
									if((createdUsers.get(j).getUsername().equals(username))
											&& (createdUsers.get(j).getPassword().equals(password)) 
											&& (createdUsers.get(j).isLocked() == false)){
										createdUsers.get(j).setLocked(true);
										
										System.out.println(createdUsers.get(j));
									}
								}	
							}
							
							//Unlock Account							
							if (userOption == 3)
							{
								System.out.println("Choose the user you would like to edit");
								createdUsers = deserializePerson(new File("src/main/resources/UserInformation.txt"));
								System.out.println(createdUsers);
								
								System.out.println("Please enter their Username.");
								username = scan.next();
								System.out.println("Please enter their Password.");
								password = scan.next();

								for (int j = 0; j < createdUsers.size(); j ++) {
									if((createdUsers.get(j).getUsername().equals(username))
											&& (createdUsers.get(j).getPassword().equals(password)) 
											&& (createdUsers.get(j).isLocked() == true)){
										createdUsers.get(j).setLocked(false);
										
										System.out.println(createdUsers.get(j));
									}
								}	
							}
						}
	
						//Makes sure user is both approved and their account isn't locked
						else if((createdUsers.get(i).getUsername().equals(username))
								&& (createdUsers.get(i).getPassword().equals(password)) 
								&& (createdUsers.get(i).isApproved() == true) 
								&& (createdUsers.get(i).isLocked() == false) ){
							System.out.println("Please select one of the following menu options:");
							System.out.println("1. Add to your cart.");
							System.out.println("2. Remove from your cart.");
							System.out.println("3. View cart.");
							
							int takenInput = scan.nextInt();
							if(takenInput == 1)
							{						
								System.out.println("Choose an item you would like to add:");
								GroceryList myList = new GroceryList();
								Iterator<Map.Entry<Integer, Item>> it = myList.items.entrySet().iterator();
								while (it.hasNext()) {
									
									Map.Entry<Integer, Item> pair = (Map.Entry<Integer, Item>) it.next(); 
									System.out.println(pair.getKey() + " => " + pair.getValue());
								}
								
								int addItemNumber = scan.nextInt();
//								if (addItemNumber == 0){
//									return;
//								}
								if (addItemNumber >= 1 && addItemNumber < 12) {
									Shop.addToCart(myList.getItem(addItemNumber));
									//System.out.println(myList.getItem(addItemNumber));
								}
						break;
							}
							if(takenInput == 2)
							{
							System.out.println("Which of the following items would you like to remove?");
							System.out.println(Shop.cart);
							String toBeRemoved = scan.nextLine();
							if(cart.contains(toBeRemoved))
							{
							  int index=cart.indexOf(toBeRemoved);
							  Shop.removeFromCart(cart.get(index));
							}
							
							
								
							}
							if(takenInput == 3)
							{
							System.out.println("Currently in your cart: " + Shop.cart);
							System.out.println("Your current total: " + Shop.getTotal());	
							}
							break;
						}
						
						//If the user account is locked or no approved
						else if	((createdUsers.get(i).getUsername().equals(username))
								&& (createdUsers.get(i).getPassword().equals(password))) 
						{
							System.out.println("Your account has not yet been approved");
							System.out.println("or is locked.");
							System.out.println("Please try again later.");
							break;
						}
					}				
				break;
				
			case 2:
				//Account Creation
				System.out.println("Please enter your Username.");
				username = scan.next();
				System.out.println("Please enter your Password.");
				password = scan.next();
				
				//Creates user object and adds the object to the ArrayList of user objects
				UserInfo user = new UserInfo(username, password);
				createdUsers.add(user);
				
				System.out.println("Congratulations on making your account!");
				System.out.println("Happy Shopping!");
				
				break;	
				
			case 0:
				//terminate the program
				System.exit(0);
			default : 
				System.out.println("defaulted");
			}
			
			System.out.println("\nTo continue to login, press 1.");
			System.out.println("To exit press 0.");
			ifHaveAnAccount = scan.nextInt();
		}
		
		serializePerson(createdUsers,new File("src/main/resources/UserInformation.txt"));
	
	}catch(InputMismatchException ime){
		logger.warn("Mismatching types", ime);
		}finally {
			//Close my resource
		    if(scan!=null)
		        scan.close();

		}
		
	}
}
