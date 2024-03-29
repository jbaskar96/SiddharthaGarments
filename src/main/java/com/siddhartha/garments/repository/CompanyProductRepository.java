package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.CompanyProductMaster;
import com.siddhartha.garments.entity.CompanyProductMasterId;

@Repository
public interface CompanyProductRepository  extends JpaRepository<CompanyProductMaster,CompanyProductMasterId>{

	List<CompanyProductMaster> findByIdCompanyId(Integer companyId);

	List<CompanyProductMaster> findByStatusIgnoreCase(String status);

	List<CompanyProductMaster> findByIdCompanyIdAndStatusIgnoreCase(Integer companyId,String status);

}
