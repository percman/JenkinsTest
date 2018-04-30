package com.revature.jdbc;

import java.util.List;

import com.revature.trade.Trade;

public interface TradeDAO {

	boolean makeTradeRequest(int idRequestor, int idAcceptor, int amount);
	List<Trade> getTradeRequest();
	boolean acceptTradeRequest(int idRequestor, int idAcceptor, int amount);
	boolean denyTradeRequest(int idRequestor, int idAcceptor, int amount);
}
