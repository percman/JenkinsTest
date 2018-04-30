package com.revature.hs.user.dao;

import com.revature.hs.card.Card;
import com.revature.hs.user.Admin;
import com.revature.hs.user.Player;
import com.revature.hs.user.User;
import com.revature.hs.util.ConnectionUtil;
import org.apache.log4j.Logger;
import sun.awt.image.ImageWatched;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static com.revature.hs.card.dao.CardService.getCard;

public class UserDaoImpl implements UserDao {
	private static UserDaoImpl instance;
	private static final Logger logger = Logger.getLogger(UserDaoImpl.class);

	private UserDaoImpl(){}

	public static UserDaoImpl getInstance() {
		if (instance == null) {
			instance = new UserDaoImpl();
		}
		return instance;
	}
	@Override
	public List<Card> getUserCards(int id) {
		int index = 0;
		List<Card> cardList = new LinkedList<>();
		Card tmp;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT card_id, owned FROM player_card WHERE player_id = ?");
			stmt.setInt(++index, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				tmp = getCard(rs.getInt("card_id"));
				tmp.setOwned(rs.getInt("owned"));
				cardList.add(tmp);
				return cardList;
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}

	@Override
	public User getUser(String username) {
		int index = 0;
		int id;
		String role;
		boolean isLocked;

		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT id, role, is_locked FROM hs_user WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				id = rs.getInt(++index);
				role = rs.getString(++index);
				isLocked = rs.getString(++index) == "1" ? true : false;
				if (role.equals("admin")) {
					return new Admin(id, username, isLocked);
				}
				return getPlayer(id, username, isLocked);
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}

	private Player getPlayer(int id, String username, boolean isLocked) {
		int index = 0;
		int dust;
		boolean isApproved;

		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT dust, isApproved FROM player WHERE id = ?");
			stmt.setInt(++index, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				dust = rs.getInt("dust");
				isApproved = rs.getString("isApproved") == "1";
				return new Player(id, username, isLocked, isApproved, dust, getUserCards(id));
			}
			throw new SQLException("ResultSet contained no results");
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean updateAdmin(Admin admin) {
		return updateUser(admin);
	}

	private boolean updateUser(User user) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE hs_user set username=?," +
					" password=?, is_locked=? WHERE id=?");
			stmt.setString(++index, user.getUserName());
			stmt.setString(++index, user.getPassword());
			stmt.setString(++index, user.isLocked() ? "1" : "0");
			stmt.setInt(++index, user.getId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean updatePlayer(Player player) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE player set dust = ?, is_approved = ?" +
					" WHERE id = ?");
			stmt.setInt(++index, player.getDust());
			stmt.setString(++index, player.isApproved() ? "1" : "0");
			stmt.setInt(++index, player.getId());
			return (stmt.executeUpdate() == 1) && updateUser(player) && updatePlayerCards(player);
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return false;
	}

	// Note: due to short-circuiting, if one card doesn't update successfully, then it'll stop updating the
	// rest of the player's cards
	private boolean updatePlayerCards(Player p) {
		boolean successful = true;
		for (Card c: p.getMyCards().values()) {
			successful = successful && updatePlayerCard(p, c);
		}
		return successful;
	}

	private boolean updatePlayerCard(Player p, Card c) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("UPDATE player_card set owned = ? WHERE " +
					"player_id = ? AND card_id = ?");
			stmt.setInt(++index, c.getOwned());
			stmt.setInt(++index, p.getId());
			stmt.setInt(++index, c.getId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return false;
	}


	@Override
	public boolean addPlayer(Player player) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT insert_player(?, ?) FROM DUAL");
			stmt.setString(1, player.getUserName());
			stmt.setString(2, player.getPassword());
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean addAdmin(Admin admin) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT insert_admin(?, ?) FROM DUAL");
			stmt.setString(1, admin.getUserName());
			stmt.setString(2, admin.getPassword());
			return stmt.executeUpdate() > 0;

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean isUser(String username) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT is_user(?) FROM DUAL");
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) == 1;
			}

		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return false;
	}

	@Override
	public boolean addCard(Player player, Card card) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			CallableStatement stmt = conn.prepareCall("{CALL add_card_to_player(?,?)}");
			stmt.setInt(++index, player.getId());
			stmt.setInt(++index, card.getId());
			return stmt.executeUpdate() > 0;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return false;
	}

	@Override
	public String hashPassword(String username, String password) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?, ?) hashed from dual");
			stmt.setString(++index, username);
			stmt.setString(++index, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("hashed");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}

	@Override
	public String getStoredHash(String username) {
		int index = 0;
		try (Connection conn = ConnectionUtil.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement("SELECT password FROM hs_user WHERE username = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("password");
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
			logger.error("SQL STATE: " + e.getSQLState());
			logger.error("ERROR CODE: " + e.getErrorCode());
		}
		return null;
	}
}
