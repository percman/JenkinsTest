package com.revature.model;

import com.revature.util.Mappable;

public class RStatusModel implements Mappable {
    private int status;
    private int rid;
    private int approver;

    public RStatusModel() {
    }

    public RStatusModel(int status, int rid, int approverId) {
        this.status = status;
        this.rid = rid;
        this.approver = approverId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getApprover() {
        return approver;
    }

    public void getApprover(int approver) {
        this.approver = approver;
    }

    @Override
    public String toString() {
        return "RStatusModel{" +
                "status=" + status +
                ", rid=" + rid +
                ", approverId=" + approver+
                '}';
    }
}