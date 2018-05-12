package com.revature.ers;

import com.revature.dao.ReimbursementService;
import com.revature.reimbursement.Reimbursment;

public class application {
	public static void main(String[] args) {
		Reimbursment re = ReimbursementService.getReimbursmentById(41);
		System.out.println(re);
		boolean reb = ReimbursementService.approveReimbursment(2, 63);
		System.out.println(reb);
	}

}
