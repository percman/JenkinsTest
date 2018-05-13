package com.revature.model;

import java.io.Serializable;

public class Reimbursement implements Serializable{

	private static final long serialVersionUID = 3082336176827941428L;

	private int id;
	private double amount;
	private int requestorId;
	private String requestTime;
	private int approverId;
	private String approvedTime;
	private String category;
	private String status;
	
	public Reimbursement() {};
	
	public Reimbursement(int requestor, String category, Double amount){
			this.requestorId=requestor;
			this.category=category;
			this.amount=amount;
	}
	
	public Reimbursement(int id, int requestor, String category, double amount, String status, String requestTime) {
		super();
		this.id=id;
		this.requestorId=requestor;
		this.category=category;
		this.status=status;
		this.amount=amount;
		this.requestTime=requestTime;
	}
	
	public Reimbursement(int id, int requestor, int approver, double amount, String category, String status,
			String requestTime, String approvedTime) {
		super();
		this.id=id;
		this.requestorId = requestor;
		this.approverId = approver;
		this.amount=amount;
		this.category = category;
		this.status = status;
		this.requestTime=requestTime;
		this.approvedTime=approvedTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getRequestorId() {
		return requestorId;
	}

	public void setRequestorId(int requestorId) {
		this.requestorId = requestorId;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String request_time) {
		this.requestTime = request_time;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public String getApprovedTime() {
		return approvedTime;
	}

	public void setApprovedTime(String approvedTime) {
		this.approvedTime = approvedTime;
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
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((approvedTime == null) ? 0 : approvedTime.hashCode());
		result = prime * result + approverId;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + id;
		result = prime * result + ((requestTime == null) ? 0 : requestTime.hashCode());
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
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (approvedTime == null) {
			if (other.approvedTime != null)
				return false;
		} else if (!approvedTime.equals(other.approvedTime))
			return false;
		if (approverId != other.approverId)
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id != other.id)
			return false;
		if (requestTime == null) {
			if (other.requestTime != null)
				return false;
		} else if (!requestTime.equals(other.requestTime))
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

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", requestorId=" + requestorId + ", request_time="
				+ requestTime + ", approverId=" + approverId + ", approvedTime=" + approvedTime + ", category="
				+ category + ", status=" + status + "]";
	}
	
}
