package com.fsfb.branchregister.branchregister.model.req;

import com.fsfb.branchregister.branchregister.model.RequestDetail;
import com.fsfb.branchregister.branchregister.model.RequestException;
import com.fsfb.branchregister.branchregister.model.RequestHistory;

import java.util.List;

public class RequestDetailRes {

    RequestDetail requestDetail;
    List<RequestException> requestExceptionList;
    List<RequestHistory> requestHistoryList;

    public RequestDetailRes() {
    }

    public RequestDetailRes(RequestDetail requestDetail, List<RequestException> requestExceptionList, List<RequestHistory> requestHistoryList) {
        this.requestDetail = requestDetail;
        this.requestExceptionList = requestExceptionList;
        this.requestHistoryList = requestHistoryList;
    }

    public RequestDetail getRequestDetail() {
        return requestDetail;
    }

    public void setRequestDetail(RequestDetail requestDetail) {
        this.requestDetail = requestDetail;
    }

    @Override
    public String toString() {
        return "RequestDetailRes{" +
                "requestDetail=" + requestDetail +
                ", requestExceptionList=" + requestExceptionList +
                ", requestHistoryList=" + requestHistoryList +
                '}';
    }
}
