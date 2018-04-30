package com.revature.hs.card.dao;

import com.revature.hs.card.Card;
import com.revature.hs.card.Rarity;

import java.util.List;

public interface CardDao {
	List<Card> getAllCards();
	Card getCard(Rarity r);
	Card getCard(int id);
}
