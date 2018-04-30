package com.revature.hs.card.dao;

import com.revature.hs.card.Card;
import com.revature.hs.card.Rarity;

import java.util.List;

public interface CardDao {
	List<Card> getAllCards(String cardSetString);
	List<Card> getAllCards(String rarityString, String cardSetString);
	Card getCard(String rarityString, String cardSetString);
	Card getCard(int id);
	boolean addCard(
}
