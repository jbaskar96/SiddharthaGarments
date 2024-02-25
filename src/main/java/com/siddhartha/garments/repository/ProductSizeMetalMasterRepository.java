package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductSizeMetalMaster;
import com.siddhartha.garments.entity.ProductSizeMetalMasterId;

@Repository
public interface ProductSizeMetalMasterRepository extends JpaRepository<ProductSizeMetalMaster, ProductSizeMetalMasterId>{

	List<ProductSizeMetalMaster> findByIdCompanyIdAndIdProductId(Integer companyId, Integer productId);

	List<ProductSizeMetalMaster> findByIdCompanyIdAndIdProductIdAndIdMetalId(Integer valueOf, Integer valueOf2,
			Integer valueOf3);

	List<ProductSizeMetalMaster> findByIdCompanyIdAndIdProductIdAndIdSizeId(Integer valueOf, Integer valueOf2,
			Integer valueOf3);

}
