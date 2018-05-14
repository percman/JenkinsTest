package com.revature.reimbursement;

import java.io.InputStream;
import java.sql.Blob;

import com.revature.dao.EmployeeService;

public class Reimbursement {
	int reimbursementId;
	int requesterId;
	int approverId;
	String category;
	int status;
	int amount;
	String dateSubmitted;
	String dateCompleted;
	String requesterFirstName;
	String requesterLastName;
	String approverFirstName;
	String approverLastName;
	private InputStream img;

	public Reimbursement(int reimbursementId, int requesterId, int approverId, String category, int status, int amount,
			String dateSubmitted, String dateCompleted, String requesterFirstName, String requesterLastName,
			String approverFirstName, String approverLastName, InputStream img) throws ClassNotFoundException {
		super();
		this.reimbursementId = reimbursementId;
		this.requesterId = requesterId;
		this.approverId = approverId;
		this.category = category;
		this.status = status;
		this.amount = amount;
		this.dateSubmitted = dateSubmitted;
		this.dateCompleted = dateCompleted;
		this.requesterFirstName = requesterFirstName;
		this.requesterLastName = requesterLastName;
		if (approverId != 0) {
			this.approverFirstName = EmployeeService.getEmployee(approverId).getFirstName();
			this.approverLastName = EmployeeService.getEmployee(approverId).getLastName();
		}

		this.img = img;
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

	public String getRequesterFirstName() {
		return requesterFirstName;
	}

	public void setRequesterFirstName(String requesterFirstName) {
		this.requesterFirstName = requesterFirstName;
	}

	public String getRequesterLastName() {
		return requesterLastName;
	}

	public void setRequesterLastName(String requesterLastName) {
		this.requesterLastName = requesterLastName;
	}

	public String getApproverFirstName() {
		return approverFirstName;
	}

	public void setApproverFirstName(String approverFirstName) {
		this.approverFirstName = approverFirstName;
	}

	public String getApproverLastName() {
		return approverLastName;
	}

	public void setApproverLastName(String approverLastName) {
		this.approverLastName = approverLastName;
	}

	public InputStream getImg() {
		return img;
	}

	public void setImg(InputStream img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbursementId=" + reimbursementId + ", requesterId=" + requesterId + ", approverId="
				+ approverId + ", category=" + category + ", status=" + status + ", amount=" + amount
				+ ", dateSubmitted=" + dateSubmitted + ", dateCompleted=" + dateCompleted + ", requesterFirstName="
				+ requesterFirstName + ", requesterLastName=" + requesterLastName + ", approverFirstName="
				+ approverFirstName + ", approverLastName=" + approverLastName + ", img=" + img + "]";
	}

}
