package com.fsfb.branchregister.branchregister.model;

public class CommonResponse {

    boolean status;
    String msg;
    Object data;
    String reqId;

    public CommonResponse(boolean status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    public CommonResponse(boolean status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    public CommonResponse(boolean status, String msg, Object data, String reqId) {
        this.status = status;
        this.msg = msg;
        this.reqId = reqId;
        this.data = data;
    }

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CommonResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                ", reqId='" + reqId + '\'' +
                '}';
    }
}
