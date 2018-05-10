package dao;

import model.Manager;

public interface ManagerDao {
	boolean createManager(String inUsername);
	Manager readManager(String inUsername);
	boolean deleteManager(String inUsername);
}
