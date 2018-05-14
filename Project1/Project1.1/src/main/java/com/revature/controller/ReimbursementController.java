package com.revature.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReimbursementController {

    private ReimbursementController(){}

    public static String myReimbursement(HttpServletRequest request, HttpServletResponse response){
        return "/MyReimbursement.jsp";
    }

    public static String submitReimbursement(HttpServletRequest request, HttpServletResponse response){

        return "/MyReimbursement.jsp";
    }



}
