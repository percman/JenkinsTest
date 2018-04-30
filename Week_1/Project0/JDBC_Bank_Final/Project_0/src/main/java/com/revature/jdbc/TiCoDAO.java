package com.revature.jdbc;

/**
 * Methods for communicating with the SQL database
 * @author Jesse
 *
 */

public interface TiCoDAO {

	boolean generateTimestamp(int id);
	int getBalance(int id);
	int getTotalBalance();
}
