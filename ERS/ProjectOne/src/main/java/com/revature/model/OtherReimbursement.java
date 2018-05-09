package com.revature.model;

import java.sql.Timestamp;

public class OtherReimbursement extends Reimbursement{
	
	private static final long serialVersionUID = 3654034208924296153L;
	
	private String type; 
	private String info;
	
	public OtherReimbursement() {}
	
	public OtherReimbursement(int id, double amount, int requestor_id, boolean status, String type, String info) {
		super(id, amount, requestor_id, status);
		this.type = type;
		this.info = info;
	}
	
	public OtherReimbursement(int id, double amount, int requestor_id, int approver_id, boolean status,
			Timestamp timemade, Timestamp timeapproved, String reason, String type, String info) {
		super(id, amount, requestor_id, approver_id, status, timemade, timeapproved, reason);
		this.type = type;
		this.info = info;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		OtherReimbursement other = (OtherReimbursement) obj;
		if (info == null) {
			if (other.info != null)
				return false;
		} else if (!info.equals(other.info))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OtherReimbursement [type=" + type + ", info=" + info + "]";
	}
	
	
	
}
