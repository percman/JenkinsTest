package dao;

import model.Information;

public interface InformationDao {
	boolean createInformation(String inUsername, String inFirstname, String inMiddlename, String inLastname);
	Information readInformation(String inUsername);
	boolean updateInformation(String inUsername, String inFirstname, String inMiddlename, String inLastname);
	boolean deleteInformation(String inUsername);
}
