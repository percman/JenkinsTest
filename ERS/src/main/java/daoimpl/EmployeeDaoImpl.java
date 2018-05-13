package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import dao.EmployeeDao;
import design.ConnectionUtil;
import design.PersonFactory;
import model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	private Logger logger;
	private static EmployeeDaoImpl dao;
	
	public static EmployeeDaoImpl getInstance(Logger logger) {
		if(dao == null)
			dao = new EmployeeDaoImpl(logger);
		return dao;
	}
	
	private EmployeeDaoImpl(Logger logger) {
		super();
		this.logger = logger;
	}

	@Override
	public boolean createEmployee(String inUsername, String inPassword) {
		String sql = "{call create_employee(?,?)}";
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
	public Employee readEmployee(String inUsernamee) {
		String sql = "SELECT * FROM employee WHERE username=?";
		try(Connection c = ConnectionUtil.connect(this.logger);
				PreparedStatement s = c.prepareStatement(sql);){
			s.setString(1, inUsernamee);
			ResultSet r = s.executeQuery();
			while(r.next()) {
				int inEmployeeid = r.getInt(1);
				int inManagerid = r.getInt(2);
				String inUsername = r.getString(3);
				String inPassword = r.getString(4);
				return (Employee)
						PersonFactory.create("employee", inEmployeeid, inManagerid, inUsername, inPassword);
			}
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		return null;
	}
	
	@Override
	public List<Employee> readEmployees(){
		String sql = "SELECT * FROM employee";
		try(Connection c = ConnectionUtil.connect(this.logger);
				PreparedStatement s = c.prepareStatement(sql);){
			ResultSet r = s.executeQuery();
			List<Employee> employees = new ArrayList<>();
			while(r.next()) {
				int inEmployeeid = r.getInt(1);
				int inManagerid = r.getInt(2);
				String inUsername = r.getString(3);
				String inPassword = r.getString(4);
				Employee e = (Employee)
						PersonFactory.create("employee", inEmployeeid, inManagerid, inUsername, inPassword);	
				employees.add(e);
			}
			return employees;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateEmployee(String inManagerUsername, String inUsername, String inPassword) {
		String sql = "{call update_employee(?,?,?)}";
		try(Connection c = ConnectionUtil.connect(this.logger);
				CallableStatement s = c.prepareCall(sql);){
			s.setString(1, inManagerUsername);
			s.setString(2, inUsername);
			s.setString(3, inPassword);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean deleteEmployee(String inUsername) {
		String sql = "{call delete_employee(?)}";
		try(Connection c = ConnectionUtil.connect(logger);
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
	public boolean authenticateEmployee(String inUsername, String inPassword) {
		//TODO
		return true;
	}
}