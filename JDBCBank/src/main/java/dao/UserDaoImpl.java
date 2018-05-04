package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import exception.CreatePersonException;
import model.PersonFactory;
import model.User;
import util.ConnectionUtil;

public class UserDaoImpl implements UserDao{
	private Logger logger;
	
	public UserDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}
	
	public Map<String, User> getUsers() {
		String sql = "select * from project_user";
		Map<String, User> users = new HashMap<>();
		try(Connection c = ConnectionUtil.getConnection(logger);
				PreparedStatement s = c.prepareStatement(sql);){
			ResultSet r = s.executeQuery();
			while(r.next()) {
				String name = r.getString("name");
				String password = r.getString("password");
				int balance = readUserBalance(name);
				boolean lock = r.getBoolean("lock");
				User u = (User) PersonFactory.create("users", name, password, balance, lock);
				users.put(name, u);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		} catch(CreatePersonException e) {
			logger.error(e);
		}
		return users;
	}
	
	public User readUser(String username) {
		String sql = "select * from project_user where name=?";
		try(Connection c = ConnectionUtil.getConnection(logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, username);
			ResultSet r = s.executeQuery();
			if(r.next()) {
				String name = r.getString("name");
				String password = r.getString("password");
				int balance = readUserBalance(name);
				boolean lock = r.getBoolean("lock");
				return (User) PersonFactory.create("user", name, password, balance, lock);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		} catch(CreatePersonException e) {
			logger.error(e);
		}
		return null;
	}	

	public boolean deleteUser(User user) {
		String sql = "delete from project_user where(name=?);";
		try(Connection c = ConnectionUtil.getConnection(logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, user.getName());
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
	
	public boolean createTransaction(String name, int amount) {
		String sql = "{call create_transaction(?,?)}";
		try(Connection c = ConnectionUtil.getConnection(logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, name);
			s.setInt(2, amount);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
	
	public boolean createUser(User user) {
		String sql = "{call create_user(?,?)}";
		try (Connection c = ConnectionUtil.getConnection(logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, user.getName());
			s.setString(2, user.getPassword());
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
	
	public int readUserBalance(String name) {
		String sql = "{? = call get_user_balance(??)}";
		try(Connection c = ConnectionUtil.getConnection(logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, name);
			s.executeUpdate();
			return s.getInt(1);
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return 0;
	}
}
