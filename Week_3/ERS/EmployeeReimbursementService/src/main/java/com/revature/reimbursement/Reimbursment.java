package com.revature.reimbursement;





public class Reimbursment {
private Category cat;
private int approverId,sumbitterId,reimburseId;
private boolean approved;



public Reimbursment() {
	
}

public Reimbursment(Category cat, int approverId, int sumbitterId, int reimburseId, int approved) {
	super();
	
	this.cat = cat;
	this.approverId = approverId;
	this.sumbitterId = sumbitterId;
	this.reimburseId = reimburseId;
	this.approved = approved == 1;
}

public Reimbursment(Category cat, int reimburseId, int approved) {
	super();
	
	this.cat = cat;
	this.reimburseId = reimburseId;
	this.approved = approved == 1;
}

public Category getCat() {
	return cat;
}
public void setCat(Category cat) {
	this.cat = cat;
}
public int getApproverId() {
	return approverId;
}
public void setApproverId(int approverId) {
	this.approverId = approverId;
}
public int getSumbitterId() {
	return sumbitterId;
}
public void setSumbitterId(int sumbitterId) {
	this.sumbitterId = sumbitterId;
}
public int getReimburseId() {
	return reimburseId;
}
public void setReimburseId(int reimburseId) {
	this.reimburseId = reimburseId;
}
public boolean isApproved() {
	return approved;
}
public void setApproved(boolean approved) {
	this.approved = approved;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + (approved ? 1231 : 1237);
	result = prime * result + approverId;
	result = prime * result + ((cat == null) ? 0 : cat.hashCode());
	result = prime * result + reimburseId;
	result = prime * result + sumbitterId;
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
	Reimbursment other = (Reimbursment) obj;
	if (approved != other.approved)
		return false;
	if (approverId != other.approverId)
		return false;
	if (cat != other.cat)
		return false;
	if (reimburseId != other.reimburseId)
		return false;
	if (sumbitterId != other.sumbitterId)
		return false;
	return true;
}
}
