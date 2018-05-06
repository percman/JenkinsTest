package dao;

import model.Manager;

public interface ManagerDao {
	boolean createManager(int inEmployeeId);
	Manager readManager(int inManagerId);
	//boolean updateManager();
	boolean deleteManager(int inEmployeeId);
}
