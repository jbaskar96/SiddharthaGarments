package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.UserTypeMaster;

@Repository
public interface UserTypeMasterRepository extends JpaRepository<UserTypeMaster, Integer> {

	List<UserTypeMaster> findByStatusIgnoreCase(String status);

}
