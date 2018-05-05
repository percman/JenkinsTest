package com.revature.reimbursement;

public class Reimbursement {
	int reimbursementId;
	int requesterId;
	int approverId;
	String category;
	String status;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Reimbursement(int reimbursementId, int requesterId, int approverId, String category, String status) {
		super();
		this.reimbursementId = reimbursementId;
		this.requesterId = requesterId;
		this.approverId = approverId;
		this.category = category;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", requesterId=" + requesterId + ", approverId="
				+ approverId + ", category=" + category + ", status=" + status + "]";
	}
}
