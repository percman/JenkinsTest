package service;

import org.apache.log4j.Logger;

import daoimpl.InformationDaoImpl;
import model.Information;

public class InformationService {
	private static Logger logger = Logger.getLogger(EmployeeService.class);
	private static InformationDaoImpl dao = InformationDaoImpl.getInstance(logger);
	
	boolean createInformation(String inUsername, String inFirstname, 
			String inMiddlename, String inLastname) {
		return dao.createInformation(inUsername, inFirstname, inMiddlename, inLastname);
	}
	public static Information readInformation(String inUsername) {
		return dao.readInformation(inUsername);
	}
	public static boolean updateInformation(String inUsername, String inFirstname, 
			String inMiddlename, String inLastname) {
		return dao.updateInformation(inUsername, inFirstname, inMiddlename, inLastname);
	}
	public static boolean deleteInformation(String inUsername) {
		return dao.deleteInformation(inUsername);
	}
}
