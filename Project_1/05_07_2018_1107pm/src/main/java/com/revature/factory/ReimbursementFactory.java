package com.revature.factory;

import com.revature.reimbursement.FoodReimbursement;
import com.revature.reimbursement.LodgingReimbursement;
import com.revature.reimbursement.OtherReimbursement;
import com.revature.reimbursement.TravelReimbursement;

public class ReimbursementFactory {
	
	public static Reimbursement getReimbursement(int category) {
		
		switch (category) {
		case 1: return new FoodReimbursement();
		case 2: return new LodgingReimbursement();
		case 3: return new OtherReimbursement();
		case 4: return new TravelReimbursement();
		default: System.out.println("Reimbursement category not found");
		return null;
		}
	}
}
