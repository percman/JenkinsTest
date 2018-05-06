package daoimpl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import dao.EmployeeDao;
import designpattern.ConnectionUtil;
import model.Employee;

public class EmployeeDaoImpl implements EmployeeDao {
	private Logger logger;
	private ConnectionUtil connectionUtil;
	
	public EmployeeDaoImpl(Logger log) {
		super();
		this.logger = log;
		this.connectionUtil = ConnectionUtil.getInstance(logger);
	}

	@Override
	public boolean createEmployee(int inManagerId, String inUsername, String inPassword) {
		String sql = "{call create_employee(?,?,?)}";
		try(Connection c = connectionUtil.getConnection();
				CallableStatement s = c.prepareCall(sql);){
			s.setInt(1, inManagerId);
			s.setString(2, inUsername);
			s.setString(3, inPassword);
			return s.executeUpdate() > 0;
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
			return false;
		}
	}

	@Override
	public Employee readEmployee(int inEmployeeId) {
		String sql = "SELECT * FROM employee WHERE employeeid=?";
		try(Connection c = connectionUtil.getConnection();
				PreparedStatement s = c.prepareStatement(sql);){
			s.setInt(1, inEmployeeId);
			ResultSet r = s.executeQuery();
			
			int employeeId = r.getInt(1);
			int managerId = r.getInt(2);
			String username = r.getString(3);
			String password = r.getString(4);
		} catch(SQLException e) {
			logger.error(e.getMessage());
			logger.error(e.getSQLState());
			logger.error(e.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean updateEmployee(int inEmployeeId, int inManagerId, String inUsername, String inPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(int inEmployeeId) {
		// TODO Auto-generated method stub
		return false;
	}
}