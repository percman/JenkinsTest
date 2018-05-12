package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable {

	private static final long serialVersionUID = 3082336176827941428L;

	// These are in the reimbursement table
	private int id;
	private int requestorId;
	private String requestorName;
	private int approverId;
	private String approverName;
	private int categoryId;
	private String category;
	private int statusId;
	private String status;
	private int amount;
	private String submitted;
	private String approved;
	
	// Public no-arg constructor
	public Reimbursement() {
		super();
	}
	
	// Public constructor that takes the requestor's id, category, and amount
	public Reimbursement(int requestorId, int categoryId, int amount) {
		super();
		this.requestorId = requestorId;
		this.categoryId = categoryId;
		this.amount = amount;
	}

	// Public constructor that takes fields to input into the database
	public Reimbursement(int id, int requestorId, int approverId, int categoryId, int statusId, int amount,
			String submitted, String approved) {
		super();
		this.id = id;
		this.requestorId = requestorId;
		this.approverId = approverId;
		this.categoryId = categoryId;
		this.statusId = statusId;
		this.amount = amount;
		this.submitted = submitted;
		this.approved = approved;
	}

	// This is the constructor used to display the info
	public Reimbursement(int id, String requestorName, String approverName, String category, String status, int amount,
			String submitted, String approved) {
		super();
		this.id = id;
		this.requestorName = requestorName;
		this.approverName = approverName;
		this.category = category;
		this.status = status;
		this.amount = amount;
		this.submitted = submitted;
		this.approved = approved;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(int requestorId) {
		this.requestorId = requestorId;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getRequestorName() {
		return requestorName;
	}

	public void setRequestorName(String requestorName) {
		this.requestorName = requestorName;
	}

	public String getApproverName() {
		return approverName;
	}

	public void setApproverName(String approverName) {
		this.approverName = approverName;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + ((approved == null) ? 0 : approved.hashCode());
		result = prime * result + approverId;
		result = prime * result + categoryId;
		result = prime * result + id;
		result = prime * result + requestorId;
		result = prime * result + statusId;
		result = prime * result + ((submitted == null) ? 0 : submitted.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (approved == null) {
			if (other.approved != null)
				return false;
		} else if (!approved.equals(other.approved))
			return false;
		if (approverId != other.approverId)
			return false;
		if (categoryId != other.categoryId)
			return false;
		if (id != other.id)
			return false;
		if (requestorId != other.requestorId)
			return false;
		if (statusId != other.statusId)
			return false;
		if (submitted == null) {
			if (other.submitted != null)
				return false;
		} else if (!submitted.equals(other.submitted))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", requestorId=" + requestorId + ", approverId=" + approverId
				+ ", categoryId=" + categoryId + ", statusId=" + statusId + ", amount=" + amount + ", submitted="
				+ submitted + ", approved=" + approved + "]";
	}
	
	
	
}
