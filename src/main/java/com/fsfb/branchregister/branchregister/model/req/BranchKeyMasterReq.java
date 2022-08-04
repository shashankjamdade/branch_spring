package com.fsfb.branchregister.branchregister.model.req;

import com.fsfb.branchregister.branchregister.model.BranchKeyMaster;
import com.fsfb.branchregister.branchregister.model.KeyMaster;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BranchKeyMasterReq {
    private Long branchId;
    BranchKeyMaster branchKeyMaster;
    ArrayList<KeyMaster> keyMasters;

   /* public BranchKeyMasterReq(Long branchId) {
        this.branchId = branchId;
    }*/

    public BranchKeyMaster getBranchKeyMaster() {
        return branchKeyMaster;
    }

    public void setBranchKeyMaster(BranchKeyMaster branchKeyMaster) {
        this.branchKeyMaster = branchKeyMaster;
    }

    public ArrayList<KeyMaster> getKeyMasters() {
        return keyMasters;
    }

    public void setKeyMasters(ArrayList<KeyMaster> keyMasters) {
        this.keyMasters = keyMasters;
    }

    public BranchKeyMasterReq(){}

    public BranchKeyMasterReq(Long branchId) {
        this.branchId = branchId;
    }

    public Long getBranchId() {
        return branchId;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchKeyMasterReq masterReq = (BranchKeyMasterReq) o;
        return Objects.equals(branchId, masterReq.branchId) && Objects.equals(branchKeyMaster, masterReq.branchKeyMaster) && Objects.equals(keyMasters, masterReq.keyMasters);
    }

    @Override
    public String toString() {
        return "BranchKeyMasterReq{" +
                "branchId=" + branchId +
                ", branchKeyMaster=" + branchKeyMaster +
                ", keyMasters=" + keyMasters +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(branchId, branchKeyMaster, keyMasters);
    }
}
