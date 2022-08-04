package com.fsfb.branchregister.branchregister.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "req_exceptions")
public class RequestException {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Long id;
    Long reqId;
    String msg;
    String reqExceptionType;
    LocalDateTime createdDate;
    LocalDateTime modifiedDate;

    public RequestException() {
    }

    public RequestException(Long req_id, String msg, String reqExceptionType) {
        this.reqId = req_id;
        this.msg = msg;
        this.reqExceptionType = reqExceptionType;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public String getReqExceptionType() {
        return reqExceptionType;
    }

    public void setReqExceptionType(String reqExceptionType) {
        this.reqExceptionType = reqExceptionType;
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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getExceptionType() {
        return reqExceptionType;
    }

    public void setExceptionType(String reqExceptionType) {
        this.reqExceptionType = reqExceptionType;
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
