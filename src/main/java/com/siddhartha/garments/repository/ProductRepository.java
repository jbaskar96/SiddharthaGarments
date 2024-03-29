package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductMaster;
import com.siddhartha.garments.entity.ProductMasterId;

@Repository
public interface ProductRepository  extends JpaRepository<ProductMaster,ProductMasterId>{

	List<ProductMaster> findByIdCompanyId(Integer companyId);

	List<ProductMaster> findByStatusIgnoreCase(String status);

	List<ProductMaster> findByIdCompanyIdAndStatusIgnoreCase(Integer companyId,String status);

}
