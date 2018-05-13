package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import dao.InformationDao;
import design.ConnectionUtil;
import model.Information;

public class InformationDaoImpl implements InformationDao{
	private Logger logger;
	private static InformationDaoImpl dao;
	
	public static InformationDaoImpl getInstance(Logger logger) {
		if(dao == null)
			dao = new InformationDaoImpl(logger);
		return dao;
	}
	
	private InformationDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public boolean createInformation(String inUsername, String inFirstname, 
			String inMiddlename, String inLastname) {
		String sql = "{call create_information(?,?,?,?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inUsername);
			s.setString(2, inFirstname);
			s.setString(3, inMiddlename);
			s.setString(4, inLastname);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public Information readInformation(String inUsername) {
		String sql = "SELECT * FROM information WHERE employeeid="
				+ "(SELECT employeeid FROM employee WHERE username=?)";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inUsername);
			
			ResultSet r = s.executeQuery();
			
			while(r.next()) {
				int informationid = r.getInt(1);
				int employeeid = r.getInt(2);
				String firstname = r.getString(3);
				String middlename = r.getString(4);
				String lastname = r.getString(5);
				return new Information(informationid, employeeid,
						firstname, middlename, lastname);
			}
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateInformation(String inUsername, String inFirstname, 
			String inMiddlename, String inLastname) {
		String sql = "{call update_information(?,?,?,?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inUsername);
			s.setString(2, inFirstname);
			s.setString(3, inMiddlename);
			s.setString(4, inLastname);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteInformation(String inUsername) {
		String sql = "{call delete_information(?)}";
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
