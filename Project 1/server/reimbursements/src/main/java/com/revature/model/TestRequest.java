package com.revature.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.revature.util.Mappable;

public class TestRequest implements Mappable {
    String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public TestRequest() {
    }

    @Override
    public String toString() {
        return "TestRequest{" +
                "msg='" + msg + '\'' +
                '}';
    }
}