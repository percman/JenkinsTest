package com.revature.reimbursement;

public class Reimbursement {
	int reimbursementId;
	int requesterId;
	int approverId;
	String category;
	int status;
	int amount;
	String dateSubmitted;
	String dateCompleted;
	
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", requesterId=" + requesterId + ", approverId="
				+ approverId + ", category=" + category + ", status=" + status + ", amount=" + amount
				+ ", dateSubmitted=" + dateSubmitted + ", dateCompleted=" + dateCompleted + "]";
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getDateCompleted() {
		return dateCompleted;
	}

	public void setDateCompleted(String dateCompleted) {
		this.dateCompleted = dateCompleted;
	}

	public Reimbursement(int reimbursementId, int requesterId, 
			String category, int status, int amount, String dateSubmitted) {
		super();
		this.reimbursementId = reimbursementId;
		this.requesterId = requesterId;
		this.category = category;
		this.status = status;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
	}

	public Reimbursement(int requesterId, String category, int status, int amount) {
		super();
		this.requesterId = requesterId;
		this.category = category;
		this.status = status;
		this.amount = amount;
	}
	
}
