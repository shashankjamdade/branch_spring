package com.fsfb.branchregister.branchregister.repository;

import com.fsfb.branchregister.branchregister.model.RequestHistory;
import com.fsfb.branchregister.branchregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestHistoryRepository extends JpaRepository<RequestHistory, Long> {

    List<RequestHistory> findAllByReqId(Long req_id);
}
