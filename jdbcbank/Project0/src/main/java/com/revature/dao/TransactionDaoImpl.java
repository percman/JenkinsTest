package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;

public class TransactionDaoImpl implements TransactionDao{
	
	private static TransactionDaoImpl instance;
	private TransactionDaoImpl() {}
	public static TransactionDaoImpl getInstance() {
		if(instance == null) {
			instance = new TransactionDaoImpl();
		}
		return instance;
	}
	
	
	public List<String> transactions(User user){
		try(Connection conn = ConnectionUtil.getConnection()){
			int index = 0;
			List<String> trans = new ArrayList<>();
			PreparedStatement stmt = conn.prepareStatement("SELECT action, amount, "
					+ "ledger_balance(transaction_time), transaction_time FROM transaction "
					+ "NATURAL JOIN person "
					+ "WHERE username = ? ORDER BY transaction_time");
			stmt.setString(++index, user.getUsername());
			ResultSet rs = stmt.executeQuery();
			System.out.println("Action  Amount  Ledger  Timestamp");
			while(rs.next()) {
				trans.add(rs.getString("action") + "  " + rs.getDouble("amount") +  "  " +
						rs.getDouble("ledger_balance(transaction_time)") + "  " + 
						rs.getTimestamp("transaction_time"));
			}
			return trans;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
			System.err.println("SQL State: " + sqle.getSQLState());
			System.err.println("Error Code : " + sqle.getErrorCode());
		}
		return null;
	}
}
