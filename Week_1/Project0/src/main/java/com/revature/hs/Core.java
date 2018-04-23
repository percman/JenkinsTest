package com.revature.hs;

import org.apache.log4j.Logger;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

public class Core {
	private UserDB users;
	private User activeUser;
	private CardCollector collector;
	private static final Logger logger = Logger.getLogger(Core.class);
	private void initializeInteractions() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		
		StartOptions whatToDo = textIO.newEnumInputReader(StartOptions.class).read("What do you want to do?");
		if (whatToDo == StartOptions.SIGNUP) {
			signup();
		}
		else {
			terminal.printf("%s", whatToDo);
		}
	}
	
	private void adminOptions() {}
	
	private void userOptions() {}
	
	private void signup() {
		
	}
	
	public static void main(String[] args) {
		Core c = new Core();
		c.users = UserDB.getInstance();
		c.collector = CardCollector.getInstance();
		c.initializeInteractions();
	}
}
