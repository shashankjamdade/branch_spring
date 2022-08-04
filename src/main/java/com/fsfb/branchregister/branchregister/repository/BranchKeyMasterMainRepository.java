package com.fsfb.branchregister.branchregister.repository;

import com.fsfb.branchregister.branchregister.model.BranchKeyMaster;
import com.fsfb.branchregister.branchregister.model.BranchKeyMasterMain;
import com.fsfb.branchregister.branchregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Repository
public interface BranchKeyMasterMainRepository extends JpaRepository<BranchKeyMasterMain, Long> {

//    List<BranchKeyMasterMain> findByBranchKeyMasterMainId(long branchCode);

}
