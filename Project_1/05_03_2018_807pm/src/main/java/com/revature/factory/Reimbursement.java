package com.revature.factory;

import java.sql.Date;

public interface Reimbursement {

	public int getRequestor_id();
	public void setRequestor_id(int requestor_id);
	public int getApprover_id();
	public void setApprover_id(int approver_id);
	public float getAmount();
	public void setAmount(float amount);
	public Date getRequestDate();
	public void setRequestDate(Date requestDate);
	public Date getApproveDate();
	public void setApproveDate(Date approveDate);
	public int getCategory();
	public int getStatus();
	public void setStatus(int status);
	public int getReimbursement_id();
	public void setReimbursement_id(int reimbursement_id);
}
