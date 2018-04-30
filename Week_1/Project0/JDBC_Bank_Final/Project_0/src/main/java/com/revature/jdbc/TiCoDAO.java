package com.revature.jdbc;

public interface TiCoDAO {

	boolean generateTimestamp(int id);
	int getBalance(int id);
	int getTotalBalance();
}
