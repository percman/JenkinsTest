package dao;

import model.Manager;

public interface ManagerDao {
	boolean createManager(String inUsername, String inPassword);
	Manager readManager(String inUsername);
	boolean deleteManager(String inUsername);
	boolean authenticateManager(String inUsername, String inPassword);
}
