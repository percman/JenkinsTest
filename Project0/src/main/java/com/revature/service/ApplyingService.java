package com.revature.service;

import com.revature.dao.ApplyingDao;
import com.revature.dao.ApplyingDaoImpl;
import com.revature.model.Applying;

public class ApplyingService {

	private static ApplyingDao dao = ApplyingDaoImpl.getInstance();
	
	private ApplyingService() {}
	
	public static Applying getApplying(String username) {
		return dao.getApplying(username);
	}
	
	public static boolean insertApplying(Applying applying) {
		return dao.insertApplying(applying);
	}
	
}
