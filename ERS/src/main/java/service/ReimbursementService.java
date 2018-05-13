package service;

import java.util.List;

import org.apache.log4j.Logger;

import daoimpl.ReimbursementDaoImpl;
import model.Reimbursement;

public class ReimbursementService {
	private static Logger logger = Logger.getLogger(ReimbursementService.class);
	private static ReimbursementDaoImpl dao = ReimbursementDaoImpl.getInstance(logger);
	
	public static boolean createReimbursement(String inUsername, String inStatus, String inImage, String inCategory) {
		return dao.createReimbursement(inUsername, inStatus, inImage, inCategory);
	}
	public static Reimbursement readReimbursement(String inUsername) {
		return dao.readReimbursement(inUsername);
	}
	public static List<Reimbursement> readReimbursements() {
		return dao.readReimbursements();
	}
	public static boolean updateReimbursement(String inUsername, String inStatus, String inImage, String inCategory) {
		return dao.updateReimbursement(inUsername, inStatus, inImage, inCategory);
	}
	public static boolean deleteReimbursement(String inUsername) {
		return dao.deleteReimbursement(inUsername);
	}
}
