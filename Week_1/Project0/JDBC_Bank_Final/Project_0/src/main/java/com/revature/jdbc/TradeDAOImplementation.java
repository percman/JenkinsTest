package com.revature.jdbc;

import static com.revature.logger.BankLogger.logger;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.trade.Trade;

public class TradeDAOImplementation implements TradeDAO {

	Connection conn = ConnectionObject.getInstance();
	
	@Override
	public boolean makeTradeRequest(int idRequestor, int idAcceptor, int amount) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL make_trade_request(?,?,?)}");
			stmt.setInt(++index, idRequestor);
			stmt.setInt(++index, idAcceptor);
			stmt.setInt(++index, amount);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tRequest made!");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean acceptTradeRequest(int idRequestor, int idAcceptor, int amount) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL accept_trade_request(?,?,?)}");
			stmt.setInt(++index, idRequestor);
			stmt.setInt(++index, idAcceptor);
			stmt.setInt(++index, amount);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tTrade accepted!");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean denyTradeRequest(int idRequestor, int idAcceptor, int amount) {
		int index = 0;
		try {
			CallableStatement stmt = conn.prepareCall("{CALL deny_trade_request(?,?,?)}");
			stmt.setInt(++index, idRequestor);
			stmt.setInt(++index, idAcceptor);
			stmt.setInt(++index, amount);
			int rowsAffected = stmt.executeUpdate();
			if(rowsAffected > 0) {System.out.println("\tTrade denied!");};
			return rowsAffected > 0;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return false;
	}
	
	@Override
	public List<Trade> getTradeRequest() {
		try {
			List<Trade> trade = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM trade_request WHERE trade_accepted <> 1 AND trade_accepted <> -1");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				trade.add(new Trade(rs.getInt("requester_accountnumber"),rs.getInt("acceptor_accountnumber"),rs.getInt("trade_amount")));
			}
			return trade;
		} catch (SQLException sqle) {
			logger.warn(sqle.getMessage());
			logger.warn("SQL State: " + sqle.getSQLState());
			logger.warn("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}
}
