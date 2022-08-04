package com.fsfb.branchregister.branchregister.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "key_master")
public class KeyMaster {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Long id;

//    @ManyToOne
//    BranchKeyMaster branchkeymaster;

    @Column(name = "key_name")
    String keyName;
    String description;
    @Column(name = "key_serial_no")
    String keySerialNo;
    @Column(name = "key_status")
    int keyStatus;
    @Column(name = "created_date")
    LocalDateTime createdDate;
    @Column(name = "modified_date")
    LocalDateTime modifiedDate;
    Long branchkeymaster_id;

    public KeyMaster() {
    }

    public KeyMaster(BranchKeyMaster branchkeymaster, String keyName, String description, String keySerialNo, int keyStatus, LocalDateTime createdDate, LocalDateTime modifiedDate) {
//        this.branchkeymaster = branchkeymaster;
        this.keyName = keyName;
        this.description = description;
        this.keySerialNo = keySerialNo;
        this.keyStatus = keyStatus;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public KeyMaster(String keyName, String description, String keySerialNo, int keyStatus) {
        this.keyName = keyName;
        this.description = description;
        this.keySerialNo = keySerialNo;
        this.keyStatus = keyStatus;
        this.modifiedDate = LocalDateTime.now();
    }

//    public BranchKeyMaster getBranchkeymaster() {
//        return branchkeymaster;
//    }

//    public void setBranchkeymaster(BranchKeyMaster branchkeymaster) {
//        this.branchkeymaster = branchkeymaster;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public BranchKeyMaster getKeysetid() {
//        return branchkeymaster;
//    }

//    public void setKeysetid(BranchKeyMaster keysetid) {
//        this.branchkeymaster = keysetid;
//    }


    public Long getBranchkeymaster_id() {
        return branchkeymaster_id;
    }

    public void setBranchkeymaster_id(Long branchkeymaster_id) {
        this.branchkeymaster_id = branchkeymaster_id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getKeySerialNo() {
        return keySerialNo;
    }

    public void setKeySerialNo(String keySerialNo) {
        this.keySerialNo = keySerialNo;
    }

    public int getKeyStatus() {
        return keyStatus;
    }

    public void setKeyStatus(int keyStatus) {
        this.keyStatus = keyStatus;
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
        return "KeyMaster{" +
                "id=" + id +
                ", keyName='" + keyName + '\'' +
                ", description='" + description + '\'' +
                ", keySerialNo='" + keySerialNo + '\'' +
                ", keyStatus=" + keyStatus +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", branchkeymaster_id=" + branchkeymaster_id +
                '}';
    }
}


