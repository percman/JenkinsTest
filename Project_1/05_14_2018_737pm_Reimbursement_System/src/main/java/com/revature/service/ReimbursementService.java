package com.revature.service;

import java.sql.Blob;
import java.util.List;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImplementation;
import com.revature.factory.Reimbursement;
import com.revature.image.Image;

public class ReimbursementService {

	private static ReimbursementDao dao = new ReimbursementDaoImplementation();

	private ReimbursementService() {
	}

	public static boolean submitRequest(Reimbursement rb, String file) {
		return dao.submitRequest(rb, file);
	}
	
	public static List<Reimbursement> getRequests(int id) {
		return dao.getRequests(id);
	}
	
	public static List<Reimbursement> getAllRequests() {
		return dao.getAllRequests();
	}
	
	public static boolean approveReimbursement(int requestorId, int managerId, int reimbursementId) {
		return dao.approveReimbursement(requestorId, managerId, reimbursementId);
	}
	
	public static boolean denyReimbursement(int requestorId, int managerId, int reimbursementId) {
		return dao.denyReimbursement(requestorId, managerId, reimbursementId);
	}
	
	public static boolean insertPhoto(int id, String file) {
		return dao.insertPhoto(id, file);
	}
	
	public static Blob retrievePhoto(int id) {
		return dao.retrievePhoto(id);
	}
	
	public static List<Image> getAllImages(){
		return dao.getAllImages();
	}
}