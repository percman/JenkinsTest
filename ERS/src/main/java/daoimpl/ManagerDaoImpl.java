package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import dao.ManagerDao;
import design.ConnectionUtil;
import design.PersonFactory;
import model.Manager;

public class ManagerDaoImpl implements ManagerDao{
	private Logger logger;
	private static ManagerDaoImpl dao;
	
	public static ManagerDaoImpl getInstance(Logger logger) {
		if(dao == null)
			dao = new ManagerDaoImpl(logger);
		return dao;
	}
	
	private ManagerDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public boolean createManager(String inUsername, String inPassword) {
		String sql = "{call create_manager(?, ?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inUsername);
			s.setString(2, inPassword);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Manager readManager(String inUsername) {
		String sql = "SELECT * FROM manager WHERE username=?";
		try(Connection c = ConnectionUtil.connect(this.logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, inUsername);
			
			ResultSet r = s.executeQuery();
			
			while(r.next()) {
				int id = r.getInt(1);
				String username = r.getString(2);
				String password = r.getString(3);
				return (Manager) PersonFactory
						.create("manager", id, username, password);
			}
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public boolean deleteManager(String inUsername) {
		String sql = "{call delete_manager(?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inUsername);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean authenticateManager(String inUsername, String inPassword) {
		// TODO Auto-generated method stub
		return true;
	}
}
