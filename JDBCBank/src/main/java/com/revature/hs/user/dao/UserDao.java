package com.revature.hs.user.dao;

import com.revature.hs.card.Card;
import com.revature.hs.user.Admin;
import com.revature.hs.user.Player;
import com.revature.hs.user.User;

import java.util.List;

public interface UserDao {
	List<Card> getUserCards(int id);
	User getUser(String username);
	boolean updateAdmin(Admin admin);
	boolean updatePlayer(Player player);
	boolean isUser(String username);
	boolean addCard(Player player, Card card);
	String hashPassword(String username, String password);
	String getStoredHash(String username);
}
