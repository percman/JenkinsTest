package com.revature.hs;

import java.util.*;

import org.apache.log4j.Logger;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.json.JSONArray;
import org.json.JSONObject;

public class Player extends User {
	private HashMap<String, Card> myCards;
	private int dust;
	private boolean isApproved;
	private static final Logger logger = Logger.getLogger(Player.class);
	
	public Player(JSONObject jso) {
		super(jso);
		JSONArray ja = jso.getJSONArray("myCards");
		this.myCards = new HashMap<>();
		CardCollector cc = CardCollector.getInstance();
		for (Object o: ja) {
			this.addCard(cc.getCard((String) o));
		}
		this.dust = jso.getInt("dust");
		this.isApproved = jso.getBoolean("isApproved");
	}

	public Player(String userName, String passwordHash, String role) {
		super(userName, passwordHash, role);
		this.dust = 0;
		this.isApproved = false;
		this.myCards = new HashMap<>();
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

		Deque<Card> cd = CardCollector.getInstance().openPack(CardCollector.convertSetName(whatToDo));
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
			Deque<Card> cd = CardCollector.getInstance().openPack(CardCollector.convertSetName(whatToDo));
			terminal.println("UNPACKING");
			for (Card c : cd) {
				terminal.println(c.getRarity().name() + ": " + c.getName());
				logger.debug("Unpacked a " + c.getRarity().name() + ": " + c.getName());
				addCard(c);
			}
		}
		saveState();
	}

	public void dustExtras() {
		TextIO textIO = TextIoFactory.getTextIO();
		TextTerminal terminal = textIO.getTextTerminal();
		for (Card c: myCards.values()) {
			while(c.getOwned() > 2) {
				c.setOwned(c.getOwned() - 1);
				addDust(CardCollector.getDustAmount(c));
				terminal.println("Dusted a " + c.getName());
				logger.debug("Dusted a " + c.getName());
			}
		}
		saveState();
	}

	public void saveState() {
		UserDB.getInstance().setUser(this);
	}

	@Override
	public JSONObject toJSONObject() {
		JSONObject jso = super.toJSONObject();
		List<String> cardList = new LinkedList<>();
		for (String s : myCards.keySet()) {
			Card card = myCards.get(s);
			int cardCount = card.getOwned();
			while(cardCount-- > 0) {
				cardList.add(s);
			}
		}
		jso.put("myCards", cardList);
		jso.put("dust", dust);
		jso.put("isApproved", isApproved);

		return jso;
	}
}
