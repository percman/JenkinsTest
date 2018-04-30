package com.revature.hs.card.dao;

import com.revature.hs.card.Card;
import com.revature.hs.card.RarityNotFoundException;
import com.revature.hs.util.ConnectionUtil;
import oracle.jdbc.internal.OracleTypes;
import oracle.jdbc.oracore.OracleType;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.revature.hs.card.dao.CardService.getCard;

public class CardDaoImpl implements CardDao {
	private static CardDaoImpl instance;
	private static final Logger logger = Logger.getLogger(CardDaoImpl.class);

	private CardDaoImpl(){}

	public static CardDaoImpl getInstance() {
		if (instance == null) {
			instance = new CardDaoImpl();
		}
		return instance;
	}

	@Override
	public List<Card> getAllCards(String cardSetString) {
		List<Card> output = new LinkedList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM card WHERE card_set = ?");
			stmt.setString(1, cardSetString);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				output.add(new Card(rs.getInt("id"), rs.getString("name"),
						rs.getString("rarity"), cardSetString));
			}
			return output;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		} catch (RarityNotFoundException e) {
			logger.error("Invalid rarity.");

		}
		return null;
	}

	@Override
	public List<Card> getAllCards(String rarityString, String cardSetString) {
		List<Card> output = new LinkedList<>();
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM card WHERE card_set = ? AND rarity = ?");
			stmt.setString(1, cardSetString);
			stmt.setString(2, rarityString);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				output.add(new Card(rs.getInt("id"), rs.getString("name"),
						rarityString, cardSetString));
			}
			return output;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		} catch (RarityNotFoundException e) {
			logger.error("Invalid rarity.");

		}
		return null;
	}

	@Override
	public Card getCard(String rarityString, String cardSetString) {
		int index = 0;
		logger.debug("rarity: " + rarityString );
		logger.debug("set: " + cardSetString);
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement cs = conn.prepareCall("SELECT get_card_by_rarity(?, ?) num FROM dual");
			cs.setString(++index, rarityString);
			cs.setString(++index, cardSetString);
			ResultSet rs = cs.executeQuery();
			if (rs.next()) {
				return getCard(rs.getInt("num"));
			}
//			if (cc.next()) {
//				logger.debug("cur.next");
//
//			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}

	@Override
	public Card getCard(int id) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM card WHERE id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Card(id, rs.getString("name"),
						rs.getString("rarity"), rs.getString("card_set"));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		} catch (RarityNotFoundException e) {
			logger.error("Invalid rarity.");

		}
		return null;
	}
}
