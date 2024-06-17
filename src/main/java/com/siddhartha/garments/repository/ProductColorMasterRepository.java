package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductColorMaster;
import com.siddhartha.garments.entity.ProductColorMasterId;

@Repository
public interface ProductColorMasterRepository extends JpaRepository<ProductColorMaster, ProductColorMasterId>{

	List<ProductColorMaster> findByIdCompanyIdAndIdProductId(Integer valueOf, Integer valueOf2);
			

	List<ProductColorMaster> findByIdCompanyIdAndIdProductIdAndStatusIgnoreCase(Integer companyId,
			Integer productId, String string);


	List<ProductColorMaster> findByStatusIgnoreCase(String string);



}
