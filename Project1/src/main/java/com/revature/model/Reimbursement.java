package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable{

	private static final long serialVersionUID = 3082336176827941428L;

	private int requestorId;
	private int approverId;
	private String category;
	private String status;
	
	public Reimbursement() {};
	
	public Reimbursement(int requestor, String category) {
		super();
		this.requestorId=requestor;
		this.category=category;
		this.status="Pending";
	}
	
	public Reimbursement(int requestor, int approver, String category, String status) {
		super();
		this.requestorId = requestor;
		this.approverId = approver;
		this.category = category;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reimbursement [requestor=" + requestorId + ", approver=" + approverId + ", category=" + category
				+ ", status=" + status + "]";
	}
	
	public int getRequestorId() {
		return requestorId;
	}
	public void setRequestor(int requestor) {
		this.requestorId = requestor;
	}
	public int getApproverId() {
		return approverId;
	}
	public void setApproverId(int approver) {
		this.approverId = approver;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + approverId;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + requestorId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		if (approverId != other.approverId)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (requestorId != other.requestorId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
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
}
