package com.siddhartha.garments.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.CompanyMaster;

@Repository
public interface CompanyProductMasterRepository extends JpaRepository<CompanyMaster, Integer> {

}
