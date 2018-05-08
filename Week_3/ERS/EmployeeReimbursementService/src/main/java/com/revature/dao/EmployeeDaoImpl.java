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
import com.revature.employee.Employee;
import com.revature.employee.GenericEmployee;
import com.revature.exceptions.EmployeeNotFoundException;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final Logger logger = Logger.getLogger(EmployeeDaoImpl.class);
	private static EmployeeDaoImpl instance;
		
		private EmployeeDaoImpl() {}
		
		public static EmployeeDaoImpl getInstance() {
			if(instance == null) {
				instance = new EmployeeDaoImpl();
			}
			return instance;
		}

		@Override
		public boolean addEmployee(GenericEmployee emp) {
			// TODO Auto-generated method stub
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				CallableStatement stmt = conn.prepareCall("{CALL insert_employee(?,?)}");
				stmt.setString(++index, emp.getUsername());
				stmt.setString(++index, emp.getPassword());
				return stmt.executeUpdate() > 0;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return false;
		}

		@Override
		public List<GenericEmployee> getEmployees() {
			// TODO Auto-generated method stub
			try(Connection conn = ConnectionUtil.getConnection()){
				List<GenericEmployee> emp= new ArrayList<>();
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE INNER JOIN GENERIC_EMPLOYEE ON gen_emp_id = emp_id ");
				ResultSet rs = stmt.executeQuery();
				
				while(rs.next()) {
					emp.add(new GenericEmployee(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Emp_First_Name"),rs.getString("Emp_Last_Name"), rs.getInt("emp_id")));
				}
				return emp;
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			return null;
		}

		@Override
		public GenericEmployee getEmployee(String emp) throws EmployeeNotFoundException {
			int index = 0;
			try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT * FROM EMPLOYEE INNER JOIN GENERIC_EMPLOYEE ON emp_username = ?");
				stmt.setString(++index, emp);
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next())
					return new GenericEmployee(rs.getString("emp_username"), rs.getString("emp_password"),rs.getString("Emp_First_Name"),rs.getString("Emp_Last_Name"), rs.getInt("emp_id"));
			} catch(SQLException sqle) {
				logger.error(sqle.getMessage(), sqle);
				logger.error(sqle.getSQLState(),sqle);
				logger.error(sqle.getErrorCode(),sqle);
			} 
			throw new EmployeeNotFoundException();
		}

		@Override
		public boolean updateInfo(GenericEmployee emp) throws EmployeeNotFoundException {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getPasswordHash(GenericEmployee emp){
			int index = 0;
			try (Connection conn = ConnectionUtil.getConnection()) {
				PreparedStatement stmt = conn.prepareStatement("SELECT get_emp_hash(?,?)AS HASH FROM dual");
				stmt.setString(++index, emp.getUsername());
				stmt.setString(++index, emp.getPassword());
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