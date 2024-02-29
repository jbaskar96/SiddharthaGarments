package com.siddhartha.garments.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.siddhartha.garments.entity.ProductSizeColorMetalMaster;
import com.siddhartha.garments.entity.ProductSizeColorMetalMasterId;

@Repository
public interface ProductSizeColorMetalMasterRepository extends JpaRepository<ProductSizeColorMetalMaster, ProductSizeColorMetalMasterId> {

	List<ProductSizeColorMetalMaster> findByIdCompanyIdAndIdProductIdAndIdSizeIdAndIdColourCode(Integer companyId, Integer productId, Integer sizeId,
			Integer colorId);

	List<ProductSizeColorMetalMaster> findByIdCompanyIdAndIdProductIdAndIdSizeIdAndIdColourCode(Integer companyId, Integer productId, Integer sizeId,
			String colorId);

}
