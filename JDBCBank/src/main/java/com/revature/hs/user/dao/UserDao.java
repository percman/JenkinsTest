package com.revature.hs.user.dao;

import com.revature.hs.card.Card;
import com.revature.hs.user.Admin;
import com.revature.hs.user.Player;
import com.revature.hs.user.User;

import java.util.List;

public interface UserDao {
	List<Card> getUserCards(int id);
	User getUser(String username);
	void updateAdmin(Admin admin);
	void updatePlayer(Player player);
	boolean isUser(String username);
}
