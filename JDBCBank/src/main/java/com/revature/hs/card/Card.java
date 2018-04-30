package com.revature.hs.card;

import com.revature.hs.card.dao.CardService;
import org.json.JSONObject;

public class Card {
    private String name;
    private Rarity rarity;
    private JSONObject json;
    private int owned;
    private int id;
    private String set;

	public Card(int id, String name, String rarityString, String cardSetString) throws RarityNotFoundException {
		this.id = id;
		this.name = name;
		this.rarity = CardService.stringToRarity(rarityString);
		this.owned = 1;
		this.set = cardSetString;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void addCopy() {
    	this.owned += 1;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rarity getRarity() {
		return rarity;
	}

	public void setRarity(Rarity rarity) {
		this.rarity = rarity;
	}

	public int getOwned() {
		return owned;
	}

	public void setOwned(int owned) {
		this.owned = owned;
	}
}
