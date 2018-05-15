package com.revature.dao;

import java.util.List;

import com.revature.model.Info;


public interface InfoDao {

	public abstract List<Info> getAllInfo();
	public abstract Info getInfo(int employeeId);
	public abstract boolean insertInfo(Info info);
	public abstract boolean updateInfo(Info info);
	
}
