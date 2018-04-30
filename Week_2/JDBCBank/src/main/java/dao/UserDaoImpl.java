package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import model.Administrator;
import model.User;
import util.ConnectionUtil;

public class UserDaoImpl implements UserDao{
	Logger logger;
	
	public UserDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public boolean createUser(User user) {
		String sql = "{call create_user(?,?)}";
		try (Connection c = ConnectionUtil.getConnection();
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

	@Override
	public User readUser(String username) {
		String sql = "select * from project_user where name=?";
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, username);
			ResultSet r = s.executeQuery();
			if(r.next()) {
				String name = r.getString("name");
				String password = r.getString("password");
				int balance = getBalance(name);
				boolean lock = r.getBoolean("lock");
				return new User(name, password, balance, lock);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return null;
	}	

	@Override
	public boolean deleteUser(User user) {
		String sql = "delete from project_user where(name=?);";
		try(Connection c = ConnectionUtil.getConnection();
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
	
	@Override
	public boolean approveUser(User user, Administrator administrator) {
		return false;
	}

	@Override
	public boolean rejectUser(User user, Administrator administrator) {
		return false;
	}

	@Override
	public boolean transact(User user, int amount) {
		String sql = "{call create_transaction(?,?)}";
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement s = c.prepareStatement(sql);){
			s.setInt(1, user.getId());
			s.setInt(2, amount);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}

	public Map<String, User> getUsers() {
		String sql = "select * from project_user";
		Map<String, User> users = new HashMap<>();
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement s = c.prepareStatement(sql);){
			ResultSet r = s.executeQuery();
			while(r.next()) {
				String name = r.getString("name");
				String password = r.getString("password");
				int balance = getBalance(name);
				boolean lock = r.getBoolean("lock");
				User u = new User(name, password, balance, lock);
				users.put(name, u);
			}
		} catch(SQLException e) {
			this.logger.error(e.getMessage());
			this.logger.error(e.getSQLState());
			this.logger.error(e.getErrorCode());
		}
		return users;
	}
	
	public int getBalance(String name) {
		String sql = "select * from project_transaction t, project_user u where";
		return 0;
	}

	@Override
	public boolean lock(User user) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	

}
