package com.revature.hs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class Player extends User {
	private HashMap<String, Card> myCards;
	private int dust;
	private boolean isApproved;
	
	public Player(JSONObject jso) {
		super(jso);
		JSONArray ja = jso.getJSONArray("myCards");
		this.myCards = new ArrayList<Card>;
		CardCollector cc = CardCollector.getInstance();
		for (Object o: ja) {
			this.addCard(cc.getCard((String) o));
		}
		this.dust = jso.getInt("dust");
		this.isApproved = jso.getBoolean("isApproved");
	}

	public Player(String userName, int passwordHash, String role) {
		super(userName, passwordHash, role);
		this.dust = 0;
		this.isApproved = false;
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
		jso.put("myList", cardList);
		jso.put("dust", dust);
		jso.put("isApproved", isApproved);

		return jso;
	}
}
