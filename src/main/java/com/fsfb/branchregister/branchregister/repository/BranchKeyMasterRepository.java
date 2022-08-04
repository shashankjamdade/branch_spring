package com.fsfb.branchregister.branchregister.repository;

import com.fsfb.branchregister.branchregister.model.BranchKeyMaster;
import com.fsfb.branchregister.branchregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchKeyMasterRepository extends JpaRepository<BranchKeyMaster, Long> {

   /* @Query( "select br from branch_key_set_master br  where br.branchId = " )
    public List<BranchKeyMaster> findAllKeysByBranchIdNew(@Param("branchId") Long branchId);

    public List<BranchKeyMaster> findAllKeysByBranchId(@Param("branchId") Long branchId);*/

    //select bkm from branch_key_set_master bkm join key_master km on km.branchkeymaster_id = bkm.id
//    @Query(value = "select * from BranchKeyMaster bkm join KeyMaster km on km.branchkeymaster.id = bkm.id where bkm.branchId=123", nativeQuery = true)
    @Query(value = "select distinct * from branch_key_set_master bkm join key_master km on km.branchkeymaster_id = bkm.id", nativeQuery = true)
    public List<BranchKeyMaster> findAllKeysByBranchIdNew();

    List<BranchKeyMaster> findByBranchId(Long branchId);
    List<BranchKeyMaster> findByuserid(Long userid);
}
