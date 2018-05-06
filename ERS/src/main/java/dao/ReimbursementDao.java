package dao;

import model.Reimbursement;

public interface ReimbursementDao {
	boolean createReimbursement(int inEmployeeId, String inStatus, String inImage, String inCategory);
	Reimbursement readReimbursement(int inReimbursementId);
	boolean updateReimbursement(int inReimbursementId, int inEmployeeId, String inStatus, String inImage, String inCategory);
	boolean deleteReimbursement(int inReimbursementId);
}
