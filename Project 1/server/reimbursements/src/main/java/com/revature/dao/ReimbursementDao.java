package com.revature.dao;

import com.revature.model.CreateReimbursementModel;
import com.revature.model.ReimbursementTable;

public interface ReimbursementDao {
    ReimbursementTable getAllReimbursements();
    ReimbursementTable getRequestedReimbursementsByUser(int id);
    boolean createReimbursement(CreateReimbursementModel cm);

}
