package com.revature.jdbc;

/**
 * Methods for communicating with the SQL database
 */

import java.util.List;

import com.revature.trade.Trade;

public interface TradeDAO {

	boolean makeTradeRequest(int idRequestor, int idAcceptor, int amount);
	List<Trade> getTradeRequest();
	boolean acceptTradeRequest(int idRequestor, int idAcceptor, int amount);
	boolean denyTradeRequest(int idRequestor, int idAcceptor, int amount);
}
