package com.fsfb.branchregister.branchregister.repository;

import com.fsfb.branchregister.branchregister.model.RequestDetail;
import com.fsfb.branchregister.branchregister.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
