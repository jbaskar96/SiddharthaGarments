package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.OperatorMaster;
import com.siddhartha.garments.entity.SectionMaster;

@Repository
public interface OperatorMasterRepository extends JpaRepository<OperatorMaster, String> {

	List<OperatorMaster> findByStatusIgnoreCase(String status);

	List<OperatorMaster> findByStatusIgnorecase(String status);

}
