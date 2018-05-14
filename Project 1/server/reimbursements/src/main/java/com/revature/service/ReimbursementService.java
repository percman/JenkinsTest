package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dao.ReimbursementDaoImpl;
import com.revature.model.CreateReimbursementModel;
import com.revature.model.MyReimbursementReturn;

import java.util.List;

public class ReimbursementService {
    private static ReimbursementDao dao = ReimbursementDaoImpl.getInstance();
    public static boolean createReimbursement (CreateReimbursementModel cm){
        return dao.createReimbursement(cm);
    }
    public static List<MyReimbursementReturn> getReimbursementsByUser(String username) {
        return dao.getRequestedReimbursementsByUser(username);
    }
}