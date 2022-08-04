package com.fsfb.branchregister.branchregister.model.req;

import com.fsfb.branchregister.branchregister.model.RequestStatus;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

public class RequestCommonReq {

    Long reqId;
    Long branchId;
    String branchName;
    Long keysetId;
    Long reqOwnerId;
    String ownerReason;
    Long reqByid;
    String reqByName;
    String ownerComment;
    Long recieverId;
    String recieverComment;
    Long bomId;
    String bomComment;
    Long zhUserId;
    String zhUserComment;
    String exceptionMsg;
    String requestStatus;
    List<Long> branchlist;
    String reqFromDate;
    String reqToDate;


    public RequestCommonReq() {
    }

    //For new req add
    public RequestCommonReq(Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, String ownerComment, Long recieverId, String recieverComment) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.keysetId = keysetId;
        this.reqOwnerId = reqOwnerId;
        this.ownerReason = ownerReason;
        this.ownerComment = ownerComment;
        this.recieverId = recieverId;
        this.recieverComment = recieverComment;
    }

    public RequestCommonReq(Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, String ownerComment, Long recieverId, String recieverComment, Long bomId, String bomComment, Long zhUserId, String zhUserComment) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.keysetId = keysetId;
        this.reqOwnerId = reqOwnerId;
        this.ownerReason = ownerReason;
        this.ownerComment = ownerComment;
        this.recieverId = recieverId;
        this.recieverComment = recieverComment;
        this.bomId = bomId;
        this.bomComment = bomComment;
        this.zhUserId = zhUserId;
        this.zhUserComment = zhUserComment;
    }

    public RequestCommonReq(Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, String ownerComment, Long recieverId, String recieverComment, Long bomId, String bomComment, Long zhUserId, String zhUserComment, String exceptionMsg, String requestStatus) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.keysetId = keysetId;
        this.reqOwnerId = reqOwnerId;
        this.ownerReason = ownerReason;
        this.ownerComment = ownerComment;
        this.recieverId = recieverId;
        this.recieverComment = recieverComment;
        this.bomId = bomId;
        this.bomComment = bomComment;
        this.zhUserId = zhUserId;
        this.zhUserComment = zhUserComment;
        this.exceptionMsg = exceptionMsg;
        this.requestStatus = requestStatus;
    }

    public RequestCommonReq(Long reqId, Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, String ownerComment, Long recieverId, String recieverComment, Long bomId, String bomComment, Long zhUserId, String zhUserComment, String exceptionMsg, String requestStatus, Long reqByid, String reqByName) {
        this.reqId = reqId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.keysetId = keysetId;
        this.reqOwnerId = reqOwnerId;
        this.ownerReason = ownerReason;
        this.ownerComment = ownerComment;
        this.recieverId = recieverId;
        this.recieverComment = recieverComment;
        this.bomId = bomId;
        this.bomComment = bomComment;
        this.zhUserId = zhUserId;
        this.zhUserComment = zhUserComment;
        this.exceptionMsg = exceptionMsg;
        this.requestStatus = requestStatus;
        this.reqByid = reqByid;
        this.reqByName = reqByName;
    }

    public RequestCommonReq(Long reqId, Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, Long reqByid, String reqByName, String ownerComment, Long recieverId, String recieverComment, Long bomId, String bomComment, Long zhUserId, String zhUserComment, String exceptionMsg, String requestStatus) {
        this.reqId = reqId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.keysetId = keysetId;
        this.reqOwnerId = reqOwnerId;
        this.ownerReason = ownerReason;
        this.reqByid = reqByid;
        this.reqByName = reqByName;
        this.ownerComment = ownerComment;
        this.recieverId = recieverId;
        this.recieverComment = recieverComment;
        this.bomId = bomId;
        this.bomComment = bomComment;
        this.zhUserId = zhUserId;
        this.zhUserComment = zhUserComment;
        this.exceptionMsg = exceptionMsg;
        this.requestStatus = requestStatus;
    }

    public RequestCommonReq(Long reqId, Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, Long reqByid, String reqByName, String ownerComment, Long recieverId, String recieverComment, Long bomId, String bomComment, Long zhUserId, String zhUserComment, String exceptionMsg, String requestStatus, List<Long> branchlist, String reqFromDate, String reqToDate) {
        this.reqId = reqId;
        this.branchId = branchId;
        this.branchName = branchName;
        this.keysetId = keysetId;
        this.reqOwnerId = reqOwnerId;
        this.ownerReason = ownerReason;
        this.reqByid = reqByid;
        this.reqByName = reqByName;
        this.ownerComment = ownerComment;
        this.recieverId = recieverId;
        this.recieverComment = recieverComment;
        this.bomId = bomId;
        this.bomComment = bomComment;
        this.zhUserId = zhUserId;
        this.zhUserComment = zhUserComment;
        this.exceptionMsg = exceptionMsg;
        this.requestStatus = requestStatus;
        this.branchlist = branchlist;
        this.reqFromDate = reqFromDate;
        this.reqToDate = reqToDate;
    }

    public String getReqFromDate() {
        return reqFromDate;
    }

    public void setReqFromDate(String reqFromDate) {
        this.reqFromDate = reqFromDate;
    }

    public String getReqToDate() {
        return reqToDate;
    }

    public void setReqToDate(String reqToDate) {
        this.reqToDate = reqToDate;
    }

    public List<Long> getBranchlist() {
        return branchlist;
    }

    public void setBranchlist(List<Long> branchlist) {
        this.branchlist = branchlist;
    }

    public Long getReqByid() {
        return reqByid;
    }

    public void setReqByid(Long reqByid) {
        this.reqByid = reqByid;
    }

    public String getReqByName() {
        return reqByName;
    }

    public void setReqByName(String reqByName) {
        this.reqByName = reqByName;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getReqId() {
        return reqId;
    }

    public void setReqId(Long reqId) {
        this.reqId = reqId;
    }


    public String getExceptionMsg() {
        return exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }


    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Long getKeysetId() {
        return keysetId;
    }

    public void setKeysetId(Long keysetId) {
        this.keysetId = keysetId;
    }

    public Long getReqOwnerId() {
        return reqOwnerId;
    }

    public void setReqOwnerId(Long reqOwnerId) {
        this.reqOwnerId = reqOwnerId;
    }

    public String getOwnerReason() {
        return ownerReason;
    }

    public void setOwnerReason(String ownerReason) {
        this.ownerReason = ownerReason;
    }

    public String getOwnerComment() {
        return ownerComment;
    }

    public void setOwnerComment(String ownerComment) {
        this.ownerComment = ownerComment;
    }

    public Long getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Long recieverId) {
        this.recieverId = recieverId;
    }

    public String getRecieverComment() {
        return recieverComment;
    }

    public void setRecieverComment(String recieverComment) {
        this.recieverComment = recieverComment;
    }

    public Long getBomId() {
        return bomId;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public String getBomComment() {
        return bomComment;
    }

    public void setBomComment(String bomComment) {
        this.bomComment = bomComment;
    }

    public Long getZhUserId() {
        return zhUserId;
    }

    public void setZhUserId(Long zhUserId) {
        this.zhUserId = zhUserId;
    }

    public String getZhUserComment() {
        return zhUserComment;
    }

    public void setZhUserComment(String zhUserComment) {
        this.zhUserComment = zhUserComment;
    }

    @Override
    public String toString() {
        return "RequestCommonReq{" +
                "branchId=" + branchId +
                ", branchName='" + branchName + '\'' +
                ", keysetId=" + keysetId +
                ", reqOwnerId=" + reqOwnerId +
                ", ownerReason='" + ownerReason + '\'' +
                ", ownerComment='" + ownerComment + '\'' +
                ", recieverId=" + recieverId +
                ", recieverComment='" + recieverComment + '\'' +
                ", bomId=" + bomId +
                ", bomComment='" + bomComment + '\'' +
                ", zhUserId=" + zhUserId +
                ", zhUserComment='" + zhUserComment + '\'' +
                ", exceptionMsg='" + exceptionMsg + '\'' +
                '}';
    }
}
