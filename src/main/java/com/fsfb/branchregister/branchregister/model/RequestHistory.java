package com.fsfb.branchregister.branchregister.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "req_history")
public class RequestHistory {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Long id;
    Long reqId;
    Long user_id;
    Long branchId;
    String username;
    String requestStatus;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public RequestHistory() {
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public RequestHistory(Long id, Long req_id, Long user_id, String requestStatus, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.reqId = req_id;
        this.user_id = user_id;
        this.requestStatus = requestStatus;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }
    public RequestHistory(Long req_id, Long user_id,  Long branchId, String username, String requestStatus) {
        this.reqId = req_id;
        this.user_id = user_id;
        this.branchId = branchId;
        this.username = username;
        this.requestStatus = requestStatus;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReq_id() {
        return reqId;
    }

    public void setReq_id(Long req_id) {
        this.reqId = req_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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
}
