package com.fsfb.branchregister.branchregister.model;


import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class BranchKeyMasterMain{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    Long id;

    long branchCode;
    String branchName;
    String branchType;
    String keySet;
    String keyType;
    String keyNumber;
    Long keyHeldByUserId;
    String keyHeldByUsername;
    String empDesg;
    String empDept;

    public BranchKeyMasterMain() {
    }

    public BranchKeyMasterMain(Long id, Long branchCode, String branchName, String branchType, String keySet, String keyType, String keyNumber, Long keyHeldByUserId, String keyHeldByUsername, String empDesg, String empDept) {
        this.id = id;
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.branchType = branchType;
        this.keySet = keySet;
        this.keyType = keyType;
        this.keyNumber = keyNumber;
        this.keyHeldByUserId = keyHeldByUserId;
        this.keyHeldByUsername = keyHeldByUsername;
        this.empDesg = empDesg;
        this.empDept = empDept;
    }

    public BranchKeyMasterMain(Long branchCode, String branchName, String branchType, String keySet, String keyType, String keyNumber, Long keyHeldByUserId, String keyHeldByUsername, String empDesg, String empDept) {
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.branchType = branchType;
        this.keySet = keySet;
        this.keyType = keyType;
        this.keyNumber = keyNumber;
        this.keyHeldByUserId = keyHeldByUserId;
        this.keyHeldByUsername = keyHeldByUsername;
        this.empDesg = empDesg;
        this.empDept = empDept;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Long branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchType() {
        return branchType;
    }

    public void setBranchType(String branchType) {
        this.branchType = branchType;
    }

    public String getKeySet() {
        return keySet;
    }

    public void setKeySet(String keySet) {
        this.keySet = keySet;
    }

    public String getKeyType() {
        return keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyNumber() {
        return keyNumber;
    }

    public void setKeyNumber(String keyNumber) {
        this.keyNumber = keyNumber;
    }

    public Long getKeyHeldByUserId() {
        return keyHeldByUserId;
    }

    public void setKeyHeldByUserId(Long keyHeldByUserId) {
        this.keyHeldByUserId = keyHeldByUserId;
    }

    public String getKeyHeldByUsername() {
        return keyHeldByUsername;
    }

    public void setKeyHeldByUsername(String keyHeldByUsername) {
        this.keyHeldByUsername = keyHeldByUsername;
    }

    public String getEmpDesg() {
        return empDesg;
    }

    public void setEmpDesg(String empDesg) {
        this.empDesg = empDesg;
    }

    public String getEmpDept() {
        return empDept;
    }

    public void setEmpDept(String empDept) {
        this.empDept = empDept;
    }
}