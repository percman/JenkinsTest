package com.revature.zero.Project_Zero;

public class TransactionFactory {

	public Transaction createTransaction(String newTrans) {
		
		Transaction t = null;
		
		if (newTrans.equals("Withdraw")) {
			t = new TransactionWithdraw();
		}
		if (newTrans.equals("Deposit")) {
			t = new TransactionDeposit();
		}
		
		return t;
	}
}
