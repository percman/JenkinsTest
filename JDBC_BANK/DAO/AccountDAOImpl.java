package com.revature.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.utility.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {

	@Override
	public Double getFunds(int accountID, User user) {
		String sql = "SELECT get_account_funds(?,?) AS money FROM dual";
		int index =0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(++index, accountID);
			stmt.setString(++index, user.getUserName());
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
				return rs.getDouble("money");
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean setFunds(int accountID, Double funds) {
		String sql = "UPDATE account SET funds = ? WHERE account_id =?";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setDouble(++index, funds);
			stmt.setInt(++index, accountID);
			int success = stmt.executeUpdate();
			return success > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}return false;
	}

	@Override
	public List<User> getAllAccounts(User user) {
		String sql = "SELECT * FROM account WHERE username =?";
		int index = 0;
		List<User> accounts = new ArrayList<>();
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, user.getUserName());
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				accounts.add(new User(rs.getInt("account_id"),rs.getString("username"),rs.getDouble("funds")));
			}
			return accounts;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return null;
	}

	@Override
	public boolean insertAccount(Account user) {
		String sql = "INSERT INTO account(username,funds) VALUES(?,?)";
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(++index, user.getUserName());
			stmt.setDouble(++index, user.getFunds());
			int rowsAffected = stmt.executeUpdate();
			return rowsAffected > 0;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: "+ sqle.getSQLState());
			System.err.println("Error Code: "+sqle.getErrorCode());
		}
		return false;
	}

}
