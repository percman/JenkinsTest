package service;

import org.apache.log4j.Logger;

import daoimpl.ManagerDaoImpl;
import model.Manager;

public class ManagerService {
	private static Logger logger = Logger.getLogger(ManagerService.class);
	private static ManagerDaoImpl dao = ManagerDaoImpl.getInstance(logger);
	
	public static boolean createManager(String inUsername) {
		return dao.createManager(inUsername);
	}
	public static Manager readManager(String inUsername) {
		return dao.readManager(inUsername);
	}
	public static boolean deleteManager(String inUsername) {
		return dao.deleteManager(inUsername);
	}
}
