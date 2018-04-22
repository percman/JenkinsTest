package com.revature.hs;

import org.json.JSONObject;

public class Card {
    private String name;
    private Rarity rarity;
    private int mana;
    private JSONObject json;
    private int owned;

    public Card(JSONObject jso) throws RarityNotFoundException {
        this.name = jso.getString("name");
        this.rarity = CardCollector.stringToRarity(jso.getString("rarity"));
        this.mana = jso.getInt("cost");
        this.owned = jso.getInt("owned");
        this.json = jso;
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

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public JSONObject getJson() {
		return json;
	}

	public void setJson(JSONObject json) {
		this.json = json;
	}

	public int getOwned() {
		return owned;
	}

	public void setOwned(int owned) {
		this.owned = owned;
	}
}
