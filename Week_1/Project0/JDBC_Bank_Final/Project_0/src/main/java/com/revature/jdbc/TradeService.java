package com.revature.jdbc;

import java.util.List;

import com.revature.trade.Trade;

public class TradeService {

	private TradeService() {}
	
	private static TradeDAO dao = new TradeDAOImplementation();
	
	public static boolean makeTradeRequest(int idRequestor, int idAcceptor, int amount) {
		return dao.makeTradeRequest(idRequestor, idAcceptor, amount);
	}
	
	public static List<Trade> getTradeRequest() {
		return dao.getTradeRequest();
	}
	
	public static boolean acceptTradeRequest(int idRequestor, int idAcceptor, int amount) {
		return dao.acceptTradeRequest(idRequestor, idAcceptor, amount);
	}
	
	public static boolean denyTradeRequest(int idRequestor, int idAcceptor, int amount) {
		return dao.denyTradeRequest(idRequestor, idAcceptor, amount);
	}
}
