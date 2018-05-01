package com.revature.service;

import com.revature.dao.CustLoginDao;
import com.revature.dao.CustLoginDaoImpl;
import com.revature.model.CustLogin;

public class CustLoginService {

	private static CustLoginDao dao = CustLoginDaoImpl.getInstance();
	private CustLoginService() {};
	
	public static CustLogin getUser(String username) {
		return (CustLogin) dao.getUserName(username);
	}
	
	public static CustLogin login(CustLogin user) {
		CustLogin temp = (CustLogin) dao.getUserName(user.getUsername());
		
		System.out.println("dao " + dao );
		System.out.println("user.getusername " + user.getUsername());
		System.out.println("user.getpassowrd " +  user.getPassowrd());
		System.out.println("temp " + temp.getPassowrd());
		
	/*	if(temp.getPassowrd().equals(dao.getPasswordHash(user))) {
			System.out.println("you are a valid user, " + temp.getUsername());
			return temp;
		}
		
		System.out.println("you are not a valid user, " + user.getUsername());*/
		return null;
	}
	
}

