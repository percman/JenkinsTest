package com.revature.dao;

import com.revature.model.Applying;

public interface ApplyingDao {

	Applying getApplying(String username);
	boolean insertApplying(Applying applying);
	boolean deleteApplying(String username);
	String getPasswordHash(Applying applying);

}
