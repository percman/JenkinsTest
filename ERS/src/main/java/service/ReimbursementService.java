package service;

import java.util.List;

import org.apache.log4j.Logger;

import daoimpl.ReimbursementDaoImpl;
import model.Reimbursement;

public class ReimbursementService {
	private static Logger logger = Logger.getLogger(ReimbursementService.class);
	private static ReimbursementDaoImpl dao = ReimbursementDaoImpl.getInstance(logger);
	
	public static boolean createReimbursementEmployee(String inUsername, String inStatus, String inImage, String inCategory) {
		return dao.createReimbursementEmployee(inUsername, inStatus, inImage, inCategory);
	}
	public static boolean createReimbursementManager(String inUsername, String inStatus, String inImage, String inCategory) {
		return dao.createReimbursementManager(inUsername, inStatus, inImage, inCategory);
	}
	public static Reimbursement readReimbursement(int reimbursementid) {
		return dao.readReimbursement(reimbursementid);
	}
	public static List<Reimbursement> readReimbursements() {
		return dao.readReimbursements();
	}
	public static List<Reimbursement> readReimbursements(String inUsername){
		return dao.readReimbursements(inUsername);
	}
	public static boolean updateReimbursement(int reimbursementid, String inStatus, int managerid) {
		return dao.updateReimbursement(reimbursementid, inStatus, managerid);
	}
	public static boolean deleteReimbursement(String inUsername) {
		return dao.deleteReimbursement(inUsername);
	}
}
