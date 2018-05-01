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
import model.Administrator;
import model.PersonFactory;
import util.ConnectionUtil;


public class AdministratorDaoImpl implements AdministratorDao{
	private Logger logger;
	public AdministratorDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public Administrator readAdministrator(String name) {
		String sql = "select * from project_administrator where name=?";
		try(Connection c = ConnectionUtil.getConnection(logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, name);
			ResultSet r = s.executeQuery();
			if(r.next()) {
				String username = r.getString("name");
				String password = r.getString("password");
				return (Administrator) PersonFactory.create("administrator", username, password, 0, false);
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

	public Map<String, Administrator> getAdministrators() {
		String sql = "select * from project_administrator;";
		Map<String, Administrator> administrators = new HashMap<>();
		try(Connection c = ConnectionUtil.getConnection(logger);
				PreparedStatement s = c.prepareStatement(sql);){
			ResultSet r = s.executeQuery();
			while(r.next()) {
				String name = r.getString("name");
				String password = r.getString("password");
				Administrator a = (Administrator) PersonFactory.create("administrator", name, password, 0, false);
				administrators.put(name, a);
			}
				
		} catch(SQLException e) {
			this.logger.error(e.getMessage());
			this.logger.error(e.getSQLState());
			this.logger.error(e.getErrorCode());
		} catch(CreatePersonException e) {
			logger.error(e.getMessage());
		}
		return administrators;
	}
	
	public boolean createAdministrator(String name, String password) {
		String sql = "{call create_administrator(?,?)}";
		try(Connection c = ConnectionUtil.getConnection(logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, name);
			s.setString(2, password);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
	
	public boolean approveUser(String username, String adminname) {
		String sql = "{call approve_user(?,?)}";
		try(Connection c = ConnectionUtil.getConnection(logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, username);
			s.setString(2, adminname);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
	
	public boolean rejectUser(String username, String adminname) {
		String sql = "{call reject_user(?,?)}";
		try(Connection c = ConnectionUtil.getConnection(logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, username);
			s.setString(2, adminname);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
	
	public boolean lockUser(String name) {
		String sql = "{call lock_user(?)}";
		try(Connection c = ConnectionUtil.getConnection(logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, name);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
	
	public boolean unlockUser(String name) {
		String sql = "{call lock_user(?)}";
		try(Connection c = ConnectionUtil.getConnection(logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, name);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return false;
	}
}
