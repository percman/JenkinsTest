package com.revature.hs.user.dao;

import com.revature.hs.card.Card;

import java.util.List;

public interface UserDao {
	List<Card> getUserCards(int id);

}
