package com.revature.hs.user;

import java.util.*;

import com.revature.hs.card.Card;
import com.revature.hs.card.dao.CardService;
import com.revature.hs.card.CardSorter;
import com.revature.hs.card.Rarity;
import com.revature.hs.ui.SetOptions;
import com.revature.hs.user.dao.UserService;
import org.apache.log4j.Logger;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;


import static com.revature.hs.card.Rarity.*;
import static com.revature.hs.user.dao.UserService.updatePlayer;

public class Player extends User {
	private HashMap<String, Card> myCards;
	private int dust;
	private boolean isApproved;
	private static final Logger logger = Logger.getLogger(Player.class);

	public Player(String userName, String password, String role) {
		super(userName, password, role);
		this.dust = 0;
		this.isApproved = false;
		this.myCards = new HashMap<>();
	}

	public Player(int id, String username, boolean isLocked, boolean isApproved, int dust, List<Card> userCards) {
		super(id, username, isLocked, "player");
		this.isApproved = isApproved;
		this.dust = dust;
		this.myCards = new HashMap<>();
		for (Card c : userCards) {
			this.myCards.put(c.getName(), c);
		}

	}

	// Adds a card to myCards, if the card exists it increments the owned count by one.
	public void addCard(Card card) {
		String cardName = card.getName();
		if (myCards.containsKey(cardName)) {
			myCards.get(cardName).addCopy();
		}
		else {
			myCards.put(cardName, card);
		}
		UserService.addCard(this, card);
	}

	public int getDust() {
		return dust;
	}

	public void addDust(int dust) {
		this.dust += dust;
	}

	public void removeDust(int dust) {
		this.dust -= dust;
	}


	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean approved) {
		isApproved = approved;
	}

	public void openPack() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		SetOptions whatToDo = textIO.newEnumInputReader(SetOptions.class).read(
				"Which pack do you want to open?");

		Deque<Card> cd = CardService.getInstance().openPack(CardService.convertSetName(whatToDo));
		terminal.println("UNPACKING");
		for(Card c : cd) {
			terminal.println(c.getRarity().name() + ": " + c.getName());
			logger.debug("Unpacked a " + c.getRarity().name() + ": " + c.getName());
			addCard(c);
		}
		saveState();
	}

	public void openPacks() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		SetOptions whatToDo = textIO.newEnumInputReader(SetOptions.class).read(
				"Which pack do you want to open?");
		int howMany = textIO.newIntInputReader().read("How many do you want to open?");
		while (howMany-- > 0) {
			Deque<Card> cd = CardService.getInstance().openPack(CardService.convertSetName(whatToDo));
			terminal.println("UNPACKING");
			for (Card c : cd) {
				terminal.println(c.getRarity().name() + ": " + c.getName());
				logger.debug("Unpacked a " + c.getRarity().name() + ": " + c.getName());
				addCard(c);
			}
		}
		saveState();
	}

	public void craftCard() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		logger.debug("Entering crafting");
		Card card;

		terminal.println("Crafting costs are as follows: ");
		terminal.println(COMMON.name() + ": " + CardService.getCraftCost(COMMON));
		terminal.println(RARE.name() + ": " + CardService.getCraftCost(RARE));
		terminal.println(EPIC.name() + ": " + CardService.getCraftCost(EPIC));
		terminal.println(LEGENDARY.name() + ": " + CardService.getCraftCost(LEGENDARY));
		terminal.println("Your dust: " + this.dust);

		Rarity whatRarity = textIO.newEnumInputReader(Rarity.class).read(
				"What rarity do you want to craft?");
		if (CardService.getCraftCost(whatRarity) > this.dust) {
			terminal.println("You can't afford that.");
			return;
		}
		SetOptions whatSet = textIO.newEnumInputReader(SetOptions.class).read(
				"What set do you want to craft from?");

		List<Card> cardList = CardService.getInstance().getCardList(whatSet, whatRarity);
		CardService.printCardList(cardList);
		terminal.println("");
		String numString = textIO.newStringInputReader().read("type the number of the card you want to craft" +
				", or anything else to cancel");
		try {
			this.addCard(cardList.get(Integer.parseInt(numString)));
			this.dust -= CardService.getCraftCost(whatRarity);
		}catch ( NumberFormatException e){ }
		catch (IndexOutOfBoundsException e){}
		saveState();

	}

	public void dustExtras() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		for (Card c: myCards.values()) {
			while(c.getOwned() > 2) {
				c.setOwned(c.getOwned() - 1);
				addDust(CardService.getDustAmount(c));
				terminal.println("Dusted a " + c.getName());
				logger.debug("Dusted a " + c.getName());
			}
		}
		saveState();
	}

	//TODO: finish implementing this
	public void dustCard() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		List<String> cardStrings = new LinkedList<>();
		int index = 0;
		for (String s: myCards.keySet()) {
			terminal.println(index + ": " + s);
			cardStrings.add(s);
		}
		//textIO.newStringInputReader().withPattern()
	}

	public void viewCards() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		List<Card> ll = new LinkedList<>(myCards.values());
		ll.sort(new CardSorter());
		logger.info("Printing user's cards.");
		terminal.println("My Collection:");
		terminal.println();
		for (Card c: ll) {
			terminal.println(c.getRarity().name() + ": " + c.getName() + " x" + c.getOwned());
		}
	}



	public void saveState() {
		updatePlayer(this);
	}

	public HashMap<String, Card> getMyCards() {
		return myCards;
	}
}
