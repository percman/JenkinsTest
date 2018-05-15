package com.revature.factory;

import java.sql.Date;

public interface Reimbursement {

	public int getRequestor_id();
	public void setRequestor_id(int requestor_id);
	public int getApprover_id();
	public void setApprover_id(int approver_id);
	public float getAmount();
	public void setAmount(float amount);
	public String getRequestDate();
	public void setRequestDate(String requestDate);
	public String getApproveDate();
	public void setApproveDate(String approveDate);
	public int getCategory();
	public int getStatus();
	public void setStatus(int status);
	public int getReimbursement_id();
	public void setReimbursement_id(int reimbursement_id);
	public String getMessage();
	public void setMessage(String message);
}
