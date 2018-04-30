package model;

public class User extends Person{
	Administrator approve;
	Administrator reject;
	
	public Administrator getApprove() {
		return approve;
	}
	public void setApprove(Administrator approve) {
		this.approve = approve;
	}
	public Administrator getReject() {
		return reject;
	}
	public void setReject(Administrator reject) {
		this.reject = reject;
	}
	
}
