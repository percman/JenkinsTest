package com.revature.dao;

import com.revature.model.Reimbursement;

import java.util.List;

public interface ReimbursementDao {

    boolean insertReimbursement(Reimbursement newReimbursement);

    boolean approveReimbursement(int reimbursementId, int financialManagerId, String decisionComment);

    boolean denyReimbursement(int reimbursementId, int financialManagerId, String decisionComment);

    List<Reimbursement> getAllReimbursements();

    List<Reimbursement> getAllReimbursements(int employeeId);

    List<Reimbursement> getAllPending();

    List<Reimbursement> getAllPending(int employeeId);

    List<Reimbursement> getAllResolved();

    List<Reimbursement> getAllResolved(int employeeId);





}
