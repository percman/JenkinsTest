package com.revature.model;

import com.revature.util.Mappable;

public class ReimbursementTable implements Mappable {
    private double amount;
    private int category;
    private int status;
    private String approverFirst;
    private String approverLast;
    private String requesterFirst;
    private String requesterLast;
    private int rid;

    public ReimbursementTable(double amount, int category, int status, String approverFirst, String approverLast, String requesterFirst, String requesterLast, int rid) {
        this.amount = amount;
        this.category = category;
        this.status = status;
        this.approverFirst = approverFirst;
        this.approverLast = approverLast;
        this.requesterFirst = requesterFirst;
        this.requesterLast = requesterLast;
        this.rid = rid;
    }

    public ReimbursementTable() {
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

    public String getRequesterFirst() {
        return requesterFirst;
    }

    public void setRequesterFirst(String requesterFirst) {
        this.requesterFirst = requesterFirst;
    }

    public String getRequesterLast() {
        return requesterLast;
    }

    public void setRequesterLast(String requesterLast) {
        this.requesterLast = requesterLast;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }
}