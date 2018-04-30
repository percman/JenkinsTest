package com.revature.Dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;


public class AccountDaoImpl implements AccountDao {

	final static Logger log = Logger.getLogger(AccountDaoImpl.class);
	
	@Override
	public boolean checkName(String check) {
		try(Connection conn = ConnectionUtil.getConnection()){
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}
	
	@Override
	public double checkBalance(int id) {
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("Select* from bank_customers WHERE accountnum=?");	
			stmt.setInt(++index, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getDouble("balance");
			}
			
		
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}
		return 0;
	}

	@Override
	public synchronized boolean deposit(int accountnum, double amount) {
		log.info("Deposit made by " + accountnum);
		if(amount < 0) {
			System.out.println("Deposits must be Greater than $0");
			log.warn("Negative Amount in Deposit");
			return false;
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL deposit(?, ?)}");
			stmt.setInt(1, accountnum);
			stmt.setDouble(2, amount);
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info( amount + "Deposit made by" + accountnum);
			} else {
				log.error("Deposit Transaction did not complete");
			}
			return result > 0;
		} catch (SQLException e) {
			log.error("Deposit Transaction Error");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public synchronized boolean withdrawal(int accountnum, double amount) {
		log.info("Withdrawal attempted by user " + accountnum);
		if(amount < 0) {
			System.out.println("Withdrawal Amount must be greater than $0");
			log.warn("Negative Amount in Withdrawal");
			return false;
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL withdrawal(?, ?)}");
			stmt.setInt(1, accountnum);
			stmt.setDouble(2, amount);
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info( amount + "Withdrawal made by" + accountnum);
			} else {
				log.error("Withdrawal Transaction did not complete");
			}
			return result > 0;
		} catch (SQLException e) {
			log.error("Withdrawal Transaction Error");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public synchronized boolean transfer(int id1, int id2, double amount) {
		if(amount < 0) {
			System.out.println("Transfer Amount must be greater than $0");
			log.warn("Negative Amount in Transfer");
			return false;
		}
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL transfer(?, ?, ?)}");
			stmt.setInt(1, id1);
			stmt.setInt(2, id2);
			stmt.setDouble(3, amount);
			int result = stmt.executeUpdate();
			if(result>0) {
				log.info( amount + "Transferred from" + id1+ "TO"+id2);
			} else {
				log.error("Transfer Transaction did not complete");
			}
			return result > 0;
		} catch (SQLException e) {
			log.error("Transfer Transaction Error");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public boolean passwordChange(int accountnum, String new_pw) {
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL change_password(?, ?)}");
			stmt.setInt(1, accountnum);
			stmt.setString(2, new_pw);
			log.info("Password Change");
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info(accountnum + "changed password" );
			} else {
				log.error("Password Change did not complete");
			}
			return result > 0;
		} catch (SQLException e) {
			log.error("Password change error.");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public boolean firstNameChange(int accountnum, String new_f_name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL change_first_name(?, ?)}");
			stmt.setInt(1, accountnum);
			stmt.setString(2, new_f_name);
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info("User" + accountnum + " successfully changed their first name." );
			} else {
				log.error("First name Change failed");
			}
			return result > 0;
		} catch (SQLException e) {
			log.error("First name change failure");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public boolean lastNameChange(int id, String new_l_name) {
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL change_last_name(?, ?)}");
			stmt.setInt(1, id);
			stmt.setString(2, new_l_name);
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info("User" + id + " successfully changed their last name." );
			} else {
				log.error("Last name Change failed");
			}
			return result > 0;
		} catch (SQLException e) {
			log.error("Something went wrong changing their last name");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public boolean emailChange(int id, String new_un) {
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL change_email(?, ?)}");
			stmt.setInt(1, id);
			stmt.setString(2, new_un);
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info( id + " Updated Email Address" );
			} else {
				log.error("Email Change failed");
			}
			return result > 0;
		} catch (SQLException e) {			
			log.error("Email Change not completed");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public User getUserInfo(String email) {
		try(Connection conn = ConnectionUtil.getConnection()){
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bank_customers WHERE email = ?");
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			if(rs.next()) {
				return new User(rs.getInt("accountnum"), rs.getInt("balance"), rs.getString("firstname"), rs.getString("lastname"),
								rs.getString("email"), rs.getString("password"),rs.getInt("active"), rs.getInt("admin"));
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return null;
	}

	@Override
	public boolean closeAccount(int accountnum) {
		log.info(accountnum + "Has been closed");
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL close_account(?)}");
			stmt.setInt(1, accountnum);
			
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info("Account closed successful.");
			} else {
				log.error("Account closing failed.");
			}
			return result>0;
		} catch (SQLException e) {
			log.error("Error closing account.");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}		
		return false;
	}

	@Override
	public boolean openAccount(String firstname, String lastname, String email, String password,
			int active,int balance, int admin) {
		log.info("Opening new account.");
		int index = 0;
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL new_user(?, ?, ?, ?, ?, ?, ?)}");
			stmt.setString(++index, firstname);
			stmt.setString(++index, lastname);
			stmt.setString(++index, email);
			stmt.setString(++index, password);
			stmt.setInt(++index, active);
			stmt.setInt(++index, balance);
			stmt.setInt(++index, admin);

			return stmt.executeUpdate() > 0;
		} catch(SQLException e) {
			log.error("Error creating new account.");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}
		return false;
	}
	
	public boolean active(int accountnum) {
		log.info("Activating " + accountnum);
		try(Connection conn = ConnectionUtil.getConnection()){
			CallableStatement stmt = conn.prepareCall("{CALL active(?)}");
			stmt.setInt(1, accountnum);
			
			int result = stmt.executeUpdate();
			if(result > 0) {
				log.info("Activation Complete.");
			} else {
				log.error("Activation Failed");
			}
			return result>0;
		} catch (SQLException e) {
			log.error("Error Activating User");
			System.err.println(e.getMessage());
			System.err.println("SQL State: " + e.getSQLState());
			System.err.println("Error Code: " + e.getErrorCode());
		}
		return false;
	}
}