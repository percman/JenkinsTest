package model;

public class User extends Person{
	private Administrator approve;
	private Administrator reject;
	private int balance;
	private boolean lock;
	User(String name, String password, int i, boolean b) {
		super();
		super.setName(name);
		super.setPassword(password);
		this.approve = null;
		this.reject = null;
		this.lock = b;
	}
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
	public boolean isLocked() {
		return lock;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int newBalance) {
		this.balance = newBalance;
	}
	public void setLocked(boolean b) {
		this.lock = b;
	}
}
