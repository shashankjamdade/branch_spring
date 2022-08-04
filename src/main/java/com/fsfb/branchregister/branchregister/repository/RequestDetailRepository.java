package com.fsfb.branchregister.branchregister.repository;

import com.fsfb.branchregister.branchregister.model.RequestDetail;
import com.fsfb.branchregister.branchregister.model.RequestHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface RequestDetailRepository extends JpaRepository<RequestDetail, Long> {



    List<RequestDetail> findBybranchIdIn(Collection<Long> branchIds);

    /**
     * Owner search
     */
    @Query(value = "SELECT * FROM branch_register.req_detail WHERE (created_date BETWEEN :reqFrom AND :reqTo) && req_owner_id=:ownerId", nativeQuery = true)
    List<RequestDetail> findAllByCreatedDateBetweenOwner(String reqFrom,
            String reqTo, String ownerId);

    @Query(value = "SELECT * FROM branch_register.req_detail WHERE (created_date BETWEEN :reqFrom AND :reqTo) && id=:reqId && req_owner_id=:ownerId", nativeQuery = true)
    List<RequestDetail> findAllByCreatedDateBetweenByIdOwner(String reqId, String reqFrom,
            String reqTo, String ownerId);

    List<RequestDetail> findByReqOwnerId(Long reqOwnerId);

    /**
     * Reciever search
     */
    @Query(value = "SELECT * FROM branch_register.req_detail WHERE (created_date BETWEEN :reqFrom AND :reqTo) && reciever_id=:reciever_id", nativeQuery = true)
    List<RequestDetail> findAllByCreatedDateBetweenReciever(String reqFrom,
                                                    String reqTo, String reciever_id);

    @Query(value = "SELECT * FROM branch_register.req_detail WHERE (created_date BETWEEN :reqFrom AND :reqTo) && id=:reqId && reciever_id=:reciever_id", nativeQuery = true)
    List<RequestDetail> findAllByCreatedDateBetweenByIdReciever(String reqId, String reqFrom,
                                                        String reqTo, String reciever_id);

    List<RequestDetail> findByRecieverId(Long recieverId);

    /**
     * BOM search
     */
    @Query(value = "SELECT * FROM branch_register.req_detail WHERE (created_date BETWEEN :reqFrom AND :reqTo) && branch_id in :branches", nativeQuery = true)
    List<RequestDetail> findAllByCreatedDateBetweenBom(String reqFrom,
                                                            String reqTo, List<String> branches);

    @Query(value = "SELECT * FROM branch_register.req_detail WHERE (created_date BETWEEN :reqFrom AND :reqTo) && id=:reqId && branch_id in :branches", nativeQuery = true)
    List<RequestDetail> findAllByCreatedDateBetweenByIdBom(String reqId, String reqFrom,
                                                                String reqTo, List<String> branches);


}
