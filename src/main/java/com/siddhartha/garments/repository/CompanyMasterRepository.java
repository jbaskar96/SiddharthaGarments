package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.CompanyMaster;

@Repository
public interface CompanyMasterRepository extends JpaRepository<CompanyMaster, Integer> {

	List<CompanyMaster> findByStatusIgnoreCase(String string);

}
