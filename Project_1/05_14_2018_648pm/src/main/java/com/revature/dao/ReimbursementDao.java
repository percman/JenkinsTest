package com.revature.dao;

import java.sql.Blob;
import java.util.List;

import com.revature.factory.Reimbursement;
import com.revature.image.Image;

public interface ReimbursementDao {

	boolean submitRequest(Reimbursement rb, String file);
	List<Reimbursement> getAllRequests();
	
	boolean approveReimbursement(int requestorId, int managerId, int reimbursementId);
	boolean denyReimbursement(int requestorId, int managerId, int reimbursementId);
	List<Reimbursement> getRequests(int id);
	boolean insertPhoto(int id, String file);
	Blob retrievePhoto(int id);
	List<Image> getAllImages();
}
