package dao;

import model.Information;

public interface InformationDao {
	boolean createInformation(int inEmployeeId, String inFirstname, String inMiddlename, String inLastname);
	Information readInformation(int inInformationId);
	boolean updateInformation(int inInformationId, int inEmployeeId, String inFirstname, String inMiddlename, String inLastname);
	boolean deleteInformation(int inInformationId);
}
