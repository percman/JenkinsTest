package com.revature.service;

import java.util.List;

import com.revature.dao.TransactionDao;
import com.revature.dao.TransactionDaoImpl;
import com.revature.model.User;

public class TransactionService {

	
	
	private static TransactionDao dao = TransactionDaoImpl.getInstance();
	private TransactionService() {}
	
	public static List<String> transactions(User user){
		return dao.transactions(user);		
	}
}
