package dao;

import java.util.List;

import model.Reimbursement;

public interface ReimbursementDao {
	boolean createReimbursementEmployee(String inUsername, String inStatus, String inImage, String inCategory);
	boolean createReimbursementManager(String inUsername, String inStatus, String inImage, String inCategory);
	Reimbursement readReimbursement(int inReimbursementid);
	List<Reimbursement> readReimbursements();
	List<Reimbursement> readReimbursements(String inUsername);
	boolean updateReimbursement(int inReimbursementid, String inStatus, int inManagerid);
	boolean deleteReimbursement(String inUsername);
}
