package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable{

	private static final long serialVersionUID = 3082336176827941428L;

	private Employee requestor;
	private Manager approver;
	private String category;
	private String status;
	
	public Reimbursement() {};
	
	public Reimbursement(Employee requestor, Manager approver, String category, String status) {
		super();
		this.requestor = requestor;
		this.approver = approver;
		this.category = category;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reimbursement [requestor=" + requestor + ", approver=" + approver + ", category=" + category
				+ ", status=" + status + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approver == null) ? 0 : approver.hashCode());
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((requestor == null) ? 0 : requestor.hashCode());
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
		if (approver == null) {
			if (other.approver != null)
				return false;
		} else if (!approver.equals(other.approver))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (requestor == null) {
			if (other.requestor != null)
				return false;
		} else if (!requestor.equals(other.requestor))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	public Employee getRequestor() {
		return requestor;
	}
	public void setRequestor(Employee requestor) {
		this.requestor = requestor;
	}
	public Manager getApprover() {
		return approver;
	}
	public void setApprover(Manager approver) {
		this.approver = approver;
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
