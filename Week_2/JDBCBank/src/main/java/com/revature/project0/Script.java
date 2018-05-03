package com.revature.project0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.revature.dao.movie.Movie;
import com.revature.dao.movie.MovieService;
import com.revature.dao.users.AdminService;
import com.revature.dao.users.UserService;
import com.revature.data.FileIO;
import com.revature.data.Login;
import com.revature.exceptions.AlreadyHaveMovieException;
import com.revature.exceptions.ApprovalPendingException;
import com.revature.exceptions.LockedAccountException;
import com.revature.exceptions.MovieNotFoundException;
import com.revature.exceptions.NoMovieException;
import com.revature.exceptions.NotRentingMovieException;
import com.revature.exceptions.OutOfStockException;
import com.revature.exceptions.PasswordIncorrectException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.exceptions.UserTypeNotFoundException;
import com.revature.exceptions.tooManyMoviesOutException;
import com.revature.users.Admin;
import com.revature.users.NewUser;
import com.revature.users.NewUserFactory;
import com.revature.users.User;

public class Script {
	private static BufferedReader read;// reads from console for all interactions
	static StringTokenizer tokenizer;// tokenizer for all inputs
	static String input;
	private static final Logger logger = Logger.getLogger(Script.class);

	// handles the basic start up for the application
	public static void start(Reader stream) {
		read = new BufferedReader(stream);
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
		} finally {
			try {
				read.close();
			} catch (IOException ioe) {
				logger.error(ioe.getMessage(), ioe);
			}
		}
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
				AdminService.addAdmin((Admin) user);
				logger.info("new admin " + username + " created");
				adminHub(AdminService.getAdmin(username));
			} else if (type.equals("user")) {
				UserService.addUser((User)user);
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
				if (Login.checkPasswordUser(UserService.getPasswordHash(new User(username,password)), username)) {
					User user = UserService.getUser(username);
					if (UserService.isUserUnapproved(user)) {
						throw new ApprovalPendingException();
					}
					if (UserService.isUserLocked(user)){
						throw new LockedAccountException();
					}
					System.out.println("Welcome " + username);
					logger.info("user " + username + "logged in");
					userHub(user);
				}
				else {
				throw new PasswordIncorrectException();
				}
				}
			if (Login.adminExists(username)) {
				System.out.print("Please enter your password:");
				String password = read.readLine();
				if (Login.checkPasswordAdmin(AdminService.getPasswordHash(new Admin(username,password)), username)) {
					System.out.println("you have been successfully logged in as an admin.");
					logger.info("admin " + username + " logged in");
					adminHub(AdminService.getAdmin(username));
				}
				else {
				throw new PasswordIncorrectException();
				}
			}
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} catch (UserNotFoundException unfe) {
			logger.error(unfe.getMessage(), unfe);
			System.out.println("Your username was not found");
		} catch (PasswordIncorrectException pie) {
			logger.error(pie.getMessage(), pie);
			System.out.println("Your password incorrect");
		} catch (LockedAccountException lae) {
			logger.error(lae.getMessage(), lae);
			System.out.println("Your account has been locked by an admin");
		} catch (ApprovalPendingException ape) {
			logger.error(ape.getMessage(), ape);
			System.out.println("Your account is currently pending approval");
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
						"Type rent to rent a movie, return to return a movie, view to view your collection or quit to stop:");
				input = read.readLine().toLowerCase();
				switch (input) {
				case "rent":
					System.out.println("The movies you can currently rent are: ");
					MovieService.ViewAvailableMovies();
					System.out.print("Enter the movies you want to add to your collection seperated by a common:");
					String titles = read.readLine();
					tokenizer = new StringTokenizer(titles, ",");
					while (tokenizer.hasMoreTokens()) {
						String title = tokenizer.nextToken();
						MovieService.RentMovie(user,title);
						logger.info("user " + user.getUsername() + " added movie " + title);
					}
					
					break;
				case "return":
					System.out.print("Enter the movies you want to return seperated by a common:");
					titles = read.readLine();
					tokenizer = new StringTokenizer(titles, ",");
					while (tokenizer.hasMoreTokens()) {
						String title = tokenizer.nextToken();
						MovieService.ReturnMovie(user,title);
						logger.info("user " + user.getUsername() + " added movie " + title);
					}
					break;
				case "view":
					System.out.println("Your collection currently consists of");
					MovieService.viewRentedMovies(user);
					break;
				case "quit":
					System.out.print("You have quit the application.");
					logger.info("user " + user.getUsername() + " logged out");
					break;
				case "q":
					System.out.print("You have quit the application.");
					logger.info("user " + user.getUsername() + " logged out");
					break;
				default:
					System.out.println("please enter add,remove or view.");
				}
			}
		} catch (IOException ioe) {
			logger.error(ioe.getMessage(), ioe);
		} catch (NoMovieException nme) {
			logger.error(nme.getMessage(), nme);
		} catch (AlreadyHaveMovieException ahme) {
			logger.error(ahme.getMessage(), ahme);
		} catch (NotRentingMovieException nrme) {
			logger.error(nrme.getMessage(), nrme);
		} catch (MovieNotFoundException mnfe) {
			logger.error(mnfe.getMessage(), mnfe);
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
						"Type lock to lock a user, unlock to unlock a user, approve to approve a user, add to add a new movie available for rental or quit to stop:");
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
						UserService.lockUser(UserService.getUser(username));
						logger.info("admin " + admin.getUsername() + " locked " + username);
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
						
						UserService.unlockUser(UserService.getUser(username));
						logger.info("admin " + admin.getUsername() + "unlocked " + username);
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
						UserService.approveUser(UserService.getUser(username));
						logger.info("admin " + admin.getUsername() + " approved " + username);
					}
					break;
				case "add":
					System.out.print("Enter the movies you want make available for rental:");
					String titles = read.readLine();
					tokenizer = new StringTokenizer(titles, ",");
					while (tokenizer.hasMoreTokens()) {
						String title = tokenizer.nextToken();
						AdminService.addNewMovie(new Movie(title));
						logger.info("admin " + admin.getUsername() + " added movie " + title);
					}
					
					break;
				case "quit":
					System.out.print("You have quit the application.");
					logger.info("admin " + admin.getUsername() + " logged out");
					break;
				case "q":
					System.out.print("You have quit the application.");
					logger.info("admin " + admin.getUsername() + " logged out");
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
