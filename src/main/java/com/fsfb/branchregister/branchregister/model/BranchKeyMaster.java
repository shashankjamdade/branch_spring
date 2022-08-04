package com.fsfb.branchregister.branchregister.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "branch_key_set_master")
public class BranchKeyMaster {

    @Column(nullable = false)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    long id;
    String label;
    @Column(name = "branch_id")
    long branchId;
    int status;
    long userid;
    String username;
    String key_name;

   /* @OneToMany(targetEntity = KeyMaster.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "k_id", referencedColumnName = "id")
    private List<KeyMaster> keysa;*/

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "branchkeymaster_id")
    private List<KeyMaster> keysa;

    @CreatedDate
    @Column(name = "created_date")
    LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "modified_date")
    LocalDateTime modifiedDate;

    public BranchKeyMaster() {
    }

    public BranchKeyMaster(long id, String label, long branchId, int status, List<KeyMaster> keysa, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.label = label;
        this.branchId = branchId;
        this.status = status;
        this.keysa = keysa;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public BranchKeyMaster(String label, int status, List<KeyMaster> keysa) {
        this.label = label;
        this.status = status;
        this.keysa = keysa;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }
    public BranchKeyMaster(String label, int status) {
        this.label = label;
        this.status = status;
        this.createdDate = LocalDateTime.now();
        this.modifiedDate = LocalDateTime.now();
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getId() {
        return id;
    }

    /*public void setId(long id) {
        this.id = id;
    }*/

    public void setId(long id) {
        this.id = id;
    }

    public String getKey_name() {
        return key_name;
    }

    public void setKey_name(String key_name) {
        this.key_name = key_name;
    }

    public List<KeyMaster> getKeysa() {
        return keysa;
    }

    public void setKeysa(List<KeyMaster> keysa) {
        this.keysa = keysa;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getBranchId() {
        return branchId;
    }

    public void setBranchId(long branchId) {
        this.branchId = branchId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
