package com.revature.model;

import java.sql.Timestamp;

public class FoodReimbursement extends Reimbursement{



	private static final long serialVersionUID = 4645325895230137268L;
	
	private String food;

	public FoodReimbursement() {}
	
	public FoodReimbursement(int id, double amount, int requestor_id, boolean status, String food) {
		super(id, amount, requestor_id, status);
		this.food = food;
	}
	
	public FoodReimbursement(int id, double amount, int requestor_id, int approver_id, boolean status,
			Timestamp timemade, Timestamp timeapproved, String reason, String food) {
		super(id, amount, requestor_id, approver_id, status, timemade, timeapproved, reason);
		this.food = food;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((food == null) ? 0 : food.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		FoodReimbursement other = (FoodReimbursement) obj;
		if (food == null) {
			if (other.food != null)
				return false;
		} else if (!food.equals(other.food))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "FoodReimbursement [food=" + food + ", id=" + id + ", amount=" + amount + ", requestor_id="
				+ requestor_id + ", approver_id=" + approver_id + ", status=" + status + ", timemade=" + timemade
				+ ", timeapproved=" + timeapproved + ", reason=" + reason + "]";
	}
	
	
	
}
