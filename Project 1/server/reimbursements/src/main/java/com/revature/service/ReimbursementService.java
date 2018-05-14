package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.CreateReimbursementModel;

public class ReimbursementService {
    private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
    public static boolean createReimbursement (CreateReimbursementModel cm){
        return dao.createReimbursement(cm);
    }
}