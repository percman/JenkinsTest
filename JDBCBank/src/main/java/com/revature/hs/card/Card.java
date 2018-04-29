package com.revature.hs.card;

import com.revature.hs.card.dao.CardService;
import org.json.JSONObject;

public class Card {
    private String name;
    private Rarity rarity;
    private int mana;
    private JSONObject json;
    private int owned;

    public Card(JSONObject jso) throws RarityNotFoundException {
        this.name = jso.getString("name");
        this.rarity = CardService.stringToRarity(jso.getString("rarity"));
        this.owned = 1;
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
