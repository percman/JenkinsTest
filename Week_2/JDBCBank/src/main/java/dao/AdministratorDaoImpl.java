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
import util.ConnectionUtil;

public class AdministratorDaoImpl implements AdministratorDao{
	Logger logger;
	public AdministratorDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public Administrator readAdministrator(String name) {
		String sql = "select * from project_administrator where name=?";
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, name);
			ResultSet r = s.executeQuery();
			if(r.next())
				return new Administrator(r.getString("name"), r.getString("password"));
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return null;
	}

	public Map<String, Administrator> getAdministrators() {
		String sql = "select * from project_administrator;";
		Map<String, Administrator> administrators = new HashMap<>();
		try(Connection c = ConnectionUtil.getConnection();
				PreparedStatement s = c.prepareStatement(sql);){
			ResultSet r = s.executeQuery();
			while(r.next()) {
				String name = r.getString("name");
				String password = r.getString("password");
				Administrator a = new Administrator(name, password);
				administrators.put(name, a);
			}
				
		} catch(SQLException e) {
			this.logger.error(e.getMessage());
			this.logger.error(e.getSQLState());
			this.logger.error(e.getErrorCode());
		}
		return administrators;
	}
	
	public boolean createAdministrator(String name, String password) {
		String sql = "{call insert_administrator(?,?)}";
		try(Connection c = ConnectionUtil.getConnection();
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

}
