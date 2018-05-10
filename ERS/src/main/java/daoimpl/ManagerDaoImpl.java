package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import dao.ManagerDao;
import designpattern.ConnectionUtil;
import designpattern.PersonFactory;
import model.Manager;

public class ManagerDaoImpl implements ManagerDao{
	private Logger logger;
	
	public ManagerDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public boolean createManager(String inUsername) {
		String sql = "{call create_manager(?)}";
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
	public Manager readManager(String inUsername) {
		String sql = "SELECT * FROM manager m, employee e "
				+ "WHERE m.employeeid=e.employeeid AND e.username=?";
		try(Connection c = ConnectionUtil.connect(this.logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, inUsername);
			
			ResultSet r = s.executeQuery();
			
			while(r.next()) {
				int managerid = r.getInt(1);
				int employeeid = r.getInt(2);
				String username = r.getString(5);
				String password = r.getString(6);
				return (Manager) PersonFactory
						.create("manager", employeeid, managerid, username, password);
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
}
