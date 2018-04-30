package com.revature.hs.ui;

import com.revature.hs.card.dao.CardService;
import com.revature.hs.user.Admin;
import com.revature.hs.user.Player;
import com.revature.hs.user.User;
import com.revature.hs.user.dao.UserService;
import com.revature.hs.user.exceptions.*;
import org.apache.log4j.Logger;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.mindrot.jbcrypt.BCrypt;

public class Core {
	private UserService users;
	private User activeUser;
	private CardService collector;
	private static final Logger logger = Logger.getLogger(Core.class);
	private static TextIO textIO;
	private static TextTerminal terminal;

	private void initializeInteractions() {
		System.setProperty("org.beryx.textio.TextTerminal", "org.beryx.textio.system.SystemTextTerminal");
		textIO = TextIoFactory.getTextIO();
		terminal = textIO.getTextTerminal();
		while(true) {
			StartOptions whatToDo = textIO.newEnumInputReader(StartOptions.class).read("What do you want to do?");
			if (whatToDo == StartOptions.SIGNUP) {
				signup();
			} else if (whatToDo == StartOptions.LOGIN) {
				login();
				if (activeUser != null) {
					if (activeUser instanceof Admin) {
						adminOptions();
					}
					else {
						playerOptions();
					}
				}
			} else {
				terminal.println("Goodbye!");
				System.exit(0);
			}
		}
	}
	
	private void adminOptions() {
		Admin admin = (Admin) activeUser;
		while (true) {
			AdminOptions whatToDo = textIO.newEnumInputReader(AdminOptions.class).read(
					"What do you want to do?");
			switch (whatToDo) {
				case ADD_ADMIN:
					admin.addAdmin();
					break;
				case LOCK_ACCOUNT:
					admin.lockUser();
					break;
				case UNLOCK_ACCOUNT:
					admin.unlockUser();
					break;
				case APPROVE_ACCOUNT:
					admin.approveUsers();
					break;
				case LOGOUT:
					return;
			}
		}
	}
	
	private void playerOptions() {
		Player player = (Player) activeUser;
		while (true) {
			PlayerOptions whatToDo = textIO.newEnumInputReader(PlayerOptions.class).read(
					"What do you want to do?");
			switch (whatToDo) {
				case OPEN_PACK:
					player.openPack();
					break;
				case OPEN_PACKS:
					player.openPacks();
					break;
				case VIEW_DUST:
					terminal.println(player.getDust() + " dust available.");
					break;
				case DUST_EXTRAS:
					player.dustExtras();
					break;
				case VIEW_CARDS:
					player.viewCards();
					break;
				case CRAFT_CARD:
					player.craftCard();
					break;
				case LOGOUT:
					return;
			}
		}

	}
	
	private void signup() {
		terminal.println("Welcome, new player! If you're looking for admin credentials, check the readme." +
		" In this system, only an admin can create another admin");
		String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read("enter username");
		String passwordHash = BCrypt.hashpw(textIO.newStringInputReader().withMinLength(5).read("enter password"),
				BCrypt.gensalt());

		try {
			UserService.getInstance().addUser(new Player(user, passwordHash, "player"));
			logger.info("Created new user " + user);
		} catch (DuplicateUserNameException e) {
			logger.info("Attempted user creation with duplicate username " + user);
			terminal.println("Sorry, a user with that name already exists");
		}
		terminal.println("User created! Note that you won't be able to login until an admin approves your account~");
	}

	private void login() {

		while(true) {
			String user = textIO.newStringInputReader().withMinLength(4).withIgnoreCase().read("enter username");
			String password = textIO.newStringInputReader().withMinLength(5).read("enter password");
			try {
				logger.info("Attempting login with user " + user);
				activeUser = users.login(user, password);
				logger.info("Logged in!");
				break;
			} catch (NoSuchUserException e) {
				logger.info("That user doesn't exist! " + user);
				terminal.println("That user doesn't exist!");
			} catch (WrongPasswordException e) {
				logger.info("Wrong pw for " + user);
				terminal.println("Wrong password.");
 			} catch (LockedAccountException e) {
				logger.info("locked account " + user);
				terminal.println("Your account is locked!");
			} catch(UnApprovedUserException e) {
				logger.info("unapproved user attempted login");
				terminal.println("You have to be approved by an admin first!");
			}
		}
	}
	
	public static void main(String[] args) {
		Core c = new Core();
		c.users = UserService.getInstance();
		c.collector = CardService.getInstance();
		c.initializeInteractions();
	}
}
