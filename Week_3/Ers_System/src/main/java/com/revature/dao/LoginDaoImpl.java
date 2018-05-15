package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.model.Employee;
import com.revature.util.ConnectionUtil;

public class LoginDaoImpl implements LoginDao{
	
	private static LoginDaoImpl instance;
	
	private LoginDaoImpl() {};
	
	public static LoginDaoImpl getInstance() {
		
		System.out.println("inside the getInstance Method in LoginDao Implementation");
		if (instance == null) {
			System.out.println("the instance was NUll");
			instance = new LoginDaoImpl();
			System.out.println("new instance " + instance);
		}
		return instance;
	}
	

	public Employee getUser(String username) {
		
		System.out.println("inside getUser");
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Employee WHERE eusername = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("has next");
				return new Employee(rs.getInt("eid"),rs.getString("efname"), rs.getString("elname"), rs.getString("eusername"),
						rs.getString("epassword"), rs.getString("etype"));
			}
			else {
				
				System.out.println("inside else");
				return null;
			}
			
		} catch(SQLException sqle) {
			System.out.println("returning null from getUser");

			System.err.println(sqle.getMessage());
			System.err.println("SQL state " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		
		return null;
	}

	
	@Override
	public String getPasswordHash(Employee employee) {
		
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
				stmt.setString(++index, employee.getUsername());
				stmt.setString(++index, employee.getPassword());
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
					System.out.println(rs.getString("HASH"));		// HASH is an Alias
					return rs.getString("HASH");
					
				}
		}	catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL state " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		
	}
		return null;
	}


	
	

	

}
