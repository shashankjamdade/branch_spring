package com.fsfb.branchregister.branchregister.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "req_detail")
public class RequestDetail {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Long id;
    Long branchId;
    String branchName;
    Long keysetId;
    Long reqOwnerId;
    String ownerReason;
    String ownerComment;
    Long recieverId;
    String recieverComment;
    Long bomId;
    String bomComment;
    Long zhUserId;
    String zhUserComment;
    int isZhRequired;
//    @Enumerated(EnumType.STRING)
    String requestStatus;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reqId")
    private List<RequestHistory> requestHistoryList;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "reqId")
    private List<RequestException> requestExceptionList;

    public RequestDetail() {
    }

    public RequestDetail(Long id, Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, String ownerComment, Long recieverId, String recieverComment, Long bomId, String bomComment, Long zhUserId, String zhUserComment, int isZhRequired, String requestStatus, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
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
        this.isZhRequired = isZhRequired;
        this.requestStatus = requestStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    //For request creation
    public RequestDetail(Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, String ownerComment, Long recieverId, String requestStatus) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.keysetId = keysetId;
        this.reqOwnerId = reqOwnerId;
        this.ownerReason = ownerReason;
        this.ownerComment = ownerComment;
        this.recieverId = recieverId;
        this.requestStatus = requestStatus;
        this.modifiedDate = LocalDateTime.now();
        this.createdDate = LocalDateTime.now();
    }

    public RequestDetail(Long id, Long branchId, String branchName, Long keysetId, Long reqOwnerId, String ownerReason, String ownerComment, Long recieverId, String recieverComment, Long bomId, String bomComment, Long zhUserId, String zhUserComment, int isZhRequired, String requestStatus, LocalDateTime createdDate, LocalDateTime modifiedDate, List<RequestHistory> requestHistoryList, List<RequestException> requestExceptionList) {
        this.id = id;
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
        this.isZhRequired = isZhRequired;
        this.requestStatus = requestStatus;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.requestHistoryList = requestHistoryList;
        this.requestExceptionList = requestExceptionList;
    }

    public List<RequestHistory> getRequestHistoryList() {
        return requestHistoryList;
    }

    public void setRequestHistoryList(List<RequestHistory> requestHistoryList) {
        this.requestHistoryList = requestHistoryList;
    }

    public List<RequestException> getRequestExceptionList() {
        return requestExceptionList;
    }

    public void setRequestExceptionList(List<RequestException> requestExceptionList) {
        this.requestExceptionList = requestExceptionList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getIsZhRequired() {
        return isZhRequired;
    }

    public void setIsZhRequired(int isZhRequired) {
        this.isZhRequired = isZhRequired;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "RequestDetail{" +
                "id=" + id +
                ", branchId=" + branchId +
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
                ", isZhRequired=" + isZhRequired +
                ", requestStatus='" + requestStatus + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", requestHistoryList=" + requestHistoryList +
                ", requestExceptionList=" + requestExceptionList +
                '}';
    }
}
