package dao;

import java.util.List;

import model.Reimbursement;

public interface ReimbursementDao {
	boolean createReimbursement(String inUsername, String inStatus, String inImage, String inCategory);
	Reimbursement readReimbursement(String inUsername);
	List<Reimbursement> readReimbursements();
	boolean updateReimbursement(String inUsername, String inStatus, String inImage, String inCategory);
	boolean deleteReimbursement(String inUsername);
}
