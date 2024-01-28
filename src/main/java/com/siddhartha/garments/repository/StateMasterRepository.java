package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.StateMaster;

@Repository
public interface StateMasterRepository extends JpaRepository<StateMaster, String>{

	List<StateMaster> findByStatusIgnoreCase(String status);

}
