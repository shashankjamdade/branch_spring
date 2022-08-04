package com.fsfb.branchregister.branchregister.repository;

import com.fsfb.branchregister.branchregister.model.BranchKeyMaster;
import com.fsfb.branchregister.branchregister.model.KeyMaster;
import com.fsfb.branchregister.branchregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyMasterRepository extends JpaRepository<KeyMaster, Long> {


}
