package com.revature.reimbursement;

public class ReimbursementFactory {

	public ReimbursementSuper reimbursementSelect(String t) {
		
		ReimbursementSuper r = null;
		
		if(t.equals("Travel")) {
			return new ReimbursementTravel();
		}
		if(t.equals("Food")) {
			return new ReimbursementFood();
		}
		if(t.equals("Lodging")) {
			return new ReimbursementLodging();
		}
		if(t.equals("Other")) {
			return new ReimbursementOther();
		}
		return r;
	}
}
