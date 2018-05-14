package com.revature.dao;

import com.revature.model.CreateReimbursementModel;
import com.revature.model.MyReimbursementReturn;
import com.revature.model.ReimbursementTable;

import java.util.List;

public interface ReimbursementDao {
    List<ReimbursementTable> getAllReimbursements();
    List<MyReimbursementReturn> getRequestedReimbursementsByUser(String username);
    boolean createReimbursement(CreateReimbursementModel cm);

}
