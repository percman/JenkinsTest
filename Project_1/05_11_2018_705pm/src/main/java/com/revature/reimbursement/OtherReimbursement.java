package com.revature.reimbursement;

import java.sql.Date;

import com.revature.factory.Reimbursement;

public class OtherReimbursement implements Reimbursement{

	private int reimbursement_id;
	private int requestor_id;
	private int approver_id;
	private float amount;
	private int status;
	private String requestDate;
	private String approveDate;
	private String message;
	
	private int category = 3;
	
	public OtherReimbursement() {}
	
	public OtherReimbursement(int requestor_id, int approver_id, int category, float amount, String requestDate,
			String approveDate) {
		super();
		this.requestor_id = requestor_id;
		this.approver_id = approver_id;
		this.amount = amount;
		this.requestDate = requestDate;
		this.approveDate = approveDate;
	}
	
	public int getCategory() {
		return category;
	}
	
	public int getRequestor_id() {
		return requestor_id;
	}
	public void setRequestor_id(int requestor_id) {
		this.requestor_id = requestor_id;
	}
	public int getApprover_id() {
		return approver_id;
	}
	public void setApprover_id(int approver_id) {
		this.approver_id = approver_id;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(String requestDate) {
		this.requestDate = requestDate;
	}
	public String getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getReimbursement_id() {
		return reimbursement_id;
	}
	public void setReimbursement_id(int reimbursement_id) {
		this.reimbursement_id = reimbursement_id;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(amount);
		result = prime * result + ((approveDate == null) ? 0 : approveDate.hashCode());
		result = prime * result + approver_id;
		result = prime * result + category;
		result = prime * result + reimbursement_id;
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
		result = prime * result + requestor_id;
		result = prime * result + status;
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
		OtherReimbursement other = (OtherReimbursement) obj;
		if (Float.floatToIntBits(amount) != Float.floatToIntBits(other.amount))
			return false;
		if (approveDate == null) {
			if (other.approveDate != null)
				return false;
		} else if (!approveDate.equals(other.approveDate))
			return false;
		if (approver_id != other.approver_id)
			return false;
		if (category != other.category)
			return false;
		if (reimbursement_id != other.reimbursement_id)
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
			return false;
		if (requestor_id != other.requestor_id)
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		if (status == 1) {
			return "Other Reimbursement \n\tReimbursement_id = " + reimbursement_id + "\n\tRequestor_id = " + requestor_id
					+ "\n\tApprover_id = " + approver_id + "\n\tAmount = " + amount + "\n\tRequestDate = " + requestDate
					+ "\n\tApproveDate = " + approveDate + "\n\tStatus: APPROVED";
		} else if (status == 0) {
			return "Other Reimbursement \n\tReimbursement_id = " + reimbursement_id + "\n\tRequestor_id = " + requestor_id
					+ "\n\tApprover_id = " + approver_id + "\n\tAmount = " + amount + "\n\tRequestDate = " + requestDate
					+ "\n\tApproveDate = " + approveDate + "\n\tStatus: PENDING";
		} else {
			return "Other Reimbursement \n\tReimbursement_id = " + reimbursement_id + "\n\tRequestor_id = " + requestor_id
					+ "\n\tApprover_id = " + approver_id + "\n\tAmount = " + amount + "\n\tRequestDate = " + requestDate
					+ "\n\tApproveDate = " + approveDate + "\n\tStatus: DENIED";
		}

	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
