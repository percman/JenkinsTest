package com.revature.dao;

import com.revature.model.ReimbursementTable;

public interface ReimbursementDao {
    ReimbursementTable getAllReimbursements();
    ReimbursementTable getRequestedReimbursementsByUser(int id);


}
