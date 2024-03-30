package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductMetalMaster;
import com.siddhartha.garments.entity.ProductMetalMasterId;

@Repository
public interface ProductMetalRepository extends JpaRepository<ProductMetalMaster, ProductMetalMasterId>{

	Long countByIdCompanyIdAndIdProductId(Integer companyId, Integer productId);

	List<ProductMetalMaster> findByIdCompanyIdAndIdProductId(Integer valueOf, Integer valueOf2);

	List<ProductMetalMaster> findByIdCompanyIdAndIdProductIdAndStatus(Integer companyId, Integer productId,
			String string);

	List<ProductMetalMaster> findByIdCompanyIdAndIdProductIdAndStatusOrderByDisplayOrder(Integer companyId,
			Integer productId, String string);

}
