package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.connection.ConnectionUtil;
import com.revature.employee.FinanceManager;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;

public class ManagerDaoImpl implements ManagerDao {
		private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
		private static ManagerDaoImpl instance;
			
			private ManagerDaoImpl() {}
			
			public static ManagerDaoImpl getInstance() {
				if(instance == null) {
					instance = new ManagerDaoImpl();
				}
				return instance;
			}

			@Override
			public boolean addManager(FinanceManager man) {
				int index = 0;
				try(Connection conn = ConnectionUtil.getConnection()){
					CallableStatement stmt = conn.prepareCall("{CALL insert_employee(?,?)}");
					stmt.setString(++index, man.getUsername());
					stmt.setString(++index, man.getPassword());
					return stmt.executeUpdate() > 0;
				} catch(SQLException sqle) {
					logger.error(sqle.getMessage(), sqle);
					logger.error(sqle.getSQLState(),sqle);
					logger.error(sqle.getErrorCode(),sqle);
				} 
				return false;
			}

			@Override
			public List<FinanceManager> getManagers() {
				try(Connection conn = ConnectionUtil.getConnection()){
					List<FinanceManager> man= new ArrayList<>();
					PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE INNER JOIN FINANCE_MANAGER ON man_id = emp_id ");
					ResultSet rs = stmt.executeQuery();
					
					while(rs.next()) {
						man.add(new FinanceManager(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Man_First_Name"),rs.getString("Man_Last_Name"), rs.getInt("emp_id")));
					}
					return man;
				} catch(SQLException sqle) {
					logger.error(sqle.getMessage(), sqle);
					logger.error(sqle.getSQLState(),sqle);
					logger.error(sqle.getErrorCode(),sqle);
				} 
				return null;
			}

			@Override
			public FinanceManager getManager(String man) throws EmployeeNotFoundException {
				int index = 0;
				try(Connection conn = ConnectionUtil.getConnection()){
					PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE INNER JOIN FINANCE_MANAGER ON emp_username = ?");
					stmt.setString(++index, man);
					ResultSet rs = stmt.executeQuery();
					
					if (rs.next())
						return new FinanceManager(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Man_First_Name"),rs.getString("Man_Last_Name"), rs.getInt("emp_id"));
				} catch(SQLException sqle) {
					logger.error(sqle.getMessage(), sqle);
					logger.error(sqle.getSQLState(),sqle);
					logger.error(sqle.getErrorCode(),sqle);
				} 
				throw new EmployeeNotFoundException();
			}

			@Override
			public boolean updateInfo(FinanceManager man) throws EmployeeNotFoundException {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public String getPasswordHash(FinanceManager man){
				int index = 0;
				try (Connection conn = ConnectionUtil.getConnection()) {
					PreparedStatement stmt = conn.prepareStatement("SELECT GET_EMP_HASH(?,?)AS HASH FROM dual");
					stmt.setString(++index, man.getUsername());
					stmt.setString(++index, man.getPassword());
					ResultSet rs = stmt.executeQuery();
					if (rs.next())
						return rs.getString("HASH");
				} catch(SQLException sqle) {
					logger.error(sqle.getMessage(), sqle);
					logger.error(sqle.getSQLState(),sqle);
					logger.error(sqle.getErrorCode(),sqle);
				} 
				return null;
			}

		 
	
}
