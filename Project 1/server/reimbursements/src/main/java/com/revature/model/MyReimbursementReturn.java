package com.revature.model;

public class MyReimbursementReturn {
    private double amount;
    private int category;
    private int status;
    private String approverFirst;
    private String approverLast;

    public MyReimbursementReturn() {
    }

    public MyReimbursementReturn(double amount, int category, int status, String approverFirst, String approverLast) {
        this.amount = amount;
        this.category = category;
        this.status = status;
        this.approverFirst = approverFirst;
        this.approverLast = approverLast;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getApproverFirst() {
        return approverFirst;
    }

    public void setApproverFirst(String approverFirst) {
        this.approverFirst = approverFirst;
    }

    public String getApproverLast() {
        return approverLast;
    }

    public void setApproverLast(String approverLast) {
        this.approverLast = approverLast;
    }

    @Override
    public String toString() {
        return "MyReimbursementReturn{" +
                "amount=" + amount +
                ", category=" + category +
                ", status=" + status +
                ", approverFirst='" + approverFirst + '\'' +
                ", approverLast='" + approverLast + '\'' +
                '}';
    }
}