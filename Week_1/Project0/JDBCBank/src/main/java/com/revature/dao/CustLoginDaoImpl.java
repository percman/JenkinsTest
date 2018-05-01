package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.revature.model.CustLogin;
import com.revature.util.ConnectionUtil;

public class CustLoginDaoImpl implements CustLoginDao {
	
private static CustLoginDaoImpl instance;
	
	private CustLoginDaoImpl() {}
	
	public static CustLoginDaoImpl getInstance() {
		if (instance == null) {
			instance = new CustLoginDaoImpl();
		}
		return instance;
	}

	@Override
	public List<CustLoginDao> getAllCustLoginDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustLoginDao getUserName(String username) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM CUST_LOGIN WHERE CUSERNAME = ?");
			stmt.setString(++index, username);
			ResultSet rs = stmt.executeQuery();
		
		} catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL state " + sqle.getSQLState());
			System.err.println("Error Code: " + sqle.getErrorCode());
		}
		return null;
	}



	@Override
	public CustLoginDao getPassword(String password) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public String getPasswordHash(CustLogin user) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
				PreparedStatement stmt = conn.prepareStatement("SELECT get_user_hash(?,?) AS HASH FROM dual");
				stmt.setString(++index, user.getUsername());
				stmt.setString(++index, user.getPassowrd());
				ResultSet rs = stmt.executeQuery();
				if(rs.next()) {
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
