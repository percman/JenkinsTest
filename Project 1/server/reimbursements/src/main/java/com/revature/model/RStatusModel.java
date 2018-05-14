package com.revature.model;

import com.revature.util.Mappable;

public class RStatusModel implements Mappable {
    private int status;
    private int rid;
    private int approverId;

    public RStatusModel() {
    }

    public RStatusModel(int status, int rid, int approverId) {
        this.status = status;
        this.rid = rid;
        this.approverId = approverId;
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

    public int getApproverId() {
        return approverId;
    }

    public void setApproverId(int approverId) {
        this.approverId = approverId;
    }

    @Override
    public String toString() {
        return "RStatusModel{" +
                "status=" + status +
                ", rid=" + rid +
                ", approverId=" + approverId +
                '}';
    }
}