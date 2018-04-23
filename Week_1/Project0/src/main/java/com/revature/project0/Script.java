package com.revature.project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

public class Script {
	private static BufferedReader read;// reads from console for all interactions
	static StringTokenizer tokenizer;// tokenizer for all inputs
	static String input;
	private static final Logger logger = Logger.getLogger(Script.class);

	// handles the basic start up for the application
	public static void start(Reader stream) {
		read = new BufferedReader(stream);
		Record.getInstance().loadData();
		System.out.print("Type login to login or new user to create a new user:");
		try {
			input = read.readLine().toLowerCase();
			if (input.equals("login")) {
				logger.info("login attempt made");
				login();
			} else if (input.equals("new user")) {
				createUser();
			}
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} /*finally {
			try {
				read.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
		}*/
	}

	// handles the script that handles the creation of a new user
	public static void createUser() {
		try {
			System.out.print("Type admin to create an admin or user to create a user:");
			String type = read.readLine().replaceAll("\\s+", "");
			System.out.print("Enter the username for the new user:");
			String username = read.readLine().replaceAll("\\s+", "");
			System.out.print("Enter the password for the new user:");
			String password = read.readLine().replaceAll("\\s+", "");
			NewUser user = NewUserFactory.getUser(type, username, password);
			if (type.equals("admin")) {
				FileIO.addAdmin((Admin) user);
				logger.info("new admin " + username + " created");
				adminHub(FileIO.getAdmin(username));
			} else if (type.equals("user")) {
				FileIO.addNewUser((User) user);
				logger.info("new user " + username + " created");
				System.out.println("Your account has been created and is now pending admin approval");
			}
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} catch (UserTypeNotFoundException utnfe) {
			logger.error(utnfe.getMessage(), utnfe);
		} catch (UserNotFoundException unfe) {
			logger.error(unfe.getMessage(), unfe);
		} finally {
			try {
				read.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
		}
	}

	// handles running the script that handles the login method
	public static void login() {
		try {
			System.out.print("Please enter your username:");
			String username = read.readLine();
			if (Login.userExists(username)) {
				System.out.print("Please enter your password:");
				String password = read.readLine();
				if (Login.checkPassword(password, username)) {
					User user = FileIO.getUser(username);
					if (user.isUserUnapproved()) {
						throw new ApprovalPendingException();
					}
					if (user.isUserLocked()) {
						throw new LockedAccountException();
					}
					System.out.println("Welcome " + username);
					logger.info("user " + username + "logged in");
					userHub(FileIO.getUser(username));
				}
				throw new PasswordIncorrectException();
			}
			if (Login.adminExists(username)) {
				System.out.print("Please enter your password:");
				String password = read.readLine();
				if (Login.checkPassword(password, username)) {
					System.out.println("you have been successfully logged in as an admin.");
					logger.info("admin " + username + "logged in");
					adminHub(FileIO.getAdmin(username));
				}
				throw new PasswordIncorrectException();
			} else {
				throw new UserNotFoundException();
			}
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} catch (UserNotFoundException unfe) {
			logger.error(unfe.getMessage(), unfe);
		} catch (PasswordIncorrectException pie) {
			logger.error(pie.getMessage(), pie);
		} catch (LockedAccountException lae) {
			logger.error(lae.getMessage(), lae);
		} catch (ApprovalPendingException ape) {
			logger.error(ape.getMessage(), ape);
		} finally {
			try {
				read.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
		}
	}

	public static void userHub(User user) {
		String input = "";
		try {
			while (!input.equals("quit") && !input.equals("q")) {
				System.out.print(
						"Type add to add a movie to your collection, remove to remove a movie from your collction, view to view your collection or quit to stop:");
				input = read.readLine().toLowerCase();
				switch (input) {
				case "add":
					System.out.print("Enter the movies you want to add to your collection seperated by a common:");
					String titles = read.readLine();
					tokenizer = new StringTokenizer(titles, ",");
					while (tokenizer.hasMoreTokens()) {
						String title = tokenizer.nextToken();
						user.addMovie(title);
						logger.info("user " + user.username + "added movie " + title);
					}
					
					break;
				case "remove":
					System.out.print("Enter the movies you want to remove from your collection seperated by a common:");
					titles = read.readLine();
					tokenizer = new StringTokenizer(titles, ",");
					while (tokenizer.hasMoreTokens()) {
						String title = tokenizer.nextToken();
						user.removeMovie(title);
						logger.info("user " + user.username + "added movie " + title);
					}
					break;
				case "view":
					System.out.println("Your collection currently consists of");
					user.viewMovies();
					break;
				case "quit":
					System.out.print("You have quit the application.");
					logger.info("user " + user.username + "logged out");
					break;
				case "q":
					System.out.print("You have quit the application.");
					logger.info("user " + user.username + "logged out");
					break;
				default:
					System.out.println("please enter add,remove or view.");
				}
			}
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} finally {
			try {
				read.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
		}
	}

	public static void adminHub(Admin admin) {
		String input = "";
		try {
			while (!input.equals("quit") && !input.equals("q")) {
				System.out.print(
						"Type lock to lock a user, unlock to unlock a user, approve to approve a user or quit to stop:");
				input = read.readLine().toLowerCase();
				String user = "";
				switch (input) {
				case "lock":
					System.out.println("The list of users that can be locked are");
					FileIO.scanLocked();
					System.out.print("Type the names of the users you want to lock seperated by a comma:");
					user = read.readLine();
					tokenizer = new StringTokenizer(user, ",");
					while (tokenizer.hasMoreTokens()) {
						String username = tokenizer.nextToken();
						admin.setLocked(true, FileIO.getUser(username));
						logger.info("admin " + admin.username + "locked " + username);
					}
					break;
				case "unlock":
					System.out.println("The list of users that can be unlocked are");
					FileIO.scanUnlocked();
					System.out.print("Type the names of the users you want to unlock seperated by a comma:");
					user = read.readLine();
					tokenizer = new StringTokenizer(user, ",");
					while (tokenizer.hasMoreTokens()) {
						String username = tokenizer.nextToken();
						admin.setLocked(false, FileIO.getUser(username));
						logger.info("admin " + admin.username + "unlocked " + username);
					}
					break;
				case "approve":
					System.out.println("The list of users that are pending approval");
					FileIO.scanApproved();
					System.out.print("Type the name of the users you want to approve separated by a comma:");
					user = read.readLine();
					tokenizer = new StringTokenizer(user, ",");
					while (tokenizer.hasMoreTokens()) {
						String username = tokenizer.nextToken();
						admin.approve(FileIO.getUser(username));
						logger.info("admin " + admin.username + "approved " + username);
					}
					break;
				case "quit":
					System.out.print("You have quit the application.");
					logger.info("admin " + admin.username + "logged out");
					break;
				case "q":
					System.out.print("You have quit the application.");
					logger.info("admin " + admin.username + "logged out");
					break;
				default:
					System.out.println("please enter lock,unlock or approve.");
				}
			}
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} catch (UserNotFoundException unfe) {
			logger.error(unfe.getMessage(), unfe);
		} finally {
			try {
				read.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
		}
	}
}
